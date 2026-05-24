package com.microservices.learn.service;

import com.microservices.learn.C02D06SpringMicroservices22SpringBootEmployeeServiceUnitTestingOfControllerLayer;
import com.microservices.learn.bean.EmployeeBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
This annotation means Spring may be used to run the test.
It is used to integrate the Spring TestContext Framework into JUnit 5’s
*/
@ExtendWith(SpringExtension.class)

/*
Annotate the class with @SpringBootTest(classes = {C02D06SpringMicroservices20SpringBootEmployeeServiceTestingServiceLayerApplication.class})
We could test the Java app using JUnit5. And for that, we used to have @ContextConfiguration(classes = C02D06SpringMicroservices20SpringBootEmployeeServiceTestingServiceLayerApplication.class). So, we used to annotate our class with the same.
This annotation is used to specify the path of the xml file or path of the Java config file/primary config file whichever is used for configuration.
It helps to start or load the context.
Now we are using @SpringBootTest annotation instead of @ContextConfiguration on the class C02D06SpringMicroservices20SpringBootEmployeeServiceTestingServiceLayerApplication.class which is the primary configuration class.

So, while writing @SpringBootTest we are using C02D06SpringMicroservices20SpringBootEmployeeServiceTestingServiceLayerApplication.class
This means that this class will act as a config class to load the context.
The imp thing is that - now the context will be cached for all test cases because of this annotation. All the test cases will have the same context.
Recap: It is used to point to classes that contain the config for the Spring application context. By specifying class = C02D06SpringMicroservices20SpringBootEmployeeServiceTestingServiceLayerApplication.class, we are telling spring to use the configuration defined in the C02D06SpringMicroservices20SpringBootEmployeeServiceTestingServiceLayerApplication class to set up the application context for our tests. This allows us to load the necessary beans and configurations required for our test cases, ensuring that they run in an environment that closely resembles your actual application.
 */
@SpringBootTest(classes = C02D06SpringMicroservices22SpringBootEmployeeServiceUnitTestingOfControllerLayer.class)

/*
@Transactional annotation we will use to test the service layer.
To check the addEmployee() function is working or not, what we will do?
Eventually, we will call the addEmployee() method in service layer, which will call employeeDAOWrapper’s addEmployee() method.
But we are testing. We don’t wanna save our testing data in the DB.
So, it will check the functionality. But after insertion, whatever operation you are doing during testing, it should be rolled back.
In order to roll that back, we are using @Transactional annotation.

Recap:
So the operation will be carried out > DB will exist > Modification will be done.
But once the testing is done, all the operations which you performed during testing will be rolled back.
And this is being done using @Transactional annotation.
 */
@Transactional

public class EmployeeServiceImplTest {

    /*
    So from EmployeeServiceTest.class, we will need to call the EmployeeService’s addEmployee() method
    to hit the service layer. So, we need to @Autowire it.
     */
    @Autowired
    EmployeeService employeeService;

    /*
    Now we need to create certain test methods:
    Lets create one method for testing addEmployee() functionality as: testAddEmployee()
    Add @Test annotation to mark this method as test a case.
     */
    @Test
    public void testAddEmployee(){
        // To test what I will do?
        //    I will pass some data to the addEmployee() method & I will check that data.
        //    To pass the data, we will need to create one bean of type EmployeeBean & set some values on this bean.
        //    Suppose we will set the 3 values here.
        EmployeeBean employeeBean = new EmployeeBean();
        employeeBean.setEmployeeName("KK");
        employeeBean.setDepartmentCode(123);
        employeeBean.setSalary(50000.00);

        //Now I have employeeService to pass this bean in addEmployee() method.
        // It is going to return me EmployeeBean eventually, so we will assign it to a variable of
        // type EmployeeBean.
        EmployeeBean employeeBean1 = employeeService.addEmployee(employeeBean);

        //Now what I have to check? I have to compare and check both employeeBeans.
        //To check we have Assertions and Assumptions.

        //First check we can put is - the bean we have received from service layer is not null. If it is null, then the test case must have failed. Because we set the values on the employeeBean in our test.
        Assertions.assertNotNull(employeeBean1);
        //Second check we can put is - employeeId we have received must be greater than 2.
        Assertions.assertTrue(employeeBean1.getEmployeeId() > 2);
        //Third check can be - The name should be “John Doe”, which we set initially.
        Assertions.assertEquals("KK", employeeBean1.getEmployeeName());
        //Fourth check can be - The salary should be equal to whatever we have set initially.
        Assertions.assertEquals(50000.00, employeeBean1.getSalary());
    }



