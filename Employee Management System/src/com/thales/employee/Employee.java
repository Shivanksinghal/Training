package com.thales.employee;

import com.thales.MainScreen;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String department;
    private LocalDate dateOfBirth;
    private boolean isLoggedIn = false;
    private EmployeeType employeeType;
    private String salt;

    public Employee(String firstName, String lastName, String department, String username, String password, String salt, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.dateOfBirth = dob;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() { return password; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() { return lastName; }

    public String getDepartment() {
        return department;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSalt() { return salt; }

    public EmployeeType getEmployeeType() { return employeeType; }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public void setEmployeeType(EmployeeType employeeType) { this.employeeType = employeeType; }


    public void viewDetails() {
        if(isLoggedIn) {
            System.out.println("\n\tUser Details");
            System.out.println("-------------------------");
            System.out.println("User Type:     " + employeeType);
            System.out.println("Username:      " + username);
            System.out.println("First Name:    " + firstName);
            System.out.println("Last Name:     " + lastName);
            System.out.println("Department:    " + department);
            System.out.println("Date of Birth: " + dateOfBirth);
            System.out.println("Password:      " + password);
        } else {
            System.out.println("You need to Log in first.");
        }
    }

    public void updateDetails() {
        if(isLoggedIn) {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nPlease update the following details");

            String nameRegex = "[A-Z][a-z]*";
            Pattern pattern = Pattern.compile(nameRegex);

            System.out.print("First Name: ");
            String firstName = sc.next();
            Matcher matcher = pattern.matcher(firstName);
            if (!matcher.matches())
                throw new IllegalArgumentException("First Name not accepted");
            setFirstName(firstName);

            System.out.print("Last Name: ");
            String lastName = sc.next();
            matcher = pattern.matcher(lastName);
            if (!matcher.matches())
                throw new IllegalArgumentException("Last Name not accepted");
            setLastName(MainScreen.sc.next());

            System.out.print("Department: ");
            String department = sc.next();
            setDepartment(department);

            MainScreen.employeeMap.put(this.username, this);
        } else {
            System.out.println("You need to Log in first.");
        }
    }

    public void signOut() { setLoggedIn(false); }

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + getUsername() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", designation='" + getDepartment() + '\'' +
                ", dateOfBirth=" + getDateOfBirth() +
                "}\n";
    }
}
