package com.microservices.learn.controller;

import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.service.EmployeeService;
import com.microservices.learn.web.custom.test.utils.JSONUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
Controller Layer Unit Testing:
Here we are testing only EmployeeController, not the complete Spring Boot application.
So we do not use @SpringBootTest because that would load the full Spring Application Context.
We also do not use @Transactional because the database is not called in this test.
The service layer is mocked, which means the controller receives fixed fake responses from EmployeeService.
This test class focuses on PUT /employees/updateEmployee.
 */
public class UpdateEmployeeUnitTest {

    //Step-1: Declare Service Layer mock.
    //The mocked service prevents actual service, DAO, and database logic from running.
    @Mock
    private EmployeeService employeeService;

    //Tells Mockito to inject mocked employeeService into EmployeeController.
    //This allows us to test the controller in isolation.
    @InjectMocks
    private EmployeeController employeeController;

    //MockMvc sends fake HTTP requests to EmployeeController without starting a server.
    protected MockMvc mockMvc;

    @BeforeEach
    public void mySetup(){
        //This method runs before every test case.
        //openMocks(this) activates @Mock and @InjectMocks annotations.
        MockitoAnnotations.openMocks(this);

        //Step-2: Create MockMVC which is replica of just Controller.
        //standaloneSetup registers only EmployeeController, keeping the test focused on controller behaviour.
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void updateEmployeeTest() throws Exception{
        String uri = "/employees/updateEmployee";

        //Arrange: Create employee request and mocked response object.
        //employeeBean represents JSON data sent by client.
        //updatedEmployee represents the value returned by service after successful update.
        EmployeeBean employeeBean = new EmployeeBean(1, "KK Updated", 60000.00, 456);
        EmployeeBean updatedEmployee = new EmployeeBean(1, "KK Updated", 60000.00, 456);

        //Convert request object into JSON because PUT endpoint accepts request body.
        String jsonInput = JSONUtils.convertFromObjectToJson(employeeBean);

        //Step-3: Use MockHttpServletRequestBuilder to create PUT request.
        //contentType tells controller that request body is JSON.
        //accept tells controller that test expects JSON response.
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.put(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput);

        //Step-4: Define mocked service behaviour for successful update.
        //Whenever controller calls updateEmployee() with any EmployeeBean,
        //Mockito returns updatedEmployee.
        when(employeeService.updateEmployee(any(EmployeeBean.class))).thenReturn(updatedEmployee);

        //Step-5: Perform the request using MockMVC and collect response result.
        ResultActions rest = mockMvc.perform(request);
        MvcResult mvcResult = rest.andReturn();

        //Extract response JSON and HTTP status from controller response.
        String result = mvcResult.getResponse().getContentAsString();
        int actualStatus = mvcResult.getResponse().getStatus();

        //Convert response JSON into EmployeeBean to verify each returned field.
        EmployeeBean actualEmployee = JSONUtils.convertFromJsonToObject(result, EmployeeBean.class);

        //Step-6: Verify that controller delegates update request to service layer once.
        //This confirms the controller did not skip the service call.
        verify(employeeService, times(1)).updateEmployee(any(EmployeeBean.class));

        //Step-7: Assert expected status and response body.
        //For successful update, controller should return 200 OK with updated employee details.
        Assertions.assertEquals(HttpStatus.OK.value(), actualStatus);
        Assertions.assertNotNull(actualEmployee);
        Assertions.assertEquals(1, actualEmployee.getEmployeeId());
        Assertions.assertEquals("KK Updated", actualEmployee.getEmployeeName());
        Assertions.assertEquals(60000.00, actualEmployee.getSalary());
        Assertions.assertEquals(456, actualEmployee.getDepartmentCode());
    }

    @Test
    public void updateEmployeeInvalidTest() throws Exception{
        String uri = "/employees/updateEmployee";

        //Arrange: Create employee request with invalid id.
        //This simulates a client trying to update an employee that does not exist.
        EmployeeBean employeeBean = new EmployeeBean(-1, "Invalid Employee", 60000.00, 456);

        //Convert invalid update request object into JSON request body.
        String jsonInput = JSONUtils.convertFromObjectToJson(employeeBean);

        //Step-3: Use MockHttpServletRequestBuilder to create PUT request.
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.put(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput);

        //Step-4: Define mocked service behaviour as null because employee is not found.
        //The controller should convert this null service response into 404 NOT_FOUND.
        when(employeeService.updateEmployee(any(EmployeeBean.class))).thenReturn(null);

        //Step-5: Perform the request using MockMVC.
        ResultActions rest = mockMvc.perform(request);
        MvcResult mvcResult = rest.andReturn();

        int actualStatus = mvcResult.getResponse().getStatus();

        //Step-6: Verify that controller delegates update request to service layer once.
        verify(employeeService, times(1)).updateEmployee(any(EmployeeBean.class));

        //Step-7: If employee is not found, controller should return 404 NOT_FOUND.
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), actualStatus);
    }

}
