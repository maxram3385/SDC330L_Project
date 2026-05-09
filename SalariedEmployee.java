/*
 * Name: Max Ramos
 * Date: May 9, 2026
 * Assignment: Phase Final Project - Employee Management Application
 * Purpose: Child class of Employee used to demonstrate inheritance,
 * abstraction, constructors, and pay calculation.
 */

public class SalariedEmployee extends Employee {
    private double annualSalary;

    /*
     * Default constructor.
     * Calls the Employee default constructor using super().
     */
    public SalariedEmployee() {
        super();
        this.annualSalary = 0.0;
    }

    /*
     * Full constructor.
     * Sends shared employee information to the abstract Employee base class
     * and stores salaried employee-specific information in this class.
     */
    public SalariedEmployee(int employeeId, String firstName, String lastName,
                            Department department, double annualSalary) {
        super(employeeId, firstName, lastName, department);
        this.annualSalary = annualSalary;
    }

    @Override
    public String getEmployeeType() {
        return "Salaried Employee";
    }

    /*
     * This method is required because Employee defines calculatePay() as abstract.
     * This program calculates salaried employee pay as weekly pay.
     */
    @Override
    public double calculatePay() {
        return annualSalary / 52;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    @Override
    public String toString() {
        return getBasicEmployeeInfo() +
               "\nAnnual Salary: $" + annualSalary +
               "\nCalculated Weekly Pay: $" + String.format("%.2f", calculatePay());
    }
}