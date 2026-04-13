/*
 * Name: Max Ramos
 * Date: April 12, 2026
 * Assignment: Week 1 Project - Employee Management Application
 * Purpose: Main application file that allows the user to manage employees
 * through a console-based menu system.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Employee> employees = new ArrayList<>();

        // Sample employee data
        employees.add(new HourlyEmployee(
                101, "John", "Smith",
                new Department("Sales", "Building A"),
                18.50, 40
        ));

        employees.add(new SalariedEmployee(
                102, "Sarah", "Jones",
                new Department("Human Resources", "Building B"),
                55000
        ));

        employees.add(new CommissionEmployee(
                103, "Mike", "Brown",
                new Department("Marketing", "Building C"),
                30000, 0.10, 20000
        ));

        int choice;

        do {
            System.out.println("\n==========================================");
            System.out.println("Week 1 Project - Employee Management App");
            System.out.println("By Max Ramos");
            System.out.println("==========================================");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Display All Employees");
            System.out.println("5. Display Employees by Type");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    addEmployee(scanner, employees);
                    break;
                case 2:
                    removeEmployee(scanner, employees);
                    break;
                case 3:
                    updateEmployee(scanner, employees);
                    break;
                case 4:
                    displayAllEmployees(employees);
                    break;
                case 5:
                    displayEmployeesByType(scanner, employees);
                    break;
                case 6:
                    System.out.println("Thank you for using the Employee Management Application. Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);

        scanner.close();
    }

    public static void addEmployee(Scanner scanner, ArrayList<Employee> employees) {
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter department name: ");
        String deptName = scanner.nextLine();

        System.out.print("Enter department location: ");
        String deptLocation = scanner.nextLine();

        Department department = new Department(deptName, deptLocation);

        System.out.println("Choose employee type:");
        System.out.println("1. Hourly");
        System.out.println("2. Salaried");
        System.out.println("3. Commission");
        System.out.print("Enter choice: ");
        int typeChoice = scanner.nextInt();

        switch (typeChoice) {
            case 1:
                System.out.print("Enter hourly rate: ");
                double hourlyRate = scanner.nextDouble();

                System.out.print("Enter hours worked: ");
                double hoursWorked = scanner.nextDouble();

                employees.add(new HourlyEmployee(id, firstName, lastName, department, hourlyRate, hoursWorked));
                System.out.println("Hourly employee added successfully.");
                break;

            case 2:
                System.out.print("Enter annual salary: ");
                double annualSalary = scanner.nextDouble();

                employees.add(new SalariedEmployee(id, firstName, lastName, department, annualSalary));
                System.out.println("Salaried employee added successfully.");
                break;

            case 3:
                System.out.print("Enter base pay: ");
                double basePay = scanner.nextDouble();

                System.out.print("Enter commission rate: ");
                double commissionRate = scanner.nextDouble();

                System.out.print("Enter sales amount: ");
                double salesAmount = scanner.nextDouble();

                employees.add(new CommissionEmployee(id, firstName, lastName, department, basePay, commissionRate, salesAmount));
                System.out.println("Commission employee added successfully.");
                break;

            default:
                System.out.println("Invalid employee type.");
        }
    }

    public static void removeEmployee(Scanner scanner, ArrayList<Employee> employees) {
        System.out.print("Enter employee ID to remove: ");
        int id = scanner.nextInt();

        Employee employeeToRemove = null;

        for (Employee employee : employees) {
            if (employee.getEmployeeId() == id) {
                employeeToRemove = employee;
                break;
            }
        }

        if (employeeToRemove != null) {
            employees.remove(employeeToRemove);
            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static void updateEmployee(Scanner scanner, ArrayList<Employee> employees) {
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Employee employeeToUpdate = null;

        for (Employee employee : employees) {
            if (employee.getEmployeeId() == id) {
                employeeToUpdate = employee;
                break;
            }
        }

        if (employeeToUpdate == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Enter new first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter new last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter new department name: ");
        String deptName = scanner.nextLine();

        System.out.print("Enter new department location: ");
        String deptLocation = scanner.nextLine();

        employeeToUpdate.setFirstName(firstName);
        employeeToUpdate.setLastName(lastName);
        employeeToUpdate.setDepartment(new Department(deptName, deptLocation));

        System.out.println("Employee updated successfully.");
    }

    public static void displayAllEmployees(ArrayList<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }

        for (Employee employee : employees) {
            System.out.println("----------------------------------");
            System.out.println(employee);
        }
    }

    public static void displayEmployeesByType(Scanner scanner, ArrayList<Employee> employees) {
        System.out.println("Display which type?");
        System.out.println("1. Hourly");
        System.out.println("2. Salaried");
        System.out.println("3. Commission");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();

        boolean found = false;

        for (Employee employee : employees) {
            if ((choice == 1 && employee instanceof HourlyEmployee) ||
                (choice == 2 && employee instanceof SalariedEmployee) ||
                (choice == 3 && employee instanceof CommissionEmployee)) {
                System.out.println("----------------------------------");
                System.out.println(employee);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No employees of that type found.");
        }
    }
}