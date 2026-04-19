package com.microservices.learn.bean;

import com.microservices.learn.validation.EmployeeFieldValidationAnnotation;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Employee {

    /*
        Following steps will be required to create custom validators:
        Step-1: Create custom annotations.
        Step-2: Create the Validator class.
        Step-3: Link Validator with custom annotation.
        Step-4: User custom annotation created in step-1 with business objects.

     */

    //Step-4: USe the custom annoattion EmployeeFieldValidationAnnotation in the Employee Class
    @EmployeeFieldValidationAnnotation(message = "{EmployeeNameValidator.employee.employeeName}")
    @NotEmpty(message = "{NotEmpty.employee.employeeName}") //Cant use not null for string as it return ""
    private String employeeName;

    private Integer employeeId;

    @Range(min = 30000, max=1000000, message = "{Range.employee.salary}")
    private Double salary;

    @NotNull(message="{NotNull.employee.departmentCode}")
    private int departmentCode;

    public Employee() {super();}

    public Employee(String employeeName, Integer employeeId, Double salary, int departmentCode) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.salary = salary;
        this.departmentCode = departmentCode;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public int getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(int departmentCode) {
        this.departmentCode = departmentCode;
    }
}
