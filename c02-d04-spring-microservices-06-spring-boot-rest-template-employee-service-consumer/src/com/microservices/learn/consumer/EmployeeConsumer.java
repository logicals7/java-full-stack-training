package com.microservices.learn.consumer;

import com.microservices.learn.bean.EmployeeBean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//I want to bootstrap my app from this class
//So we will define it as main class
public class EmployeeConsumer {

    //Need to get the url first on which the microservices are running
    private static final String BASE_URL = "http://localhost:8080/employees/";

    public static void main(String[] args) {
        addEmployee();
        findEmployeeById();
        findAllEmployees();
        updateEmployee();
        deleteEmployee();
    }



    private static void addEmployee() {
        //first we need to get a RestTemplate object
        RestTemplate restTemplate = new RestTemplate();
        //For post method we will be passing an EmployeeBean. So lets create a bean
        EmployeeBean employeeBean = new EmployeeBean();
        //set values for employeeBean
        employeeBean.setEmployeeName("John Doe");
        employeeBean.setSalary(234567.00);
        employeeBean.setDepartmentCode(101);

        //use rest template
        String response = restTemplate.postForObject(BASE_URL + "addEmployee", employeeBean, String.class);
        System.out.println(response);
    }

    private static void findEmployeeById() {
        RestTemplate restTemplate = new RestTemplate();
        //response type in controller is employeeBean, so we are passing EmployeeBean.class
        EmployeeBean employeeBean = restTemplate.getForObject(BASE_URL + "findEmployee/7", EmployeeBean.class);
        if (employeeBean != null){
            System.out.println("Employee Id: " + employeeBean.getEmployeeId());
            System.out.println("Employee Name: " + employeeBean.getEmployeeName());
            System.out.println("Salary: " + employeeBean.getSalary());
            System.out.println("deptCode: " + employeeBean.getDepartmentCode());
        } else{
            System.out.println("Employee Not Found with id: " + 2);
        }
    }


    private static void findAllEmployees() {
        RestTemplate restTemplate = new RestTemplate();
        List list = restTemplate.getForObject(BASE_URL + "findAllEmployees", List.class);
        System.out.println(list);
    }

    private static void updateEmployee() {
        RestTemplate restTemplate = new RestTemplate();

        EmployeeBean employeeBean = new EmployeeBean();
        employeeBean.setEmployeeId(7);
        employeeBean.setEmployeeName("Nands");
        employeeBean.setSalary(45098763.00);
        employeeBean.setDepartmentCode(202);

        restTemplate.put(BASE_URL + "updateEmployee", employeeBean);
        System.out.println("Employee Record updated successfully!!");
    }


    private static void deleteEmployee() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(BASE_URL + "deleteEmployee/5");
        System.out.println("Employee record deleted successfully!!");
    }
}
