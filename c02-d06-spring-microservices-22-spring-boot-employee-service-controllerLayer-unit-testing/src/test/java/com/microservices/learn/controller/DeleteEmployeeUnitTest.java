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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
Controller Layer Unit Testing:
Here we are testing only EmployeeController, not the complete Spring Boot application.
So we do not use @SpringBootTest because that would load the full Spring Application Context.
We also do not use @Transactional because the database is not called in this test.
The service layer is mocked, which means the controller receives fixed fake responses from EmployeeService.
This test class focuses on DELETE /employees/deleteEmployee/{empId}.
 */
public class DeleteEmployeeUnitTest {

    //Step-1: Declare Service Layer mock.
    //The mocked service prevents actual service, DAO, and database logic from running.
    @Mock
    private EmployeeService employeeService;

    //Tells Mockito to inject mocked employeeService into EmployeeController.
    //This makes EmployeeController ready for testing without Spring dependency injection.
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
    public void deleteEmployeeTest() throws Exception{
        String uri = "/employees/deleteEmployee/1";

        //Arrange: Create mocked service response object.
        //This represents the employee details returned by service after successful deletion.
        EmployeeBean deletedEmployee = new EmployeeBean(1, "KK", 50000.00, 123);

        //Step-3: Use MockHttpServletRequestBuilder to create DELETE request.
        //The id value 1 is passed as path variable empId to controller.
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(uri);

        //Step-4: Define mocked service behaviour for successful delete.
        //When controller calls employeeService.deleteEmployee(1), Mockito returns deletedEmployee.
        when(employeeService.deleteEmployee(1)).thenReturn(deletedEmployee);

        //Step-5: Perform the request using MockMVC and collect response result.
        ResultActions rest = mockMvc.perform(request);
        MvcResult mvcResult = rest.andReturn();

        //Extract response JSON and HTTP status from controller response.
        String result = mvcResult.getResponse().getContentAsString();
        int actualStatus = mvcResult.getResponse().getStatus();

        //Convert response JSON into EmployeeBean to verify returned employee fields.
        EmployeeBean actualEmployee = JSONUtils.convertFromJsonToObject(result, EmployeeBean.class);

        //Step-6: Verify that controller delegates delete request to service layer once.
        //This also verifies that path variable empId was passed as integer value 1.
        verify(employeeService, times(1)).deleteEmployee(1);

        //Step-7: Controller currently returns 302 FOUND after successful delete.
        //The assertions below confirm both HTTP status and returned employee details.
        Assertions.assertEquals(HttpStatus.FOUND.value(), actualStatus);
        Assertions.assertNotNull(actualEmployee);
        Assertions.assertEquals(1, actualEmployee.getEmployeeId());
        Assertions.assertEquals("KK", actualEmployee.getEmployeeName());
        Assertions.assertEquals(50000.00, actualEmployee.getSalary());
        Assertions.assertEquals(123, actualEmployee.getDepartmentCode());
    }

    @Test
    public void deleteEmployeeInvalidTest() throws Exception{
        String uri = "/employees/deleteEmployee/-1";

        //Step-3: Use MockHttpServletRequestBuilder to create DELETE request with invalid id.
        //Here -1 is used as a value that should not exist.
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(uri);

        //Step-4: Define mocked service behaviour as null because employee is not found.
        //The controller should convert this null service response into 404 NOT_FOUND.
        when(employeeService.deleteEmployee(-1)).thenReturn(null);

        //Step-5: Perform the request using MockMVC.
        ResultActions rest = mockMvc.perform(request);
        MvcResult mvcResult = rest.andReturn();

        int actualStatus = mvcResult.getResponse().getStatus();

        //Step-6: Verify that controller delegates delete request to service layer once.
        //This confirms the controller passed the invalid path variable value to service.
        verify(employeeService, times(1)).deleteEmployee(-1);

        //Step-7: If employee is not found, controller should return 404 NOT_FOUND.
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), actualStatus);
    }

}
