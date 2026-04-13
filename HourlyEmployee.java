/*
 * Name: Max Ramos
 * Date: April 12, 2026
 * Assignment: Week 1 Project - Employee Management Application
 * Purpose: Child class of Employee used to demonstrate inheritance.
 */

public class HourlyEmployee extends Employee {
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
    public String toString() {
        return super.toString() +
               "\nHourly Rate: $" + hourlyRate +
               "\nHours Worked: " + hoursWorked;
    }
}