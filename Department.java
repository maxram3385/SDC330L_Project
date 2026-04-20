/*
 * Name: Max Ramos
 * Date: April 19, 2026
 * Assignment: Week 2 Project - Employee Management Application
 * Purpose: Department class used to demonstrate composition.
 */

public class Department {
    private String departmentName;
    private String location;

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
