/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employee.hierarchy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author sameer
 */
public class EmployeeTree {

    static String space = "";
    static final String EMPTY = " ";
    List<Employee> employees;
    List<Employee> inValidEmployees;
    Set<Employee> employeesWithoutManager;
    Employee ceo;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getInValidEmployees() {
        return inValidEmployees;
    }

    public void setInValidEmployees(List<Employee> inValidEmployees) {
        this.inValidEmployees = inValidEmployees;
    }

    public Set<Employee> getEmployeesWithoutManager() {
        return employeesWithoutManager;
    }

    public void setEmployeesWithoutManager(Set<Employee> employeesWithoutManager) {
        this.employeesWithoutManager = employeesWithoutManager;
    }

    public Employee getCeo() {
        return ceo;
    }

    public void setCeo(Employee ceo) {
        this.ceo = ceo;
    }

    EmployeeTree() {
        employees = new ArrayList<>();
        inValidEmployees = new ArrayList<>();
        employeesWithoutManager = new HashSet<>();
    }

    List<Employee> findEmployeesByManagerId(Integer managerId) {
        List<Employee> empList = new ArrayList<>();
        employees.stream().filter((emp) -> (Objects.equals(emp.getManagerId(), managerId))).forEachOrdered((emp) -> {
            empList.add(emp);
        });
        return empList;
    }
    // to find employees without managers   

    void findManagerByManagerId(Integer managerId) {
        List<Employee> empList = new ArrayList<>();
        employees.stream().filter((emp) -> (Objects.equals(emp.getEmpId(), managerId))).forEachOrdered((emp) -> {
           
            empList.add(emp);
        });
        if (empList.isEmpty()) {
            //System.out.println( managerId);
            employeesWithoutManager.addAll(employees.stream()
                                                    .filter(temp -> Objects.equals(temp.getManagerId(), managerId))
                                                    .collect(Collectors.toList())
                                           );
        }
      
    }

    //Actual formation of tree
    void formTree(Employee employee) {
        List<Employee> juniors = findEmployeesByManagerId(employee.getEmpId());
        if (juniors == null || juniors.isEmpty()) {

            return;
        } else {
            employee.setJuniors(juniors);
            juniors.forEach((junior) -> {
                formTree(junior);
            });
        }

    }

    void printTree(Employee employee) {
        System.out.println(EmployeeTree.space + employee.getName());

        List<Employee> juniors = employee.getJuniors();
        if (juniors == null || juniors.isEmpty()) {
            return;
        } else {
            EmployeeTree.space += "\t";
            juniors.forEach((junior) -> {
                printTree(junior);
            });
            removeTabSpace();

        }

    }

    void removeTabSpace() {
        int lasti = EmployeeTree.space.lastIndexOf("\t");

        EmployeeTree.space = EmployeeTree.space.substring(0, lasti);
    }

    public static void main(String args[]) {
        
       
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter the absolute file path ");
        
        String path = scan.nextLine(); 
        
        EmployeeTree tree = new EmployeeTree();
        
        
       

        try ( Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(line -> tree.saveEmployeesToList(line));

        } catch (IOException e) {
            e.printStackTrace();
        }
        

        tree.formTree(tree.getCeo());
        tree.printTree(tree.getCeo());
        
        System.err.println("invalid employees");
        
        tree.getInValidEmployees().forEach(inValidEmp -> tree.formTree(inValidEmp));
        tree.getInValidEmployees().forEach(inValidEmp -> tree.printTree(inValidEmp));

        System.out.println("");
        System.err.println("Employees without manager");

        
        tree.getEmployees().forEach(emp -> tree.findManagerByManagerId(emp.getManagerId()));
       
        tree.getEmployeesWithoutManager().forEach(System.out::println);
    }

    //list of employees to process
    void saveEmployeesToList(String employeeInfoLine) {

        String[] employeeInfo = employeeInfoLine.split(EMPTY);
        Integer managerID = 0;
        Integer employeeID = Integer.parseInt(employeeInfo[1]);
        if (employeeInfo.length > 2 && employeeInfo[2] != null) {
            managerID = Integer.parseInt(employeeInfo[2]);
        }
        Employee newEmployee = new Employee(employeeInfo[0], employeeID, managerID);
        if (managerID == 0) {
            ceo = newEmployee;
        }
        if (employeeID > 0) {
            employees.add(newEmployee);
        } else {
            inValidEmployees.add(newEmployee);
        }

    }
}
