package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;


import model.Employee;
import util.MySQLHelper;

public class EmployeeDAO {
	public boolean insert(Employee employee){
		boolean result = false;
		String sql = "insert into employee(id, name, position, salary) values(?, ?, ?, ?)";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, employee.getId());
			ps.setString(2, employee.getName());
			ps.setInt(3, employee.getPosition());
			ps.setInt(4, employee.getSalary());
			
			int row = ps.executeUpdate();
			if(row > 0)
				result = true;
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean update(Employee employee){
		boolean result = false;
		String sql = "update employee set name = ?, position = ?, salary = ? where id = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(4, employee.getId());
			ps.setString(1, employee.getName());
			ps.setInt(2, employee.getPosition());
			ps.setInt(3, employee.getSalary());
			
			int row = ps.executeUpdate();
			if(row > 0)
				result = true;
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean delete(String id){
		boolean result = false;
		String sql = "delete from employee where id = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, id);			
			
			int row = ps.executeUpdate();
			if(row > 0)
				result = true;
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public Vector selectAll(){
		Vector employees = new Vector();
		Vector employee;
		String sql = "select * from employee";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next()){
				employee = new Vector<>();
				for(int i=1; i<=columns; i++){
					employee.add(rs.getString(i));
				}
				employees.add(employee);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employees;
	}
	public Vector selectById(String id){		
		Vector employee = new Vector();
		String sql = "select * from employee where id = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next()){
				employee = new Vector();
				for(int i=1; i<=columns; i++){
					employee.add(rs.getString(i));
				}				
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}
	public Vector selectByNameOrId(String search){
		Vector employees = new Vector();
		Vector employee;
		String sql = "select * from employee where id = ? or name like ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, search);
			ps.setString(2, "%" + search + "%");
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next()){
				employee = new Vector<>();
				for(int i=1; i<=columns; i++){
					employee.add(rs.getString(i));
				}
				employees.add(employee);
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employees;
	}
	public boolean validId(String id){
		boolean result = false;
		String sql = "select count(id) from employee where id = ?";
		try {
			PreparedStatement ps = MySQLHelper.openDB().prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				int count = rs.getInt(1);
				if(count == 0)
					result = true;
			}
			rs.close();
			ps.close();
			MySQLHelper.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
