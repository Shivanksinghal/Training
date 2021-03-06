package com.thales.employee;

import java.time.LocalDate;

public class RegularEmployee extends Employee {
    private boolean isLocked = false;
    public int wrongPasswordCount = 0;

    public RegularEmployee(String firstName, String lastName, String department, String username, String password, String salt, LocalDate dob) {
        super(firstName, lastName, department, username, password, salt, dob);
        setEmployeeType(EmployeeType.Regular);
    }

    public EmployeeType getEmployeeType() {
        return EmployeeType.Regular;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
