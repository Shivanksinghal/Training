package com.thales.authentication;

import com.thales.MainScreen;
import com.thales.employee.Admin;
import com.thales.employee.Employee;
import com.thales.employee.EmployeeType;
import com.thales.encryption.PasswordUtils;
import com.thales.exceptions.BadPasswordException;
import com.thales.exceptions.BadUsernameException;
import com.thales.exceptions.WrongPasswordException;
import com.thales.exceptions.WrongUsernameException;

import java.time.LocalDate;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminAuthenticator implements Authenticator{
    @Override
    public Admin authenticate(String username, String password) throws WrongUsernameException, WrongPasswordException {
        Employee employee = MainScreen.employeeMap.get(username);
        if(employee == null || employee.getEmployeeType() == EmployeeType.Regular) {
            System.out.println("Admin doesn't exist");
            throw new WrongUsernameException("Wrong username or password");
        }
        Admin admin = (Admin) employee;
        if(PasswordUtils.verifyUserPassword(password, admin.getPassword(), admin.getSalt())) {
            employee.setLoggedIn(true);
            System.out.println("Admin logged in successfully ...");
        } else {
            System.out.println("Wrong username or password");
            throw new WrongPasswordException("Wrong username or Password");
        }
        return admin;
    }

    @Override
    public void register(String firstName, String lastName, String department, String username, String password, LocalDate dob) throws BadUsernameException, BadPasswordException {
        if(MainScreen.employeeMap.get(username) != null) {
            System.out.println("User already exist");
            return;
        }

        String usernameRegex = "^[A-Za-z]\\w{5,29}$";
        Pattern usernamePattern = Pattern.compile(usernameRegex);
        Matcher usernameMatcher = usernamePattern.matcher(password);
        if(!usernameMatcher.matches())
            throw new BadUsernameException("Username is not in proper format ...");


        String passwordRegex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if(!passwordMatcher.matches())
            throw new BadPasswordException("Password must be strong ...");


        String salt = PasswordUtils.getSalt(30);
        password = PasswordUtils.generateSecurePassword(password, salt);
        Employee employee = new Admin(firstName, lastName, department, username, password, salt, dob);
        Map<String, Employee> map = MainScreen.employeeMap;
        map.put(username, employee);
        System.out.println("User successfully registered as admin.");
    }
}
