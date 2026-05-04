/*
 * Name: Max Ramos
 * Date: May 3 2026
 * Assignment: Week 4 Project - Database Interactions
 * Purpose: Data Access Object class that performs Create, Read, Update,
 * and Delete operations for employee records stored in a SQLite database.
 */

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {
    private Connection conn;

    public EmployeeDAO(Connection conn) {
        this.conn = conn;
    }

    public void createEmployeeTable() {
        String sql = "CREATE TABLE IF NOT EXISTS employees (" +
                "employee_id INTEGER PRIMARY KEY, " +
                "first_name TEXT NOT NULL, " +
                "last_name TEXT NOT NULL, " +
                "department_name TEXT NOT NULL, " +
                "department_location TEXT NOT NULL, " +
                "employee_type TEXT NOT NULL, " +
                "hourly_rate REAL, " +
                "hours_worked REAL, " +
                "annual_salary REAL, " +
                "base_pay REAL, " +
                "commission_rate REAL, " +
                "sales_amount REAL" +
                ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creating employee table: " + e.getMessage());
        }
    }

    public void seedSampleEmployees() {
        if (!getAllEmployees().isEmpty()) {
            return;
        }

        addEmployee(new HourlyEmployee(
                101,
                "John",
                "Smith",
                new Department("Sales", "Building A"),
                18.50,
                40
        ));

        addEmployee(new SalariedEmployee(
                102,
                "Sarah",
                "Jones",
                new Department("Human Resources", "Building B"),
                55000
        ));

        addEmployee(new CommissionEmployee(
                103,
                "Mike",
                "Brown",
                new Department("Marketing", "Building C"),
                700.00,
                0.10,
                5000
        ));
    }

    public boolean addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (" +
                "employee_id, first_name, last_name, department_name, department_location, employee_type, " +
                "hourly_rate, hours_worked, annual_salary, base_pay, commission_rate, sales_amount" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employee.getEmployeeId());
            pstmt.setString(2, employee.getFirstName());
            pstmt.setString(3, employee.getLastName());
            pstmt.setString(4, employee.getDepartment().getDepartmentName());
            pstmt.setString(5, employee.getDepartment().getLocation());
            pstmt.setString(6, employee.getEmployeeType());

            if (employee instanceof HourlyEmployee) {
                HourlyEmployee hourly = (HourlyEmployee) employee;
                pstmt.setDouble(7, hourly.getHourlyRate());
                pstmt.setDouble(8, hourly.getHoursWorked());
                pstmt.setNull(9, Types.REAL);
                pstmt.setNull(10, Types.REAL);
                pstmt.setNull(11, Types.REAL);
                pstmt.setNull(12, Types.REAL);
            } else if (employee instanceof SalariedEmployee) {
                SalariedEmployee salaried = (SalariedEmployee) employee;
                pstmt.setNull(7, Types.REAL);
                pstmt.setNull(8, Types.REAL);
                pstmt.setDouble(9, salaried.getAnnualSalary());
                pstmt.setNull(10, Types.REAL);
                pstmt.setNull(11, Types.REAL);
                pstmt.setNull(12, Types.REAL);
            } else if (employee instanceof CommissionEmployee) {
                CommissionEmployee commission = (CommissionEmployee) employee;
                pstmt.setNull(7, Types.REAL);
                pstmt.setNull(8, Types.REAL);
                pstmt.setNull(9, Types.REAL);
                pstmt.setDouble(10, commission.getBasePay());
                pstmt.setDouble(11, commission.getCommissionRate());
                pstmt.setDouble(12, commission.getSalesAmount());
            }

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();

        String sql = "SELECT * FROM employees ORDER BY employee_id;";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                employees.add(createEmployeeFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error reading employees: " + e.getMessage());
        }

        return employees;
    }

    public Employee getEmployeeById(int employeeId) {
        String sql = "SELECT * FROM employees WHERE employee_id = ?;";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return createEmployeeFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.out.println("Error finding employee: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Employee> getEmployeesByType(int typeChoice) {
        ArrayList<Employee> employees = new ArrayList<>();
        String employeeType = "";

        if (typeChoice == 1) {
            employeeType = "Hourly Employee";
        } else if (typeChoice == 2) {
            employeeType = "Salaried Employee";
        } else if (typeChoice == 3) {
            employeeType = "Commission Employee";
        } else {
            return employees;
        }

        String sql = "SELECT * FROM employees WHERE employee_type = ? ORDER BY employee_id;";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employeeType);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                employees.add(createEmployeeFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error reading employees by type: " + e.getMessage());
        }

        return employees;
    }

    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET " +
                "first_name = ?, " +
                "last_name = ?, " +
                "department_name = ?, " +
                "department_location = ?, " +
                "employee_type = ?, " +
                "hourly_rate = ?, " +
                "hours_worked = ?, " +
                "annual_salary = ?, " +
                "base_pay = ?, " +
                "commission_rate = ?, " +
                "sales_amount = ? " +
                "WHERE employee_id = ?;";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getDepartment().getDepartmentName());
            pstmt.setString(4, employee.getDepartment().getLocation());
            pstmt.setString(5, employee.getEmployeeType());

            if (employee instanceof HourlyEmployee) {
                HourlyEmployee hourly = (HourlyEmployee) employee;
                pstmt.setDouble(6, hourly.getHourlyRate());
                pstmt.setDouble(7, hourly.getHoursWorked());
                pstmt.setNull(8, Types.REAL);
                pstmt.setNull(9, Types.REAL);
                pstmt.setNull(10, Types.REAL);
                pstmt.setNull(11, Types.REAL);
            } else if (employee instanceof SalariedEmployee) {
                SalariedEmployee salaried = (SalariedEmployee) employee;
                pstmt.setNull(6, Types.REAL);
                pstmt.setNull(7, Types.REAL);
                pstmt.setDouble(8, salaried.getAnnualSalary());
                pstmt.setNull(9, Types.REAL);
                pstmt.setNull(10, Types.REAL);
                pstmt.setNull(11, Types.REAL);
            } else if (employee instanceof CommissionEmployee) {
                CommissionEmployee commission = (CommissionEmployee) employee;
                pstmt.setNull(6, Types.REAL);
                pstmt.setNull(7, Types.REAL);
                pstmt.setNull(8, Types.REAL);
                pstmt.setDouble(9, commission.getBasePay());
                pstmt.setDouble(10, commission.getCommissionRate());
                pstmt.setDouble(11, commission.getSalesAmount());
            }

            pstmt.setInt(12, employee.getEmployeeId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Error updating employee: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteEmployee(int employeeId) {
        String sql = "DELETE FROM employees WHERE employee_id = ?;";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);

            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
            return false;
        }
    }

    private Employee createEmployeeFromResultSet(ResultSet rs) throws SQLException {
        int employeeId = rs.getInt("employee_id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String departmentName = rs.getString("department_name");
        String departmentLocation = rs.getString("department_location");
        String employeeType = rs.getString("employee_type");

        Department department = new Department(departmentName, departmentLocation);

        if (employeeType.equals("Hourly Employee")) {
            return new HourlyEmployee(
                    employeeId,
                    firstName,
                    lastName,
                    department,
                    rs.getDouble("hourly_rate"),
                    rs.getDouble("hours_worked")
            );
        } else if (employeeType.equals("Salaried Employee")) {
            return new SalariedEmployee(
                    employeeId,
                    firstName,
                    lastName,
                    department,
                    rs.getDouble("annual_salary")
            );
        } else {
            return new CommissionEmployee(
                    employeeId,
                    firstName,
                    lastName,
                    department,
                    rs.getDouble("base_pay"),
                    rs.getDouble("commission_rate"),
                    rs.getDouble("sales_amount")
            );
        }
    }
}