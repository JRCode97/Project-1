package dev.rivera.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dev.rivera.backend.ConnectionUtils;
import dev.rivera.entities.Employee;
import dev.rivera.entities.Manager;

public class ManagerDAOdb implements ManagerDAO {


	public Manager getManagerByID(int id) {
		try (Connection con = ConnectionUtils.createConnection()){
			String sql = "SELECT * FROM MANAGER WHERE ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Manager m = new Manager(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				return m;
			}else {
				System.out.println("manager not found");
			}
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return null;
	}

	public Manager UpdateManager(Manager m) {
		try (Connection con = ConnectionUtils.createConnection()){
			String sql = "UPDATE MANAGER SET ID = ?,USERNAME = ?,PASSWORD = ? WHERE ID =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, m.getmId());
			ps.setString(2, m.getUsername());
			ps.setString(3, m.getPassword());
			ps.setInt(4, m.getmId());
			ps.executeUpdate();
			return m;
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return m;
	}

	public boolean DeleteManager(Manager m) {
		try (Connection con = ConnectionUtils.createConnection()){
			String Query = "DELETE FROM MANAGER WHERE ID = ?";
			PreparedStatement post = con.prepareStatement(Query);
			post.setInt(1,m.getmId());
			boolean success = post.execute();
			return success;
		}catch(Exception ex){
			System.out.println(ex);
		}
			return true;
	}

	@Override
	public Manager getManagerByLoginCredentials(String username, String password) {
		try (Connection con = ConnectionUtils.createConnection()){
			String sql = "SELECT * FROM MANAGER WHERE USERNAME = ? AND PASSWORD = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Manager m = new Manager(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				return m;
			}else {
				System.out.println("manager not found");
			}
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return null;
	}
	@Override
	public int getManagerBudget(Manager m) {
		try (Connection con = ConnectionUtils.createConnection()){
			String sql = "SELECT BUDGET FROM MANAGER,WORKFORCE WHERE MANAGER.WORKFORCE_NAME = WORKFORCE.WORKFORCE_NAME AND WORKFORCE.WORKFORCE_NAME = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, m.getWorkForce());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int budget = rs.getInt("BUDGET");
				return budget;
			}else {
				System.out.println("manager not found");
			}
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return 0;
		
	}

	@Override
	public boolean updateManagerBudget(int b,Manager m) {
		try (Connection con = ConnectionUtils.createConnection()){
			String sql = "UPDATE WORKFORCE SET BUDGET = ? WHERE WORKFORCE_NAME = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, b);
			ps.setString(2, m.getWorkForce());

			ps.executeUpdate();
			return true;
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return false;
	}
	@Override
	public List<Employee> getEmployeesfromManagerWorkForce(Manager m) {
		List<Employee> employees = new ArrayList<Employee>();
		try (Connection con = ConnectionUtils.createConnection()){
			String sql = "SELECT * FROM EMPLOYEE WHERE WORKFORCE_NAME IN (SELECT WORKFORCE_NAME FROM MANAGER WHERE ID = ? )";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, m.getmId());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Employee e = new Employee(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(2),rs.getString(5));
				employees.add(e);
				
			}
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return employees;
	}
	

}
