/*
 * Name: Max Ramos
 * Date: May 9, 2026
 * Assignment: Phase Final Project - Employee Management Application
 * Purpose: Main application file that allows the user to manage employee records
 * through a console menu. This final version combines the project pieces from
 * previous weeks by using user interaction, object-oriented programming,
 * inheritance, abstraction, polymorphism, composition, and SQLite database CRUD
 * operations.
 */

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        final String databaseName = "employee_management.db";

        Scanner scanner = new Scanner(System.in);
        Connection conn = SQLiteDatabase.connect(databaseName);

        if (conn == null) {
            System.out.println("Could not connect to the database. Program ending.");
            return;
        }

        /*
         * The EmployeeDAO object handles all database operations.
         * This keeps the database code separate from the menu/user interaction code.
         */
        EmployeeDAO employeeDAO = new EmployeeDAO(conn);

        employeeDAO.createEmployeeTable();
        employeeDAO.seedSampleEmployees();

        int choice;

        System.out.println("==================================================");
        System.out.println("Phase Final Project - Employee Management App");
        System.out.println("By Max Ramos");
        System.out.println("==================================================");
        System.out.println("Welcome to the Employee Management Application.");
        System.out.println("This program stores employee records in a SQLite database.");
        System.out.println("Use the menu below to create, read, update, and delete");
        System.out.println("employee information.");

        do {
            displayMenu();
            choice = getIntInput(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    addEmployee(scanner, employeeDAO);
                    break;
                case 2:
                    displayAllEmployees(employeeDAO);
                    break;
                case 3:
                    displayEmployeesByType(scanner, employeeDAO);
                    break;
                case 4:
                    searchEmployeeById(scanner, employeeDAO);
                    break;
                case 5:
                    updateEmployee(scanner, employeeDAO);
                    break;
                case 6:
                    deleteEmployee(scanner, employeeDAO);
                    break;
                case 7:
                    displayEmployeePay(employeeDAO);
                    break;
                case 8:
                    System.out.println("Thank you for using the Employee Management Application. Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 8.");
            }

        } while (choice != 8);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n================ MENU ================");
        System.out.println("1. Add Employee");
        System.out.println("2. Display All Employees");
        System.out.println("3. Display Employees by Type");
        System.out.println("4. Search Employee by ID");
        System.out.println("5. Update Employee");
        System.out.println("6. Delete Employee");
        System.out.println("7. Display Employee Pay");
        System.out.println("8. Exit");
    }

    private static void addEmployee(Scanner scanner, EmployeeDAO employeeDAO) {
        int id = getIntInput(scanner, "Enter employee ID: ");

        if (employeeDAO.getEmployeeById(id) != null) {
            System.out.println("An employee with that ID already exists.");
            return;
        }

        Employee employee = collectEmployeeInformation(scanner, id);

        if (employee != null) {
            boolean success = employeeDAO.addEmployee(employee);

            if (success) {
                System.out.println("Employee added successfully.");
            } else {
                System.out.println("Employee was not added.");
            }
        }
    }

    private static void displayAllEmployees(EmployeeDAO employeeDAO) {
        ArrayList<Employee> employees = employeeDAO.getAllEmployees();

        if (employees.isEmpty()) {
            System.out.println("No employees stored in the database.");
            return;
        }

        System.out.println("\nEmployee Records Stored in Database");

        for (Employee employee : employees) {
            System.out.println("----------------------------------");
            System.out.println(employee);
        }
    }

    private static void displayEmployeesByType(Scanner scanner, EmployeeDAO employeeDAO) {
        System.out.println("Display which type?");
        System.out.println("1. Hourly");
        System.out.println("2. Salaried");
        System.out.println("3. Commission");

        int typeChoice = getIntInput(scanner, "Enter choice: ");

        ArrayList<Employee> employees = employeeDAO.getEmployeesByType(typeChoice);

        if (employees.isEmpty()) {
            System.out.println("No employees of that type were found.");
            return;
        }

        for (Employee employee : employees) {
            System.out.println("----------------------------------");
            System.out.println(employee);
        }
    }

    private static void searchEmployeeById(Scanner scanner, EmployeeDAO employeeDAO) {
        int id = getIntInput(scanner, "Enter employee ID to search: ");

        Employee employee = employeeDAO.getEmployeeById(id);

        if (employee == null) {
            System.out.println("Employee not found.");
        } else {
            System.out.println("----------------------------------");
            System.out.println(employee);
        }
    }

    private static void updateEmployee(Scanner scanner, EmployeeDAO employeeDAO) {
        int id = getIntInput(scanner, "Enter employee ID to update: ");

        Employee existingEmployee = employeeDAO.getEmployeeById(id);

        if (existingEmployee == null) {
            System.out.println("Employee not found. Update cancelled.");
            return;
        }

        System.out.println("Current employee information:");
        System.out.println("----------------------------------");
        System.out.println(existingEmployee);

        System.out.println("\nEnter the updated employee information.");
        Employee updatedEmployee = collectEmployeeInformation(scanner, id);

        if (updatedEmployee != null) {
            boolean success = employeeDAO.updateEmployee(updatedEmployee);

            if (success) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Employee update failed.");
            }
        }
    }

    private static void deleteEmployee(Scanner scanner, EmployeeDAO employeeDAO) {
        int id = getIntInput(scanner, "Enter employee ID to delete: ");

        Employee employee = employeeDAO.getEmployeeById(id);

        if (employee == null) {
            System.out.println("Employee not found. Delete cancelled.");
            return;
        }

        System.out.println("Employee selected for deletion:");
        System.out.println("----------------------------------");
        System.out.println(employee);

        System.out.print("Are you sure you want to delete this employee? Enter Y or N: ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            boolean success = employeeDAO.deleteEmployee(id);

            if (success) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee delete failed.");
            }
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    private static void displayEmployeePay(EmployeeDAO employeeDAO) {
        ArrayList<Employee> employees = employeeDAO.getAllEmployees();

        if (employees.isEmpty()) {
            System.out.println("No employees stored in the database.");
            return;
        }

        System.out.println("\nEmployee Payment Information");
        System.out.println("==========================================");

        /*
         * Polymorphism is demonstrated here.
         * Each object is stored as an Employee reference, but calculatePay()
         * runs differently depending on the actual subclass object.
         */
        for (Employee employee : employees) {
            System.out.println("----------------------------------");
            System.out.println(employee.getFirstName() + " " + employee.getLastName());
            System.out.println("Type: " + employee.getEmployeeType());
            System.out.println("Pay this period: $" + String.format("%.2f", employee.calculatePay()));
        }
    }

    private static Employee collectEmployeeInformation(Scanner scanner, int id) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter department name: ");
        String deptName = scanner.nextLine();

        System.out.print("Enter department location: ");
        String deptLocation = scanner.nextLine();

        /*
         * Composition is demonstrated here.
         * An Employee has a Department object as part of its information.
         */
        Department department = new Department(deptName, deptLocation);

        System.out.println("Choose employee type:");
        System.out.println("1. Hourly");
        System.out.println("2. Salaried");
        System.out.println("3. Commission");

        int typeChoice = getIntInput(scanner, "Enter choice: ");

        /*
         * These constructor calls create specific employee subclass objects.
         * Each subclass inherits the shared Employee fields and adds its own
         * unique pay-related fields.
         */
        if (typeChoice == 1) {
            double hourlyRate = getDoubleInput(scanner, "Enter hourly rate: ");
            double hoursWorked = getDoubleInput(scanner, "Enter hours worked: ");

            return new HourlyEmployee(id, firstName, lastName, department, hourlyRate, hoursWorked);

        } else if (typeChoice == 2) {
            double annualSalary = getDoubleInput(scanner, "Enter annual salary: ");

            return new SalariedEmployee(id, firstName, lastName, department, annualSalary);

        } else if (typeChoice == 3) {
            double basePay = getDoubleInput(scanner, "Enter base pay: ");
            double commissionRate = getDoubleInput(scanner, "Enter commission rate: ");
            double salesAmount = getDoubleInput(scanner, "Enter sales amount: ");

            return new CommissionEmployee(id, firstName, lastName, department, basePay, commissionRate, salesAmount);

        } else {
            System.out.println("Invalid employee type. Operation cancelled.");
            return null;
        }
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double getDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}