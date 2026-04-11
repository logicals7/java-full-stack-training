package com.microservices.learn.bean;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Employee {

    @NotEmpty(message = "{NotEmpty.employee.employeeName}")
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
