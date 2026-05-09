# Employee Management Application

## Project Summary

This project is a console-based Employee Management Application created for the SDC330L course. The application allows a user to manage employee records through a menu-driven interface. The user can add employees, display all employees, display employees by type, search for employees by ID, update employee records, delete employees, and display employee pay information.

The final version of this project combines the work from previous project phases into one complete application. The first phase focused on user interaction and menu design. The second phase focused on software design and object-oriented programming structure. Later phases added inheritance, abstraction, polymorphism, composition, and database interaction. The final application stores employee information in a SQLite database and uses full CRUD functionality.

## Features

- Add new employee records
- Display all employees
- Display employees by type
- Search employees by ID
- Update employee records
- Delete employee records
- Display calculated employee pay
- Store employee records in a SQLite database
- Seed sample employee data when the database is empty
- Use input validation for menu choices and numeric values

## Object-Oriented Programming Concepts Used

### Abstraction

The `Employee` class is an abstract base class. It contains shared employee information, but it does not represent one specific type of employee on its own. The child classes define the specific employee types.

### Inheritance

The application uses inheritance through the following child classes:

- `HourlyEmployee`
- `SalariedEmployee`
- `CommissionEmployee`

Each of these classes extends the abstract `Employee` class and reuses the shared employee fields and methods.

### Polymorphism

Polymorphism is demonstrated through the `calculatePay()` method. Each employee type calculates pay differently, but the application can call the same method name on different employee objects.

### Composition

Composition is demonstrated with the `Department` class. Each `Employee` object contains a `Department` object as part of its information.

### Interface

The `Payable` interface defines the `calculatePay()` method. Each employee type implements this behavior in its own way.

## Database Functionality

This project uses SQLite to store employee records. The `EmployeeDAO` class handles database operations and separates the database logic from the main application menu.

The database functionality includes:

- Creating the employee table
- Adding employee records
- Reading all employee records
- Searching employees by ID
- Filtering employees by type
- Updating full employee records
- Deleting employee records
- Reconstructing employee objects from database rows

## Classes Included

### App.java

Controls the main menu and user interaction.

### Employee.java

Abstract base class for all employee types.

### HourlyEmployee.java

Child class for hourly employees.

### SalariedEmployee.java

Child class for salaried employees.

### CommissionEmployee.java

Child class for commission employees.

### Department.java

Stores department name and location information.

### Payable.java

Interface requiring employee classes to calculate pay.

### EmployeeDAO.java

Handles all SQLite database CRUD operations.

### SQLiteDatabase.java

Creates the SQLite database connection.

## How to Run the Program

1. Open the project in Visual Studio Code.
2. Make sure the SQLite JDBC driver is available for the project.
3. Compile the Java files.
4. Run `App.java`.
5. Use the console menu to interact with the Employee Management Application.


## GitHub Repository

https://github.com/maxram3385/SDC330L_Project

## Author

Max Ramos