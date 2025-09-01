package dataAccessObject;


import database.BaseDBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static database.logQueryWithParam.logQueryWithParams;

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
        String query = "SELECT * FROM orangehrm_db.hs_hr_employee WHERE employee_id = ?";
        System.out.println("Executing query " + logQueryWithParams(query, employeeID));
        try (ResultSet rs = dbHelper.executeQuery(query, employeeID)) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Checks if an employee updated in the database by first name, middle name and last name of the employee
     *
     * @param firstName the employee first name to check
     * @param middleName the employee middle name to check
     * @param lastName the employee last name to check
     * @return true if the employee exists, false otherwise or if an error occurs
     */
    public boolean isEmployeeUpdatedData(String firstName, String middleName, String lastName) {
        String query = "SELECT * FROM orangehrm_db.hs_hr_employee where emp_firstname = ? and emp_middle_name = ? and emp_lastname = ?";
        System.out.println("Executing query " + logQueryWithParams(query, firstName, middleName, lastName));
        try (ResultSet rs = dbHelper.executeQuery(query, firstName, middleName, lastName)) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}