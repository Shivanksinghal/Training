package com.thales.authentication;

import com.thales.employee.Admin;
import com.thales.employee.RegularEmployee;
import com.thales.exceptions.BadPasswordException;
import com.thales.exceptions.BadUsernameException;
import com.thales.exceptions.WrongPasswordException;
import com.thales.exceptions.WrongUsernameException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    private Authenticator authenticator;
    private Scanner sc = new Scanner(System.in);

    public RegularEmployee loginAsRegular() {
        System.out.print("Username: ");
        String username = sc.next();

        System.out.print("Password: ");
        String password = sc.next();

        authenticator = new UserAuthenticator();
        RegularEmployee employee = null;
        try {
            employee = (RegularEmployee) authenticator.authenticate(username, password);
        } catch (WrongUsernameException | WrongPasswordException e) {
            throw new IllegalArgumentException(e);
        }
        return employee;
    }

    public Admin loginAsAdmin() {
        System.out.print("Username: ");
        String username = sc.next();

        System.out.print("Password: ");
        String password = sc.next();

        authenticator = new AdminAuthenticator();
        Admin employee = null;
        try {
            employee = (Admin) authenticator.authenticate(username, password);
        } catch (WrongUsernameException | WrongPasswordException e) {
            throw new IllegalArgumentException(e);
        }
        return employee;
    }

    public void register() {
        System.out.println("Register User ...");

        System.out.print("Select user type (Admin/Regular) : ");
        String userType = sc.next();
        if(!(userType.equals("Admin") || userType.equals("Regular"))) {
            System.out.println("User registration failed ...");
            throw new IllegalArgumentException("Invalid User Type");
        }

        String nameRegex = "[A-Z][a-z]*";
        Pattern pattern = Pattern.compile(nameRegex);

        System.out.print("First Name: ");
        String firstName = sc.next();
        Matcher matcher = pattern.matcher(firstName);
        if(!matcher.matches())
            throw new IllegalArgumentException("Invalid first name");

        System.out.print("last Name: ");
        String lastName = sc.next();
        matcher = pattern.matcher(lastName);
        if(!matcher.matches())
            throw new IllegalArgumentException("Invalid last name");

        System.out.print("Department: ");
        String department = sc.next();

        System.out.println("Date of birth: ");
        System.out.print("Day (dd): ");
        int day, month, year;
        try {
            day = sc.nextInt();
            System.out.print("Month (mm): ");
            month = sc.nextInt();
            System.out.print("Year (yyyy): ");
            year = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
            return;
        }
        LocalDate dob;
        try {
            dob = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            System.out.println("Invalid Date of birth");
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("Username: ");
        String username = sc.next();

        System.out.print("password: ");
        String password = sc.next();

        if(userType.equals("Regular")) {
            authenticator = new UserAuthenticator();
        } else {
            authenticator = new AdminAuthenticator();
        }
        try {
            authenticator.register(firstName, lastName, department, username, password, dob);
        } catch (BadPasswordException | BadUsernameException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
