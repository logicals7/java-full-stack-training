package com.microservices.learn.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

//It will have the same properties as EmployeeBean in Producer-EmployeeService
//Because the consumer will consume those services only
public class EmployeeBean {
    @JsonProperty("employeeId")
    private Integer employeeId;
    @JsonProperty("employeeName")
    private String employeeName;
    private Double salary;
    @JsonProperty("departmentCode")
    private Integer departmentCode;

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

    public Integer getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Integer departmentCode) {
        this.departmentCode = departmentCode;
    }

    @Override
    public String toString() {
        return "EmployeeBean{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", salary=" + salary +
                ", departmentCode=" + departmentCode +
                '}';
    }
}
