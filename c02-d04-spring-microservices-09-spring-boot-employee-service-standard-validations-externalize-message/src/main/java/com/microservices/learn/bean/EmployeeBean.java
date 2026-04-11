package com.microservices.learn.bean;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeBean {

    private Integer employeeId;

    //Changes for externalizing message for validations

    //@NotEmpty(message = "employeeName can't be empty!!")
    @NotEmpty(message = "{NotEmpty.employeeBean.employeeName}")
    //@Size(min = 5, max = 20, message = "size must be bw 5 to 20 chars")
    @Size(min = 5, max = 20, message = "{Size.employeeBean.employeeName}")
    private String employeeName;

    //@NotNull(message = "Salary can't be null")
    //@Range(min = 30000, max=1000000, message = "salary must be bw 30k & 1000k")
    @NotNull(message = "{NotNull.employeeBean.salary}")
    @Range(min = 30000, max=1000000, message = "{Range.employeeBean.salary}")
    private Double salary;
    private String departmentCode;

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

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
