package com.thales.authentication;

import com.thales.MainScreen;
import com.thales.employee.Employee;
import com.thales.employee.EmployeeType;
import com.thales.employee.RegularEmployee;
import com.thales.encryption.PasswordUtils;
import com.thales.exceptions.BadPasswordException;
import com.thales.exceptions.BadUsernameException;
import com.thales.exceptions.WrongPasswordException;
import com.thales.exceptions.WrongUsernameException;

import java.time.LocalDate;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAuthenticator implements Authenticator{
    @Override
    public RegularEmployee authenticate(String username, String password) throws WrongUsernameException, WrongPasswordException {
        Employee employee = MainScreen.employeeMap.getOrDefault(username, null);

        if(employee == null || employee.getEmployeeType() == EmployeeType.Admin) {
            System.out.println("Employee doesn't exist");
            throw new WrongUsernameException("Wrong Username or Password");
        }

        RegularEmployee regularEmployee = (RegularEmployee) employee;

        if(regularEmployee.isLocked()) {
            System.out.println("User Account is locked");
            throw new WrongUsernameException("Wrong Username or Password");
        } else if(PasswordUtils.verifyUserPassword(password, regularEmployee.getPassword(), regularEmployee.getSalt())) {
            regularEmployee.setLoggedIn(true);
            regularEmployee.wrongPasswordCount = 0;
            System.out.println("User logged in successfully ...");
        } else {
            regularEmployee.wrongPasswordCount ++;
            if(regularEmployee.wrongPasswordCount >= 3) {
                regularEmployee.setLocked(true);
                System.out.println("Account locked due to 3 unsuccessful attempt");
            }
            throw new WrongPasswordException("Wrong Username or Password");
        }

        return regularEmployee;
    }

    @Override
    public void register(String firstName, String lastName, String department, String username, String password, LocalDate dob) throws BadPasswordException, BadUsernameException {
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
        Employee employee = new RegularEmployee(firstName, lastName, department, username, password, salt, dob);
        Map<String, Employee> map = MainScreen.employeeMap;
        map.put(username, employee);
        System.out.println("User successfully registered as regular user.");
    }
}
