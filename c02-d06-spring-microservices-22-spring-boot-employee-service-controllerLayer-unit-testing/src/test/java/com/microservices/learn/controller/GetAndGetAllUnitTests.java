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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
Controller Layer Unit Testing:
Here we are testing only EmployeeController, not the complete Spring Boot application.
So we do not use @SpringBootTest because that would load the full Spring Application Context.
We also do not use @Transactional because the database is not called in this test.
The service layer is mocked, which means the controller receives fixed fake responses from EmployeeService.
This test class focuses on GET endpoints:
    1. /employees/findAllEmployees
    2. /employees/findEmployee/{empId}
 */
public class GetAndGetAllUnitTests {

    //Step-1: Declare Service Layer mock and inject it into controller.

    //This mock annotation instructs Mockito to analyse the class or interface
    //to produce a fake EmployeeService object with the same public methods.
    //The fake service does not execute actual service or DAO logic.
    @Mock
    private EmployeeService employeeService;

    //Tells Mockito to inject mocked objects into controller.
    //In our case mocked employeeService will be supplied to EmployeeController.
    @InjectMocks
    private EmployeeController employeeController;

    //MockMvc is used to send fake HTTP requests to the controller.
    //It lets us test request mapping, response status, and response body without running a server.
    protected MockMvc mockMvc;

    @BeforeEach
    public void mySetup(){
        //This method runs before every test case.
        //openMocks(this) activates @Mock and @InjectMocks annotations in this test class.
        MockitoAnnotations.openMocks(this);

        //Step-2: Using MockMvcBuilders to create a MockMVC which is replica of just Controller.
        //standaloneSetup keeps this as a true controller unit test because only EmployeeController is registered.
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void findAllEmployeesTest() throws Exception{
        String uri = "/employees/findAllEmployees";

        //Step-3: Use MockHttpServletRequestBuilder to create GET request.
        //This request will hit only the controller method mapped with /employees/findAllEmployees.
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri);

        //Step-4: Prepare expected response from mocked service.
        //When controller calls employeeService.findAllEmployees(),
        //Mockito returns this fixed list instead of calling real service/database logic.
        List<EmployeeBean> expectedEmployeeList = findAllEmployeesStubData();
        when(employeeService.findAllEmployees()).thenReturn(expectedEmployeeList);

        //Step-5: MockMVC created in step 2 will perform() the request created in step 3.
        //ResultActions lets us collect the final MVC response.
        ResultActions rest = mockMvc.perform(request);
        MvcResult mvcResult = rest.andReturn();

        //Step-6: Extract the actual response content and response status from MVCResult.
        //result contains JSON returned by controller.
        //actualStatus contains HTTP status code returned by controller.
        String result = mvcResult.getResponse().getContentAsString();
        int actualStatus = mvcResult.getResponse().getStatus();

        //Currently our result is a JSON Array.
        //When JSONUtils converts this JSON using List.class, Jackson creates List<Map<String, Object>>.
        //That is why we validate employee values by reading keys like employeeId, employeeName, salary and departmentCode.
        List<Map<String, Object>> employeeBeanList = JSONUtils.convertFromJsonToObject(result, List.class);

        //Step-7: Verify if the controller is able to delegate the call to mock.
        //As we expect the controller to invoke the findAllEmployees() method of the employeeService once,
        //we have written the following statement.
        verify(employeeService, times(1)).findAllEmployees();

        //First check the common controller response expectations:
        //response body should not be null, status should be 200 OK,
        //and number of employees returned should match the mocked service list.
        Assertions.assertNotNull(employeeBeanList);
        Assertions.assertEquals(HttpStatus.OK.value(), actualStatus);
        Assertions.assertEquals(expectedEmployeeList.size(), employeeBeanList.size());

        //Improved assertions: checking actual response body values, not only status and not-null.
        //This makes the test stronger because it confirms that the controller writes the expected JSON data.
        Assertions.assertEquals(1, employeeBeanList.get(0).get("employeeId"));
        Assertions.assertEquals("KK1", employeeBeanList.get(0).get("employeeName"));
        Assertions.assertEquals(100119716.0, employeeBeanList.get(0).get("salary"));
        Assertions.assertEquals(101, employeeBeanList.get(0).get("departmentCode"));

        Assertions.assertEquals(4, employeeBeanList.get(3).get("employeeId"));
        Assertions.assertEquals("KK4", employeeBeanList.get(3).get("employeeName"));
        Assertions.assertEquals(100119719.0, employeeBeanList.get(3).get("salary"));
        Assertions.assertEquals(104, employeeBeanList.get(3).get("departmentCode"));
    }

