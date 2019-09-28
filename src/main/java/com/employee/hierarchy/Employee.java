/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employee.hierarchy;

import java.util.List;

/**
 *
 * @author sameer
 */
public class Employee {
    
    private Integer empId;
    private String name;
    private Integer managerId;
    private List<Employee> juniors;
    Employee(){
        
    }

    @Override
    public String toString() {
        return "Employee{" + "name=" + name + '}';
    }
     
    Employee(String name , Integer empId, Integer managerId){
        this.empId = empId;
        this.name = name;
        this.managerId = managerId;
        
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public List<Employee> getJuniors() {
        return juniors;
    }

    public void setJuniors(List<Employee> juniors) {
        this.juniors = juniors;
    }
    
}
