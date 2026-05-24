package com.microservices.learn.controller;

import com.microservices.learn.C02D06SpringMicroservices22SpringBootEmployeeServiceUnitTestingOfControllerLayer;
import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.web.custom.test.utils.JSONUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/*To test the controller layer, we will be using the same annotations which we were using in Service Layer Testing. */

/*
It will associate the Unit with SpringTest Framework.
Used to tell that Spring is used to run the tests.
 */
@ExtendWith(SpringExtension.class)

/*
It is a replacement of @Configuration annotation.
Used to point to the files having the configuration and helps to load and start the context.
Context will be cached for all test cases and classes.
 */
@SpringBootTest(classes = C02D06SpringMicroservices22SpringBootEmployeeServiceUnitTestingOfControllerLayer.class)

/*
Used to run each test case in an individual transaction.
With default strategy as rollback, as service layer is hitting DB Layer.
So changes done to DB must be undone.
 */
@Transactional

/*
Now we will implement the step-1: Enable Web Application Context for the test cases. So that we can use MockMVC to send request and get the response from the controller.
Instead of putting on all methods, we will put it at class level.
@WebAppConfiguration
It will allow us to use MockMVC to send request and get the response from the controller.
 */
@WebAppConfiguration
public class GetAndGetAllIntegrationTests {

    //We will autowire the WebApplicationContext //cached.
    @Autowired
    private WebApplicationContext webApplicationContext;


    //Step-2: Create a MockMVC.
    //To create a MockMVC we use a protected variable of type MockMVC.
    protected MockMvc mockMvc;

    //This annotation makes sure that the method mySetup() will get executed before any test case gets executed.
    //So we are doing a setUp before running any test case.
    //In this setUp method, we are performing step-2 by:
    //Creating a MockMVCBuilder to create a request.
    //    Here MockMVCBuilder we are using,. A class which has a method webAppContextSetup().
    //    We are passing our entire context to it.
    //    We are using a build() method to create a MockMVCBuilder.
    //    So in short, it will create a MockMVC, which is an exact replica of actual Model, View and Controller (DAO, Service & Controller Layers).
    @BeforeEach
    public void mySetup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /*
    Now I need to test the functionalities of my controller. In controller, we can see I am checking functionality findAllEmployees(). So we created one method as - findAllEmployeesTest()
    We have added @Test annotation to register it as test case.
    We need to hit the endpoint - /employees/findAllEmployees. So we are setting it as String Variable uri.
    Then we are implementing step-3:
        Use MockMvcRequestBuilders to create the request with uri and http method.
        We are using MockHttpServletRequestBuilder. It will allow us to create a request to the handler method findAllEmployees() of controller class.
        So we are using MockHttpServletRequestBuilder, creating an object of this class as request & using the class MockMvcRequestBuilder.get() method & passing the uri.
        We are using the get() method, because the handler method is of type GET.
    This request will go to the endpoint /employees/findAllEmployees.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void findAllEmployeesTest() throws Exception{
        String uri = "/employees/findAllEmployees";
        //Use MockMvcRequestBuilders to create the request with uri and http method.
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri);

        //step-4: So we created a mock request for this controller method. Now using this request, we will implement perform method. Perform method will give us the result.
        //We will pass the request created earlier in perform method of mockMVC & it will return a result in ResultAction rest variable.
        //Converting this ResultAction into MvcResult using andReturn() method. Because I need to get the response from this mvcResult which is the actual result.
        ResultActions rest = mockMvc.perform(request);
        MvcResult mvcResult = rest.andReturn();

        //Now lets deserialise the output.
        //Step-5: Get the actual result and status from MvcResult and compare it with expected result and status.
        //My actual output is in mvcResult.
        //So we will use the getResponse() method to get the response which will be in the form of the employeeBean.
        //We will convert the employeeBean into a string using getContentAsString() method.
        //We will save it into a String variable result.
        //Similarly we can get the status from mvcResult using mvcResult.getResult().getStatus(); and we will store it in a variable of type int as actualStatus.
        String result = mvcResult.getResponse().getContentAsString();
        int actualStatus = mvcResult.getResponse().getStatus();

        //Currently our result is a JSON String, but we need it as a list of employeeBean.
        //For that we have already create one method - createFromJsonToObject to convert it into object and save it into a variable of type List<EmployeeBean> listEmp
        List<EmployeeBean> listEmp = JSONUtils.convertFromJsonToObject(result, List.class);

        //Now I have a List and the status code. So we can use Assertions.
        Assertions.assertNotNull(listEmp);
        Assertions.assertEquals(actualStatus, HttpStatus.OK.value());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void findEmployeeByIdTest() throws Exception{
        //First create an employee, because findEmployeeById needs an employee id that exists in the DB.
        EmployeeBean employeeBean = new EmployeeBean();
        employeeBean.setEmployeeName("KK");
        employeeBean.setDepartmentCode(123);
        employeeBean.setSalary(50000.00);

        //Convert the employee object into JSON so it can be sent in the POST request body.
        String jsonInput = JSONUtils.convertFromObjectToJson(employeeBean);

        //Create a POST request for /employees/addEmployee to save the employee using the controller.
        MockHttpServletRequestBuilder addRequest = MockMvcRequestBuilders
                .post("/employees/addEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInput);

        //Execute the POST request and read the saved employee from the JSON response.
        //The saved employee contains the generated employeeId.
        MvcResult addMvcResult = mockMvc.perform(addRequest).andReturn();
        String addResult = addMvcResult.getResponse().getContentAsString();
        EmployeeBean savedEmployee = JSONUtils.convertFromJsonToObject(addResult, EmployeeBean.class);

        //Now create the GET request using the generated id from the saved employee.
        String uri = "/employees/findEmployee/" + savedEmployee.getEmployeeId();
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri);

        //Perform the GET request and collect the actual MVC result.
        ResultActions rest = mockMvc.perform(request);
        MvcResult mvcResult = rest.andReturn();

        //Get the response body and HTTP status code from the MVC result.
        String result = mvcResult.getResponse().getContentAsString();
        int actualStatus = mvcResult.getResponse().getStatus();

        //Convert the JSON response back into an EmployeeBean object.
        EmployeeBean foundEmployee = JSONUtils.convertFromJsonToObject(result, EmployeeBean.class);

        //Verify that the employee was found and all returned fields match the saved employee.
        Assertions.assertNotNull(foundEmployee);
        Assertions.assertEquals(HttpStatus.OK.value(), actualStatus);
        Assertions.assertEquals(savedEmployee.getEmployeeId(), foundEmployee.getEmployeeId());
        Assertions.assertEquals("KK", foundEmployee.getEmployeeName());
        Assertions.assertEquals(123, foundEmployee.getDepartmentCode());
        Assertions.assertEquals(50000.00, foundEmployee.getSalary());
    }

    //negative test
    @Test
    public void findEmployeeByIdInvalidTest() throws Exception{
        //Use an invalid employee id which should not exist in the DB.
        String uri = "/employees/findEmployee/-1";

        //Create a GET request for /employees/findEmployee/{empId}.
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri);

        //Perform the request and collect the MVC result.
        ResultActions rest = mockMvc.perform(request);
        MvcResult mvcResult = rest.andReturn();

        //Get the actual HTTP status code from the response.
        int actualStatus = mvcResult.getResponse().getStatus();

        //If the employee is not found, controller should return 404 NOT_FOUND.
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), actualStatus);
    }

}
