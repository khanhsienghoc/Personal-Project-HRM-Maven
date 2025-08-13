package dataAccessObject;


import database.BaseDBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDataAccess {
    private BaseDBHelper dbHelper = new BaseDBHelper();

    public static EmployeeDataAccess employeeData() {
        return new EmployeeDataAccess();
    }

    /**
     * Checks if an employee exists in the database by employeeID
     *
     * @param employeeID the employee ID to check
     * @return true if the employee exists, false otherwise or if an error occurs
     */
    public boolean isEmployeeExist(String employeeID) {
        String query = "SELECT 1 FROM orangehrm_db.hs_hr_employee WHERE employee_id = ?";
        try (ResultSet rs = dbHelper.executeQuery(query, employeeID)) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isEmployeeUpdatedData(String firstName, String middleName, String lastName) {
        String query = "SELECT 1 FROM `hs_hr_employee` where emp_firstname = ? and emp_middle_name = ? and emp_lastname = ?";
        try (ResultSet rs = dbHelper.executeQuery(query, firstName, middleName, lastName)) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}