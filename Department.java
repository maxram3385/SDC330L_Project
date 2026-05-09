/*
 * Name: Max Ramos
 * Date: May 9, 2026
 * Assignment: Phase Final Project - Employee Management Application
 * Purpose: Department class used to demonstrate composition and constructors.
 */

public class Department {
    /*
     * Private fields are used so department data cannot be changed directly
     * from outside the class.
     */
    private String departmentName;
    private String location;

    /*
     * Default constructor.
     * This can be used when department information is not provided yet.
     */
    public Department() {
        this.departmentName = "Unknown Department";
        this.location = "Unknown Location";
    }

    /*
     * Overloaded constructor.
     * This allows a Department object to be created with only a department name.
     */
    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.location = "Unknown Location";
    }

    /*
     * Full constructor.
     * This is used when both department name and location are known.
     */
    public Department(String departmentName, String location) {
        this.departmentName = departmentName;
        this.location = location;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getLocation() {
        return location;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return departmentName + " - " + location;
    }
}