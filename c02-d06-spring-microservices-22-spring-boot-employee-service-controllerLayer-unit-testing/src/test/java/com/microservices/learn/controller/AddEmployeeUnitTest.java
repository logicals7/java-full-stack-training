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
This makes the test fast and focused only on controller behaviour:
    1. Is the correct endpoint mapped?
    2. Is the request body accepted?
    3. Is the service method called?
    4. Is the expected HTTP status and JSON response returned?
 */
public class AddEmployeeUnitTest {

    //Step-1: Declare Service Layer mock.
    //Mockito will create a fake implementation of EmployeeService.
    //No real service logic, DAO logic, or database call will happen from this mock.
    @Mock
    private EmployeeService employeeService;

    //InjectMocks tells Mockito to create EmployeeController and inject the mocked employeeService into it.
    //This is similar to how Spring would inject EmployeeService in the real application,
    //but here Mockito is doing it only for the unit test.
    @InjectMocks
    private EmployeeController employeeController;

    //MockMvc is used to send fake HTTP requests to the controller.
    //It lets us test controller endpoints without starting an actual web server.
    protected MockMvc mockMvc;

    @BeforeEach
    public void mySetup(){
        //This method runs before every test case.
        //openMocks(this) activates @Mock and @InjectMocks annotations in this test class.
        MockitoAnnotations.openMocks(this);

        //Step-2: Create MockMVC for standalone controller testing.
        //standaloneSetup(employeeController) registers only this controller,
        //so the test remains a controller unit test instead of an integration test.
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void addEmployeeTest() throws Exception{
        String uri = "/employees/addEmployee";

        //Arrange: Create employee request object.
        //This object represents the data that a client sends in the POST request body.
        //employeeId is not set because usually the database generates the id while saving.
        EmployeeBean employeeBean = new EmployeeBean();
        employeeBean.setEmployeeName("KK");
        employeeBean.setSalary(56789056.0);
        employeeBean.setDepartmentCode(1234);

        //Arrange: Create mocked service response object.
        //This is what we want the mocked EmployeeService to return after "saving" the employee.
        //The id is present here to represent a successfully saved employee.
        EmployeeBean savedEmployee = new EmployeeBean(1, "KK", 56789056.0, 1234);

        //Convert the Java object to JSON string.
        //POST endpoints usually receive data as JSON in the request body.
        String jsonInput = JSONUtils.convertFromObjectToJson(employeeBean);

        //Step-3: Create POST request.
        //accept(MediaType.APPLICATION_JSON) means the test expects JSON response from controller.
        //contentType(MediaType.APPLICATION_JSON) means the request body being sent is JSON.
        //content(jsonInput) attaches the converted JSON data to the request body.
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput);

        //Step-4: Define mocked service behaviour.
        //When controller calls employeeService.addEmployee(any EmployeeBean),
        //Mockito will return savedEmployee instead of calling real service logic.
        when(employeeService.addEmployee(any(EmployeeBean.class))).thenReturn(savedEmployee);

        //Step-5: Perform the fake HTTP request using MockMvc.
        //andReturn() gives MvcResult, which contains response status, headers, and body.
        MvcResult mvcResult = mockMvc.perform(request).andReturn();

        //Extract response body as String and HTTP status as int from the MVC result.
        String result = mvcResult.getResponse().getContentAsString();
        int actualStatus = mvcResult.getResponse().getStatus();

        //Convert JSON response body back to EmployeeBean so individual fields can be asserted.
        EmployeeBean actualEmployee = JSONUtils.convertFromJsonToObject(result, EmployeeBean.class);

        //Step-6: Verify controller-service interaction.
        //This confirms that EmployeeController actually called employeeService.addEmployee() exactly one time.
        verify(employeeService, times(1)).addEmployee(any(EmployeeBean.class));

        //Step-7: Assert expected status and response body.
        //For successful creation, controller should return 201 CREATED and the saved employee details.
        Assertions.assertEquals(HttpStatus.CREATED.value(), actualStatus);
        Assertions.assertNotNull(actualEmployee);
        Assertions.assertEquals(1, actualEmployee.getEmployeeId());
        Assertions.assertEquals("KK", actualEmployee.getEmployeeName());
        Assertions.assertEquals(56789056.0, actualEmployee.getSalary());
        Assertions.assertEquals(1234, actualEmployee.getDepartmentCode());
    }

}
