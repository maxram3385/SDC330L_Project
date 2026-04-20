/*
 * Name: Max Ramos
 * Date: April 19, 2026
 * Assignment: Week 2 Project - Employee Management Application
 * Purpose: Child class of Employee used to demonstrate inheritance and interface implementation.
 */

public class SalariedEmployee extends Employee implements Payable {
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
    public double calculatePay() {
        return annualSalary / 52;
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nAnnual Salary: $" + annualSalary +
               "\nCalculated Weekly Pay: $" + String.format("%.2f", calculatePay());
    }
}
