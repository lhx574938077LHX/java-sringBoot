package com.lhx.dao.entity;

import java.util.Date;

public class WorkersInfo {
    private Integer id;

    private String workername;

    private Integer sex;

    private Integer salary;

    private String email;

    private Date employeddates;

    private String department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkername() {
        return workername;
    }

    public void setWorkername(String workername) {
        this.workername = workername == null ? null : workername.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getEmployeddates() {
        return employeddates;
    }

    public void setEmployeddates(Date employeddates) {
        this.employeddates = employeddates;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }
}