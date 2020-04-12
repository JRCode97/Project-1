package dev.rivera.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dev.rivera.backend.ConnectionUtils;
import dev.rivera.entities.Employee;

public class EmployeeDAOdb implements EmployeeDAO {



	public Employee getEmployeeByID(int id) {
		
		try (Connection con = ConnectionUtils.createConnection()){
			String sql = "SELECT * FROM EMPLOYEE WHERE ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Employee e = new Employee(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(2));
				return e;
			}else {
				System.out.println("employee not found");
			}
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return null;
	}

	public Employee UpdateEmployee(Employee e) {
		try (Connection con = ConnectionUtils.createConnection()){
			String sql = "UPDATE EMPLOYEE SET ID = ?,NAME = ?,USERNAME = ?,PASSWORD = ? WHERE ID =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, e.geteId());
			ps.setString(2, e.getName());
			ps.setString(3, e.getUsername());
			ps.setString(4, e.getPassword());
			ps.setInt(5, e.geteId());
			ps.executeUpdate();
			return e;
		}catch(Exception ex) {
			System.out.println(e);
		}
		return e;
	}

	public boolean DeleteEmployee(Employee e) {
		try (Connection con = ConnectionUtils.createConnection()){
			String Query = "DELETE FROM EMPLOYEE WHERE ID = ?";
			PreparedStatement post = con.prepareStatement(Query);
			post.setInt(1,e.geteId());
			boolean success = post.execute();
			return success;
		}catch(Exception ex){
			System.out.println(ex);
		}
			return true;
	}

}
