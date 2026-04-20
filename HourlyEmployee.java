/*
 * Name: Max Ramos
 * Date: April 19, 2026
 * Assignment: Week 2 Project - Employee Management Application
 * Purpose: Child class of Employee used to demonstrate inheritance and interface implementation.
 */

public class HourlyEmployee extends Employee implements Payable {
    private double hourlyRate;
    private double hoursWorked;

    public HourlyEmployee(int employeeId, String firstName, String lastName,
                          Department department, double hourlyRate, double hoursWorked) {
        super(employeeId, firstName, lastName, department);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String getEmployeeType() {
        return "Hourly Employee";
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nHourly Rate: $" + hourlyRate +
               "\nHours Worked: " + hoursWorked +
               "\nCalculated Pay: $" + String.format("%.2f", calculatePay());
    }
}
