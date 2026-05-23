package com.microservices.learn.controller;

import com.microservices.learn.C02D06SpringMicroservices21SpringBootEmployeeServiceIntegrationTestingOfControllerLayer;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = C02D06SpringMicroservices21SpringBootEmployeeServiceIntegrationTestingOfControllerLayer.class)
@WebAppConfiguration
@Transactional
public class UpdateEmployeeIntegrationTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @BeforeEach
    public void mySetup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void updateEmployeeTest() throws Exception{
        //Arrange: first add an employee, because update needs an existing employee id.
        EmployeeBean employeeBean = new EmployeeBean();
        employeeBean.setEmployeeName("KK");
        employeeBean.setSalary(50000.00);
        employeeBean.setDepartmentCode(123);

        String addJsonInput = JSONUtils.convertFromObjectToJson(employeeBean);

        MockHttpServletRequestBuilder addRequest =
                MockMvcRequestBuilders.post("/employees/addEmployee")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(addJsonInput);

        MvcResult addMvcResult = mockMvc.perform(addRequest).andReturn();
        String addResult = addMvcResult.getResponse().getContentAsString();
        EmployeeBean savedEmployee = JSONUtils.convertFromJsonToObject(addResult, EmployeeBean.class);

        //Arrange: update the saved employee data and convert it into JSON request body.
        savedEmployee.setEmployeeName("KK Updated");
        savedEmployee.setSalary(60000.00);
        savedEmployee.setDepartmentCode(456);

        String updateJsonInput = JSONUtils.convertFromObjectToJson(savedEmployee);

        MockHttpServletRequestBuilder updateRequest =
                MockMvcRequestBuilders.put("/employees/updateEmployee")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJsonInput);

        //Act: call the update endpoint.
        ResultActions rest = mockMvc.perform(updateRequest);
        MvcResult mvcResult = rest.andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        int actualStatus = mvcResult.getResponse().getStatus();

        EmployeeBean updatedEmployee = JSONUtils.convertFromJsonToObject(result, EmployeeBean.class);

        //Assert: controller should return 200 OK and updated employee details.
        Assertions.assertEquals(HttpStatus.OK.value(), actualStatus);
        Assertions.assertNotNull(updatedEmployee);
        Assertions.assertEquals(savedEmployee.getEmployeeId(), updatedEmployee.getEmployeeId());
        Assertions.assertEquals("KK Updated", updatedEmployee.getEmployeeName());
        Assertions.assertEquals(456, updatedEmployee.getDepartmentCode());
        Assertions.assertEquals(60000.00, updatedEmployee.getSalary());
    }

    @Test
    public void updateEmployeeInvalidTest() throws Exception{
        //Arrange: use an invalid employee id which should not exist in the DB.
        EmployeeBean employeeBean = new EmployeeBean();
        employeeBean.setEmployeeId(-1);
        employeeBean.setEmployeeName("Invalid Employee");
        employeeBean.setSalary(60000.00);
        employeeBean.setDepartmentCode(456);

        String jsonInput = JSONUtils.convertFromObjectToJson(employeeBean);

        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.put("/employees/updateEmployee")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput);

        //Act: call the update endpoint with an invalid employee id.
        ResultActions rest = mockMvc.perform(request);
        MvcResult mvcResult = rest.andReturn();

        int actualStatus = mvcResult.getResponse().getStatus();

        //Assert: controller should return 404 NOT_FOUND.
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), actualStatus);
    }

}