    /*
    your test should check:
    1. an employee exists in DB
    2. you call employeeService.findEmployeeById(id)
    3. the returned employee is not null
    4. the returned fields match the employee you saved

    Do not use a random hardcoded id like 123, because that employee may not exist. First create an employee, get its generated id, then search using that id.
    Use this in EmployeeServiceImplTest:
     */
    @Test
    public void testFindEmployeeById(){
        // Arrange: create and save employee first
         EmployeeBean employeeBean = new EmployeeBean();
         employeeBean.setEmployeeName("KK");
         employeeBean.setDepartmentCode(123);
         employeeBean.setSalary(50000.00);

         EmployeeBean savedEmployee = employeeService.addEmployee(employeeBean);

        // Act: find employee by generated id
        EmployeeBean foundEmployee = employeeService.findEmployeeById(savedEmployee.getEmployeeId());

        // Assert: verify result
        Assertions.assertNotNull(foundEmployee);
        Assertions.assertEquals(savedEmployee.getEmployeeId(), foundEmployee.getEmployeeId());
        Assertions.assertEquals("KK", foundEmployee.getEmployeeName());
        Assertions.assertEquals(123, foundEmployee.getDepartmentCode());
        Assertions.assertEquals(50000.00, foundEmployee.getSalary());
    }

    @Test
    public void testFindAllEmployees(){

        //Arrange
        List<EmployeeBean> savedEmployeeBeanList = new ArrayList<EmployeeBean>();

        EmployeeBean employeeBean1 = new EmployeeBean();
        employeeBean1.setEmployeeName("KK");
        employeeBean1.setDepartmentCode(123);
        employeeBean1.setSalary(50000.00);

        EmployeeBean employeeBean2 = new EmployeeBean();
        employeeBean2.setEmployeeName("KKK");
        employeeBean2.setDepartmentCode(1223);
        employeeBean2.setSalary(60000.00);

        savedEmployeeBeanList.add(employeeBean1);
        savedEmployeeBeanList.add(employeeBean2);

        employeeService.addEmployee(employeeBean1);
        employeeService.addEmployee(employeeBean2);

        //Act
        List<EmployeeBean> foundEmployeeBeanList = employeeService.findAllEmployees();

        //Assert
        Assertions.assertNotNull(foundEmployeeBeanList);
        Assertions.assertTrue(foundEmployeeBeanList.size() >= 2);
    }

    @Test
    public void testFindEmployeeByIdInvalid(){
        //Arrange: use an invalid employee id which should not exist in the DB.
        Integer invalidEmployeeId = -1;

        //Act
        EmployeeBean foundEmployee = employeeService.findEmployeeById(invalidEmployeeId);

        //Assert
        Assertions.assertNull(foundEmployee);
    }

    @Test
    public void testUpdateEmployee(){
        //Arrange: create and save employee first, because update needs an existing employee id.
        EmployeeBean employeeBean = new EmployeeBean();
        employeeBean.setEmployeeName("KK");
        employeeBean.setDepartmentCode(123);
        employeeBean.setSalary(50000.00);

        EmployeeBean savedEmployee = employeeService.addEmployee(employeeBean);

        //Arrange: change values on the saved employee.
        savedEmployee.setEmployeeName("KK Updated");
        savedEmployee.setDepartmentCode(456);
        savedEmployee.setSalary(60000.00);

        //Act
        EmployeeBean updatedEmployee = employeeService.updateEmployee(savedEmployee);

        //Assert
        Assertions.assertNotNull(updatedEmployee);
        Assertions.assertEquals(savedEmployee.getEmployeeId(), updatedEmployee.getEmployeeId());
        Assertions.assertEquals("KK Updated", updatedEmployee.getEmployeeName());
        Assertions.assertEquals(456, updatedEmployee.getDepartmentCode());
        Assertions.assertEquals(60000.00, updatedEmployee.getSalary());
    }

    @Test
    public void testUpdateEmployeeInvalid(){
        //Arrange: use an invalid employee id which should not exist in the DB.
        EmployeeBean employeeBean = new EmployeeBean();
        employeeBean.setEmployeeId(-1);
        employeeBean.setEmployeeName("Invalid Employee");
        employeeBean.setDepartmentCode(456);
        employeeBean.setSalary(60000.00);

        //Act
        EmployeeBean updatedEmployee = employeeService.updateEmployee(employeeBean);

        //Assert
        Assertions.assertNull(updatedEmployee);
    }

    @Test
    public void testDeleteEmployee(){
        //Arrange: create and save employee first, because delete needs an existing employee id.
        EmployeeBean employeeBean = new EmployeeBean();
        employeeBean.setEmployeeName("KK");
        employeeBean.setDepartmentCode(123);
        employeeBean.setSalary(50000.00);

        EmployeeBean savedEmployee = employeeService.addEmployee(employeeBean);

        //Act
        EmployeeBean deletedEmployee = employeeService.deleteEmployee(savedEmployee.getEmployeeId());

        //Assert
        Assertions.assertNotNull(deletedEmployee);
        Assertions.assertEquals(savedEmployee.getEmployeeId(), deletedEmployee.getEmployeeId());
        Assertions.assertEquals("KK", deletedEmployee.getEmployeeName());
        Assertions.assertEquals(123, deletedEmployee.getDepartmentCode());
        Assertions.assertEquals(50000.00, deletedEmployee.getSalary());
    }

    @Test
    public void testDeleteEmployeeInvalid(){
        //Arrange: use an invalid employee id which should not exist in the DB.
        Integer invalidEmployeeId = -1;

        //Act
        EmployeeBean deletedEmployee = employeeService.deleteEmployee(invalidEmployeeId);

        //Assert
        Assertions.assertNull(deletedEmployee);
    }


}
