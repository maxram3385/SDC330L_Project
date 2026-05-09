/*
 * Name: Max Ramos
 * Date: May 9, 2026
 * Assignment: Phase Final Project - Employee Management Application
 * Purpose: Abstract base Employee class used to demonstrate abstraction,
 * inheritance, constructors, composition, and access specifiers.
 */

public abstract class Employee implements Payable {
    /*
     * These fields are private access specifiers.
     * Private fields cannot be accessed directly from outside this class.
     * This protects the data and supports encapsulation.
     */
    private int employeeId;
    private String firstName;
    private String lastName;
    private Department department;

    /*
     * Default constructor.
     * This gives subclasses a basic Employee setup when no values are provided.
     */
    public Employee() {
        this.employeeId = 0;
        this.firstName = "Unknown";
        this.lastName = "Unknown";
        this.department = new Department();
    }

    /*
     * Full constructor.
     * This constructor allows subclasses to pass shared employee information
     * into the abstract Employee base class.
     */
    public Employee(int employeeId, String firstName, String lastName, Department department) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    /*
     * Public getter methods provide controlled access to private fields.
     */
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

    /*
     * Public setter methods allow controlled updates to private fields.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    /*
     * Protected access allows this method to be used by Employee and its child
     * classes, but not freely accessed from unrelated classes.
     */
    protected String getBasicEmployeeInfo() {
        return "Employee ID: " + employeeId +
               "\nName: " + firstName + " " + lastName +
               "\nEmployee Type: " + getEmployeeType() +
               "\nDepartment: " + department;
    }

    /*
     * Abstract method.
     * Employee is a general base class, so it does not define one employee type.
     * Each child class must provide its own employee type.
     */
    public abstract String getEmployeeType();

    /*
     * Abstract method from the Payable interface.
     * Each child class must calculate pay differently.
     */
    public abstract double calculatePay();

    @Override
    public String toString() {
        return getBasicEmployeeInfo();
    }
}