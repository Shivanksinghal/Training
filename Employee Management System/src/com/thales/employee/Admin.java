package com.thales.employee;

import com.thales.MainScreen;

import java.time.LocalDate;
import java.util.Map;

public class Admin extends Employee{

    public Admin(String firstName, String lastName, String department, String username, String password, String salt, LocalDate dob) {
        super(firstName, lastName, department, username, password, salt, dob);
        setEmployeeType(EmployeeType.Admin);
    }

    public void viewAllUsers() {
        if(isLoggedIn()) {
            Map<String, Employee> employees = MainScreen.employeeMap;
            System.out.println("\n\n\t\t\t\t\t\t\t\t\t List of all Employees\n");
            System.out.format("%9s%12s%14s%14s%30s%30s\n", "User Type", "Username", "First Name", "Last Name", "Date of Birth (yyyy/mm/dd)", "Password");
            for (int i = 0; i < 130; ++i) System.out.print("-");
            System.out.println();
            for (Map.Entry entry : employees.entrySet()) {
                String username = (String) entry.getKey();
                Employee employee = (Employee) entry.getValue();

                System.out.format("%9s%12s%14s%14s%20s%60s\n", employee.getEmployeeType(),
                        username,
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getDateOfBirth(),
                        employee.getPassword());
            }
        } else {
            System.out.println("You need to Log in first");
        }
    }

    public void unlockEmployee(String username) {
        if(isLoggedIn()) {
            Employee employee = MainScreen.employeeMap.get(username);
            if (employee == null || employee.getEmployeeType() == EmployeeType.Admin) {
                System.out.println("You can't unlock this user");
                return;
            }
            RegularEmployee regularEmployee = (RegularEmployee) employee;
            if (regularEmployee.isLocked() == true) {
                regularEmployee.setLocked(false);
                regularEmployee.wrongPasswordCount = 0;
                System.out.println("Employee Unlocked Successfully");
            } else if (!regularEmployee.isLocked()) {
                System.out.println("User is already unlocked");
            }
        } else {
            System.out.println("You need to log in first");
        }
    }

    public void deleteEmployee(String username) {
        if(isLoggedIn()) {
            Employee employee = MainScreen.employeeMap.getOrDefault(username, null);
            if (employee == null) {
                System.out.println("Employee doesn't exist with that username");
                return;
            } else if (employee.getEmployeeType() == EmployeeType.Regular) {
                MainScreen.employeeMap.remove(username);
                System.out.println("Employee deleted Successfully");
            } else {
                System.out.println("You don't have permission to delete an admin.");
            }
        } else {
            System.out.println("You need to log in first");
        }
    }

}
