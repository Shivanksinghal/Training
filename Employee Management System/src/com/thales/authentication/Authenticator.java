package com.thales.authentication;

import com.thales.employee.Employee;
import com.thales.exceptions.BadPasswordException;
import com.thales.exceptions.BadUsernameException;
import com.thales.exceptions.WrongPasswordException;
import com.thales.exceptions.WrongUsernameException;

import java.time.LocalDate;

public interface Authenticator {
    Employee authenticate(String username, String password) throws WrongUsernameException, WrongPasswordException;
    void register(String firstName, String lastName, String department,String username, String password, LocalDate dob) throws BadPasswordException, BadUsernameException;
}
