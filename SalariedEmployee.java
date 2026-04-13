/*
 * Name: Max Ramos
 * Date: April 12, 2026
 * Assignment: Week 1 Project - Employee Management Application
 * Purpose: Child class of Employee used to demonstrate inheritance.
 */

public class SalariedEmployee extends Employee {
    private double annualSalary;

    public SalariedEmployee(int employeeId, String firstName, String lastName,
                            Department department, double annualSalary) {
        super(employeeId, firstName, lastName, department);
        this.annualSalary = annualSalary;
    }

    @Override
    public String getEmployeeType() {
        return "Salaried Employee";
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nAnnual Salary: $" + annualSalary;
    }
}