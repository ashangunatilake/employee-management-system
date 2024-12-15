package database;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DatabaseServices {
	Connection startConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/company", "root", "");
		System.out.println("Connection created");
		return connection;
	}
	
	void loadEmployeeData(JTable table, JPanel panel) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing data

        String query = "SELECT * FROM employees";

        try (Connection connection = startConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
            	int eID = resultSet.getInt("EmployeeID");
                String eName = resultSet.getString("Name");
                int eDeptNo = resultSet.getInt("DeptNumber");
                Date eDOB = resultSet.getDate("DOB");
                String eJob = resultSet.getString("JobTitle");
                double eSalary = resultSet.getDouble("Salary");
                int eSsn = resultSet.getInt("SSN");

                model.addRow(new Object[]{eID, eName, eDeptNo, eDOB, eJob, eSalary, eSsn});
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(panel, "Error loading employee data");
        }
	}
	
	void loadDepartmentData(JTable table, JPanel panel) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing data

        String query = "SELECT Dname, Dnumber, Mgr_ssn, Mgr_start_date FROM DEPARTMENT";

        try (Connection connection = startConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String dName = resultSet.getString("DName");
                int dNumber = resultSet.getInt("DNumber");
                int mgrSsn = resultSet.getInt("Mgr_ssn");
                Date mgrStartDate = resultSet.getDate("Mgr_start_date");

                model.addRow(new Object[]{dName, dNumber, mgrSsn, mgrStartDate});
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(panel, "Error loading department data");
        }
	}
	
	void loadDepartmentLocationData(JTable table, JPanel panel) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing data
        
        String query = "SELECT Dnumber, Dlocation FROM DEPT_LOCATIONS";
        
        try (Connection connection = startConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

               while (resultSet.next()) {
                   int dNumber = resultSet.getInt("Dnumber");
                   String dLocation = resultSet.getString("Dlocation");

                   model.addRow(new Object[]{dNumber, dLocation});
               }
           } catch (SQLException | ClassNotFoundException e) {
               JOptionPane.showMessageDialog(panel, "Error loading department location data");
           }
	}
	
	String getValueEmployee(String eID, String field, JPanel panel) {
		String query = "SELECT " + field + " FROM employees WHERE EmployeeID = ?";
        int id = Integer.parseInt(eID);
        try (Connection connection = startConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(field);
            } else {
                JOptionPane.showMessageDialog(panel, "No employee found with id: " + id);
                return null;
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(panel, "Error : Not found");
            return null;
        }
	}
	
	void insertEmployee(String eID, String eName, String eDeptNo, String eDOB, String eJob, String eSalary, String eSsn, JPanel panel) {
		int ID = Integer.parseInt(eID);
		int department = Integer.parseInt(eDeptNo);
		double salary = Double.parseDouble(eSalary);
		int ssn = Integer.parseInt(eSsn);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		java.sql.Date sqlDate = null;
		try {
			LocalDate localDate = LocalDate.parse(eDOB, formatter);
            sqlDate = java.sql.Date.valueOf(localDate);
            System.out.println("Converted to java.sql.Date: " + sqlDate);
        } catch (DateTimeParseException e1) {
            System.out.println("Failed to parse date: " + e1.getMessage());
        }
		
		String query = "INSERT INTO employees VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = startConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
		preparedStatement.setInt(1, ID);
        preparedStatement.setString(2, eName);
        preparedStatement.setInt(3, department);
        preparedStatement.setDate(4, sqlDate);
        preparedStatement.setString(5, eJob);
        preparedStatement.setDouble(6, salary);
        preparedStatement.setInt(7, ssn);
        preparedStatement.executeUpdate();
        
        JOptionPane.showMessageDialog(panel, "Employee added successfully");
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(panel, "Error adding employee");
		}
	}
	
	void updateEmployee(String eID, String eName, String eDeptNo, String eDOB, String eJob, String eSalary, String eSsn, JPanel panel) {
		int ID = Integer.parseInt(eID);
		int department = Integer.parseInt(eDeptNo);
		double salary = Double.parseDouble(eSalary);
		int ssn = Integer.parseInt(eSsn);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		java.sql.Date sqlDate = null;
		try {
			LocalDate localDate = LocalDate.parse(eDOB, formatter);
            sqlDate = java.sql.Date.valueOf(localDate);
            System.out.println("Converted to java.sql.Date: " + sqlDate);
        } catch (DateTimeParseException e1) {
            System.out.println("Failed to parse date: " + e1.getMessage());
        }
		
		String query = "UPDATE employees SET Name = ?, DeptNumber = ?, DOB = ?, JobTitle = ?, Salary = ?, SSN = ? WHERE EmployeeID = ?";
		try (Connection connection = startConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
		preparedStatement.setString(1, eName);
        preparedStatement.setInt(2, department);
        preparedStatement.setDate(3, sqlDate);
        preparedStatement.setString(4, eJob);
        preparedStatement.setDouble(5, salary);
        preparedStatement.setInt(6, ssn);
        preparedStatement.setInt(7, ID);
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(panel, "Employee updated successfully");
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(panel, "Error updating department");
		}
	}
	
	void deleteEmployee(String eID, JPanel panel) {
		int ID = Integer.parseInt(eID);
		String query = "DELETE FROM employees WHERE EmployeeID = ?";
		try (Connection connection = startConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
		preparedStatement.setInt(1, ID);
        preparedStatement.executeUpdate();
        
        JOptionPane.showMessageDialog(panel, "Employee deleted successfully.");
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(panel, "Error deleting employee");
		}
	}
	
	String getValueDepartment(String dnum, String field, JPanel panel) {
		String query = "SELECT " + field + " FROM DEPARTMENT WHERE Dnumber = ?";
        int num = Integer.parseInt(dnum);
        try (Connection connection = startConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, num);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(field);
            } else {
                JOptionPane.showMessageDialog(panel, "No department found with number: " + dnum);
                return null;
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(panel, "Error : Not found");
            return null;
        }
	}
	
	void insertDepartment(String dname, String dnumber, String mgr_ssn, String mgr_start_date, JPanel panel) {
		int num = Integer.parseInt(dnumber);
		int mgrssn = Integer.parseInt(mgr_ssn);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		java.sql.Date sqlDate = null;
		try {
			LocalDate localDate = LocalDate.parse(mgr_start_date, formatter);
            sqlDate = java.sql.Date.valueOf(localDate);
            System.out.println("Converted to java.sql.Date: " + sqlDate);
        } catch (DateTimeParseException e1) {
            System.out.println("Failed to parse date: " + e1.getMessage());
        }
		
		String query = "INSERT INTO DEPARTMENT VALUES (?, ?, ?, ?)";
		try (Connection connection = startConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
		preparedStatement.setString(1, dname);
        preparedStatement.setInt(2, num);
        preparedStatement.setInt(3, mgrssn);
        preparedStatement.setDate(4, sqlDate);
        preparedStatement.executeUpdate();
        
        JOptionPane.showMessageDialog(panel, "Department inserted successfully");
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(panel, "Error creating department");
		}
	}
	
	void deleteDepartment(String dnumber, JPanel panel) {
		int num = Integer.parseInt(dnumber);
		String query = "DELETE FROM DEPARTMENT WHERE Dnumber = ?";
		try (Connection connection = startConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
		preparedStatement.setInt(1, num);
        preparedStatement.executeUpdate();
        
        JOptionPane.showMessageDialog(panel, "Department deleted successfully.");
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(panel, "Error deleting department");
		}
	}
	
	void updateDepartment(String dname, String dnumber, String mgr_ssn, String mgr_start_date, JPanel panel) {
		int num = Integer.parseInt(dnumber);
		int mgrssn = Integer.parseInt(mgr_ssn);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		java.sql.Date sqlDate = null;
		try {
			LocalDate localDate = LocalDate.parse(mgr_start_date, formatter);
            sqlDate = java.sql.Date.valueOf(localDate);
            System.out.println("Converted to java.sql.Date: " + sqlDate);
        } catch (DateTimeParseException e1) {
            System.out.println("Failed to parse date: " + e1.getMessage());
        }
		
		String query = "UPDATE DEPARTMENT SET Dname = ?, Mgr_ssn = ?, Mgr_start_date = ? WHERE Dnumber = ?";
		try (Connection connection = startConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
		preparedStatement.setString(1, dname);
        preparedStatement.setInt(2, mgrssn);
        preparedStatement.setDate(3, sqlDate);
        preparedStatement.setInt(4, num);
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(panel, "Department updated successfully");
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(panel, "Error updating department");
		}
	}
	
	void insertDeptLocation(String dnumber, String dlocation, JPanel panel) {
		int num = Integer.parseInt(dnumber);
		String query = "INSERT INTO DEPT_LOCATIONS VALUES (?, ?)";
		try (Connection connection = startConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
        preparedStatement.setInt(1, num);
        preparedStatement.setString(2, dlocation);
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(panel, "Department location inserted successfully");
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(panel, "Error creating department location");
		}
	}
	
	void updateDeptLocation(String dnumber, String dlocation, JPanel panel) {
		int num = Integer.parseInt(dnumber);
		String query = "UPDATE DEPT_LOCATIONS SET Dnumber = ?, Dlocation = ? WHERE Dnumber = ? AND Dlocation = ?";
		try (Connection connection = startConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
		preparedStatement.setInt(1, num);
        preparedStatement.setString(2, dlocation);
        preparedStatement.setInt(3, num);
        preparedStatement.setString(4, dlocation);
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(panel, "Department location updated successfully");
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(panel, "Error updating department");
		}
	}
	
	void deleteDeptLocation(String dnumber, String dlocation, JPanel panel) {
		int num = Integer.parseInt(dnumber);
		String query = "DELETE FROM DEPT_LOCATIONS WHERE Dnumber = ? AND Dlocation = ?";
		try (Connection connection = startConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
		preparedStatement.setInt(1, num);
		preparedStatement.setString(2, dlocation);
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(panel, "Department location deleted successfully.");
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(panel, "Error deleting department location");
		}
	}
}