    @Test
    public void findEmployeeByIdTest() throws Exception{
        String uri = "/employees/findEmployee/1";

        //Step-3: Use MockHttpServletRequestBuilder to create GET request.
        //The path variable value 1 should be passed to controller as empId.
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri);

        //Step-4: Define mocked service response for a valid employee id.
        //This tells Mockito what to return when controller asks service for employee id 1.
        EmployeeBean expectedEmployee = new EmployeeBean(1, "KK", 50000.00, 123);
        when(employeeService.findEmployeeById(1)).thenReturn(expectedEmployee);

        //Step-5: Perform request using MockMVC and collect MVC result.
        ResultActions rest = mockMvc.perform(request);
        MvcResult mvcResult = rest.andReturn();

        //Step-6: Extract response body and status.
        //Response body is JSON, so it is converted back into EmployeeBean for field assertions.
        String result = mvcResult.getResponse().getContentAsString();
        int actualStatus = mvcResult.getResponse().getStatus();

        EmployeeBean foundEmployee = JSONUtils.convertFromJsonToObject(result, EmployeeBean.class);

        //Step-7: Verify that controller delegates the id to service layer once.
        //This confirms the controller passed the path variable value correctly.
        verify(employeeService, times(1)).findEmployeeById(1);

        //For an existing employee, controller should return 200 OK and matching employee details.
        Assertions.assertEquals(HttpStatus.OK.value(), actualStatus);
        Assertions.assertNotNull(foundEmployee);
        Assertions.assertEquals(1, foundEmployee.getEmployeeId());
        Assertions.assertEquals("KK", foundEmployee.getEmployeeName());
        Assertions.assertEquals(50000.00, foundEmployee.getSalary());
        Assertions.assertEquals(123, foundEmployee.getDepartmentCode());
    }

    //Negative test: checks controller behaviour when service does not find an employee.
    @Test
    public void findEmployeeByIdInvalidTest() throws Exception{
        String uri = "/employees/findEmployee/-1";

        //Step-3: Use MockHttpServletRequestBuilder to create GET request with invalid employee id.
        //Here -1 is used as a value that should not exist.
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri);

        //Step-4: Define mocked service response as null because employee is not found.
        //The controller checks for null and should convert it into HTTP 404.
        when(employeeService.findEmployeeById(-1)).thenReturn(null);

        //Step-5: Perform request using MockMVC.
        ResultActions rest = mockMvc.perform(request);
        MvcResult mvcResult = rest.andReturn();

        int actualStatus = mvcResult.getResponse().getStatus();

        //Step-6: Verify that controller delegates the invalid id to service layer once.
        verify(employeeService, times(1)).findEmployeeById(-1);

        //Step-7: If employee is not found, controller should return 404 NOT_FOUND.
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), actualStatus);
    }

    private List<EmployeeBean> findAllEmployeesStubData() {
        //This method provides fixed test data for findAllEmployeesTest().
        //Keeping stub data in one method makes the test method easier to read.
        return Arrays.asList(
                new EmployeeBean(1, "KK1", 100119716.0, 101),
                new EmployeeBean(2, "KK2", 100119717.0, 102),
                new EmployeeBean(3, "KK3", 100119718.0, 103),
                new EmployeeBean(4, "KK4", 100119719.0, 104)
        );
    }

}
