package com.microservices.learn.bean;

public class EmployeeBean {

    private Integer employeeId;
    private String employeeName;
    private Double salary;
    private Integer departmentCode;

    public EmployeeBean(Integer employeeId, String employeeName, Double salary, Integer departmentCode) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.departmentCode = departmentCode;
    }

    public EmployeeBean() {
        super();
    }

    @Override
    public String toString() {
        return "EmployeeBean{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", salary=" + salary +
                ", departmentCode='" + departmentCode + '\'' +
                '}';
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

    public Integer getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Integer departmentCode) {
        this.departmentCode = departmentCode;
    }
}
