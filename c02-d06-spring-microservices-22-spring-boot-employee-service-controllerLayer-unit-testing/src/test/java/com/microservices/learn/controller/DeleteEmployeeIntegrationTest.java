package com.microservices.learn.controller;

import com.microservices.learn.C02D06SpringMicroservices22SpringBootEmployeeServiceUnitTestingOfControllerLayer;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = C02D06SpringMicroservices22SpringBootEmployeeServiceUnitTestingOfControllerLayer.class)
@WebAppConfiguration
@Transactional
public class DeleteEmployeeIntegrationTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @BeforeEach
    public void mySetup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void deleteEmployeeTest() throws Exception{
        //Arrange: first add an employee, because delete needs an existing employee id.
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

        String uri = "/employees/deleteEmployee/" + savedEmployee.getEmployeeId();

        MockHttpServletRequestBuilder deleteRequest = MockMvcRequestBuilders.delete(uri);

        //Act: call the delete endpoint.
        ResultActions rest = mockMvc.perform(deleteRequest);
        MvcResult mvcResult = rest.andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        int actualStatus = mvcResult.getResponse().getStatus();

        EmployeeBean deletedEmployee = JSONUtils.convertFromJsonToObject(result, EmployeeBean.class);

        //Assert: controller currently returns 302 FOUND after successful delete.
        Assertions.assertEquals(HttpStatus.FOUND.value(), actualStatus);
        Assertions.assertNotNull(deletedEmployee);
        Assertions.assertEquals(savedEmployee.getEmployeeId(), deletedEmployee.getEmployeeId());
        Assertions.assertEquals("KK", deletedEmployee.getEmployeeName());
        Assertions.assertEquals(123, deletedEmployee.getDepartmentCode());
        Assertions.assertEquals(50000.00, deletedEmployee.getSalary());
    }

    @Test
    public void deleteEmployeeInvalidTest() throws Exception{
        //Arrange: use an invalid employee id which should not exist in the DB.
        String uri = "/employees/deleteEmployee/-1";

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(uri);

        //Act: call the delete endpoint with an invalid employee id.
        ResultActions rest = mockMvc.perform(request);
        MvcResult mvcResult = rest.andReturn();

        int actualStatus = mvcResult.getResponse().getStatus();

        //Assert: controller should return 404 NOT_FOUND.
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), actualStatus);
    }

}
