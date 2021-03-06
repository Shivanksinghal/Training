package com.thales;

import com.thales.authentication.Client;
import com.thales.employee.Admin;
import com.thales.employee.Employee;
import com.thales.employee.RegularEmployee;
import com.thales.encryption.PasswordUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainScreen {
    public static Map<String, Employee> employeeMap = new HashMap<>();
    public final static Scanner sc = new Scanner(System.in);


    public static void seedDatabase() {
        String salt = PasswordUtils.getSalt(30);
        Employee e1 = new Admin("Shivank", "Singhal", "DES", "shivank",
                PasswordUtils.generateSecurePassword("shivank", salt), salt, LocalDate.of(2000, 01, 17));

        Employee e2 = new Admin("Sagar", "Tiwari", "DES", "sagar",
                PasswordUtils.generateSecurePassword("sagar", salt), salt, LocalDate.of(2000, 01, 17));

        Employee e3 = new RegularEmployee("Bhumika", "Rupera", "DES", "bhumika",
                PasswordUtils.generateSecurePassword("bhumika", salt), salt, LocalDate.of(2000, 01, 17));

        Employee e4 = new RegularEmployee("Aditi", "Agarwal", "DES", "aditi",
                PasswordUtils.generateSecurePassword("aditi", salt), salt, LocalDate.of(2000, 01, 17));

        employeeMap.put("shivank", e1);
        employeeMap.put("sagar", e2);
        employeeMap.put("bhumika", e3);
        employeeMap.put("aditi", e4);
    }


    public static char showScreen() {
        System.out.println("\n\nWelcome to Employee Management System");
        System.out.println("A. Login as Admin");
        System.out.println("B. Login as Regular User");
        System.out.println("C. Register");
        System.out.println("D. Exit");
        System.out.print("\nChoose an option: ");
        String choice = sc.next();
        if(choice == null || choice.length() > 1) {
            return 'x';
        }
        return choice.charAt(0);
    }

    public static char viewAdminConsole() {
        System.out.println("\n\nWelcome to Admin Console");
        System.out.println("Select option");
        System.out.println("1. View Details");
        System.out.println("2. Update Details");
        System.out.println("3. Unlock User");
        System.out.println("4. Delete User");
        System.out.println("5. View All Users");
        System.out.println("6. Sign out");
        System.out.print("\nChoose an option: ");

        String choice = sc.next();
        if(choice == null || choice.length() > 1) {
            return 'x';
        }
        return choice.charAt(0);
    }

    public static char viewRegularConsole() {
        System.out.println("\n\nWelcome to User Console");
        System.out.println("Select option");
        System.out.println("1. View Details");
        System.out.println("2. Update Details");
        System.out.println("3. Sign out");
        System.out.print("\nChoose an option: ");

        String choice = sc.next();
        if(choice == null || choice.length() > 1) {
            return 'x';
        }
        return choice.charAt(0);
    }

    public static void main(String[] args) {
        seedDatabase();

        while(true) {
            Employee employee = null;
            while (true) {
                char option = showScreen();

                if (option == 'A' || option == 'a') {

                    System.out.println("\nLogin as Admin");
                    Client client = new Client();
                    try {
                        employee = client.loginAsAdmin();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    if (employee != null) {
                        break;
                    }

                } else if (option == 'B' || option == 'b') {

                    System.out.println("\nLogin as Regular User");
                    Client client = new Client();
                    try {
                        employee = client.loginAsRegular();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    if (employee != null) {
                        break;
                    }

                } else if (option == 'C' || option == 'c') {

                    System.out.println("\nRegister");
                    Client client = new Client();
                    try {
                        client.register();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (option == 'D' || option == 'd') {

                    System.out.println("\nExiting ...");
                    return;

                } else {

                    System.out.println("\nInvalid input, please try again ...");

                }
            }
            if (employee != null) {
                if (employee instanceof RegularEmployee) {
                    while (true) {
                        char choice = viewRegularConsole();
                        if (choice == '1') {
                            employee.viewDetails();
                        } else if (choice == '2') {
                            try {
                                employee.updateDetails();
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        } else if (choice == '3') {
                            employee.signOut();
                            break;
                        } else {
                            System.out.println("=====Invalid Choice=====");
                        }
                    }
                } else {
                    Admin admin = (Admin) employee;
                    while (true) {
                        char choice = viewAdminConsole();
                        if (choice == '1') {
                            employee.viewDetails();
                        } else if (choice == '2') {
                            try {
                                employee.updateDetails();
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        } else if (choice == '3') {
                            System.out.print("Enter username: ");
                            String username = sc.next();
                            admin.unlockEmployee(username);
                        } else if (choice == '4') {
                            System.out.print("Enter username: ");
                            String username = sc.next();
                            admin.deleteEmployee(username);
                        } else if (choice == '5') {
                            admin.viewAllUsers();
                        } else if (choice == '6') {
                            employee.signOut();
                            break;
                        } else {
                            System.out.println("Invalid Choice");
                        }
                    }
                }
            }
        }

    }

}
