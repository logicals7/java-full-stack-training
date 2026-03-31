package com.microservices.learn.bean;

//POJO class: acts as a DTO for employee details
public class EmployeeBean {
    private Integer empId;
    private String empName;
    private Double salary;
    private Integer deptCode;


    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(Integer deptCode) {
        this.deptCode = deptCode;
    }

    @Override
    public String toString() {
        return "EmployeeBean{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", salary=" + salary +
                ", deptCode=" + deptCode +
                '}';
    }
}
