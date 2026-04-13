/*
 * Name: Max Ramos
 * Date: April 12, 2026
 * Assignment: Week 1 Project - Employee Management Application
 * Purpose: Child class of Employee used to demonstrate inheritance.
 */

public class CommissionEmployee extends Employee {
    private double basePay;
    private double commissionRate;
    private double salesAmount;

    public CommissionEmployee(int employeeId, String firstName, String lastName,
                              Department department, double basePay,
                              double commissionRate, double salesAmount) {
        super(employeeId, firstName, lastName, department);
        this.basePay = basePay;
        this.commissionRate = commissionRate;
        this.salesAmount = salesAmount;
    }

    @Override
    public String getEmployeeType() {
        return "Commission Employee";
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nBase Pay: $" + basePay +
               "\nCommission Rate: " + commissionRate +
               "\nSales Amount: $" + salesAmount;
    }
}