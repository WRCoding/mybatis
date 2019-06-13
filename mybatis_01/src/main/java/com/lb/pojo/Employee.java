package com.lb.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author LB
 * @create 2019-06-12 7:50
 */
public class Employee implements Serializable {
    private double salary;
    private User user;
    private List<Integer> idList;

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }


    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
