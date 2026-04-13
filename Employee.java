/*
 * Name: Max Ramos
 * Date: April 12, 2026
 * Assignment: Week 1 Project - Employee Management Application
 * Purpose: Base Employee class used to demonstrate inheritance.
 * Composition is shown because each Employee has a Department object.
 */

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private Department department; // Composition

    public Employee(int employeeId, String firstName, String lastName, Department department) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEmployeeType() {
        return "Employee";
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId +
               "\nName: " + firstName + " " + lastName +
               "\nEmployee Type: " + getEmployeeType() +
               "\nDepartment: " + department;
    }
}