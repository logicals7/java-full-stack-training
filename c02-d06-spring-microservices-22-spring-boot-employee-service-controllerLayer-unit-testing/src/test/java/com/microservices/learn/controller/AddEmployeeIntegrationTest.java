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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = C02D06SpringMicroservices22SpringBootEmployeeServiceUnitTestingOfControllerLayer.class)
@WebAppConfiguration
@Transactional
public class AddEmployeeIntegrationTest {

    @Autowired
    WebApplicationContext webApplicationContext; //cached

    protected MockMvc mockMvc;

    @BeforeEach
    public void mySetup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addEmployeeTest() throws Exception{
        //Arrange
        String uri = "/employees/addEmployee";

        EmployeeBean employeeBean = new EmployeeBean();
        employeeBean.setEmployeeName("KK");
        employeeBean.setSalary(56789056.0);
        employeeBean.setDepartmentCode(1234);

        //converting the object to RequestBody. Request Body is always JSON in POST methods.
        String jsonInput = JSONUtils.convertFromObjectToJson(employeeBean);

        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post(uri)
                        //ResponseContentType: what test case / test client expects in return
                        .accept(MediaType.APPLICATION_JSON)
                        //Data type of the data being sent to server
                        .contentType(MediaType.APPLICATION_JSON)
                        //Data what is sent to server as RequestBody
                        .content(jsonInput);

        //Act
        MvcResult mvcResult = mockMvc.perform(request).andReturn();

        //Above line could be written as:
        //ResultActions rest = mockMvc.perform(request);
        //MvcResult mvcResult = rest.andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        int actualStatus = mvcResult.getResponse().getStatus();

        EmployeeBean savedEmployee = JSONUtils.convertFromJsonToObject(result, EmployeeBean.class);

        //Assert
        Assertions.assertEquals(HttpStatus.CREATED.value(), actualStatus);
        Assertions.assertNotNull(savedEmployee);
        Assertions.assertTrue(savedEmployee.getEmployeeId() > 0);
        Assertions.assertEquals("KK", savedEmployee.getEmployeeName());
        Assertions.assertEquals(1234, savedEmployee.getDepartmentCode());
        Assertions.assertEquals(56789056.0, savedEmployee.getSalary());

    }

}
