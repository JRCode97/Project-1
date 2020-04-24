package dev.rivera.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.rivera.backend.ConnectionUtils;
import dev.rivera.entities.Employee;
import dev.rivera.entities.Reimbursement;
import dev.rivera.daos.*;

public class ReimbursementDAOdb implements ReimbursementDAO {
	public Reimbursement createReimbursement(Reimbursement r) {
		
		try(Connection con = ConnectionUtils.createConnection()) {
			
			
			String Query = "INSERT into REIMBURSEMENT VALUES (0,?,?,?,?)";
			PreparedStatement post = con.prepareStatement(Query,Statement.RETURN_GENERATED_KEYS);
			post.setInt(1, r.getAmount());
			post.setString(2, r.getDescription());
			post.setString(3, r.getStatus());
			post.setInt(4, r.getRequesterId());
			post.execute();
			ResultSet rs = post.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("ID");
			r.setrId(key);
			return r;
			
	}catch(Exception e){
		System.out.println(e);
	}
		return r;
	}
	public List<Reimbursement> getAllReimbursements() {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try(Connection con = ConnectionUtils.createConnection()) {
			
			
			String Query = "SELECT * FROM REIMBURSEMENT";
			PreparedStatement recieve = con.prepareStatement(Query);
			ResultSet rs = recieve.executeQuery();
			
			while(rs.next()) {
				System.out.println("found reimbursement");
				Reimbursement currentReimbursement = new Reimbursement(rs.getInt("ID"),rs.getInt("AMOUNT"),rs.getString("DESCRIPTION"),rs.getInt("REQUESTERID"),rs.getString("STATUS"));
				System.out.println(currentReimbursement);
				reimbursements.add(currentReimbursement);
			}if(!rs.next())
				System.out.println("no reimbursements found");
	}catch(SQLException e){
		e.printStackTrace();
	}
	return reimbursements;
	}
	public List<Reimbursement> getPendingReimbursements(int id) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try(Connection con = ConnectionUtils.createConnection()) {
			
			
			String Query = "SELECT * FROM REIMBURSEMENT WHERE STATUS = 'pending' and REIMBURSEMENT.REQUESTERID IN(SELECT ID FROM EMPLOYEE WHERE WORKFORCE_NAME IN (SELECT WORKFORCE_NAME FROM MANAGER WHERE ID = ?) );";
			PreparedStatement recieve = con.prepareStatement(Query);
			recieve.setInt(1,id);
			ResultSet rs = recieve.executeQuery();
			
			while(rs.next()) {
				System.out.println("found reimbursement");
				Reimbursement currentReimbursement = new Reimbursement(rs.getInt("ID"),rs.getInt("AMOUNT"),rs.getString("DESCRIPTION"),rs.getInt("REQUESTERID"),rs.getString("STATUS"));
				System.out.println(currentReimbursement);
				reimbursements.add(currentReimbursement);
			}if(!rs.next())
				System.out.println("no reimbursements found");
	}catch(SQLException e){
		e.printStackTrace();
	}
	return reimbursements;
	}
	
	public List<Reimbursement> getDeniedReimbursements(int id) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try(Connection con = ConnectionUtils.createConnection()) {
			
			
			String Query = "SELECT * FROM REIMBURSEMENT WHERE STATUS = 'denied' and REIMBURSEMENT.REQUESTERID IN(SELECT ID FROM EMPLOYEE WHERE WORKFORCE_NAME IN (SELECT WORKFORCE_NAME FROM MANAGER WHERE ID = ?) );";
			PreparedStatement recieve = con.prepareStatement(Query);
			recieve.setInt(1,id);
			ResultSet rs = recieve.executeQuery();
			
			while(rs.next()) {
				System.out.println("found reimbursement");
				Reimbursement currentReimbursement = new Reimbursement(rs.getInt("ID"),rs.getInt("AMOUNT"),rs.getString("DESCRIPTION"),rs.getInt("REQUESTERID"),rs.getString("STATUS"));
				System.out.println(currentReimbursement);
				reimbursements.add(currentReimbursement);
			}if(!rs.next())
				System.out.println("no reimbursements found");
	}catch(SQLException e){
		e.printStackTrace();
	}
	return reimbursements;
	}
	
	public List<Reimbursement> getApprovedReimbursements(int id) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try(Connection con = ConnectionUtils.createConnection()) {
			
			
			String Query = "SELECT * FROM REIMBURSEMENT WHERE STATUS = 'approved' and REIMBURSEMENT.REQUESTERID IN(SELECT ID FROM EMPLOYEE WHERE WORKFORCE_NAME IN (SELECT WORKFORCE_NAME FROM MANAGER WHERE ID = ?) );";
			PreparedStatement recieve = con.prepareStatement(Query);
			recieve.setInt(1,id);
			ResultSet rs = recieve.executeQuery();
			
			while(rs.next()) {
				System.out.println("found reimbursement");
				Reimbursement currentReimbursement = new Reimbursement(rs.getInt("ID"),rs.getInt("AMOUNT"),rs.getString("DESCRIPTION"),rs.getInt("REQUESTERID"),rs.getString("STATUS"));
				System.out.println(currentReimbursement);
				reimbursements.add(currentReimbursement);
			}if(!rs.next())
				System.out.println("no reimbursements found");
	}catch(SQLException e){
		e.printStackTrace();
	}
	return reimbursements;
	}
	
	
	public Reimbursement getReimbursementbyID(int id) {
		Reimbursement reimbursement = null;
try(Connection con = ConnectionUtils.createConnection()) {
			
			
			String Query = "SELECT * FROM REIMBURSEMENT WHERE ID = ?";
			PreparedStatement recieve = con.prepareStatement(Query);
			recieve.setInt(1, id);
			ResultSet rs = recieve.executeQuery();
			
			if(rs.next()) {
				System.out.println("found reimbursement");
				 reimbursement = new Reimbursement(rs.getInt("ID"),rs.getInt("AMOUNT"),rs.getString("DESCRIPTION"),rs.getInt("REQUESTERID"),rs.getString("STATUS"));
				System.out.println(reimbursement);
				return reimbursement;
			}if(!rs.next())
				System.out.println("no reimbursements found");
	}catch(Exception e){
		e.printStackTrace();
	}
	return reimbursement;
	}

	public List<Reimbursement> getReimbursementByEmployee(Employee e) {
		Reimbursement reimbursement = null;
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		try(Connection con = ConnectionUtils.createConnection()) {
					
					
					String Query = "SELECT REIMBURSEMENT.ID,REIMBURSEMENT.AMOUNT,REIMBURSEMENT.DESCRIPTION,REIMBURSEMENT.STATUS,REIMBURSEMENT.REQUESTERID FROM REIMBURSEMENT,EMPLOYEE WHERE REIMBURSEMENT.REQUESTERID = EMPLOYEE.ID AND REIMBURSEMENT.REQUESTERID = ? ";
					PreparedStatement recieve = con.prepareStatement(Query);
					recieve.setInt(1, e.geteId());
					ResultSet rs = recieve.executeQuery();

					while(rs.next()) {
						System.out.println("found reimbursement");
						 reimbursement = new Reimbursement(rs.getInt("ID"),rs.getInt("AMOUNT"),rs.getString("DESCRIPTION"),rs.getInt("REQUESTERID"),rs.getString("STATUS"));
						System.out.println(reimbursement);
						reimbursements.add(reimbursement);
						
					}
					if(!rs.next())
						System.out.println("no reimbursements found");
			}catch(Exception ex){
				System.out.println(ex);
			}
			return reimbursements;
	}

	public Reimbursement UpdateReimbursement(Reimbursement r) {
		try (Connection con = ConnectionUtils.createConnection()){
			String sql = "UPDATE REIMBURSEMENT SET ID = ?,AMOUNT = ?,DESCRIPTION = ?,STATUS = ?,REQUESTERID = ? WHERE ID =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, r.getrId());
			ps.setInt(2, r.getAmount());
			ps.setString(3, r.getDescription());
			ps.setString(4, r.getStatus());
			ps.setInt(5, r.getRequesterId());
			ps.setInt(6, r.getrId());
			ps.executeUpdate();
			return r;
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return r;
	}

	public boolean DeleteReimbursement(Reimbursement r) {
		try (Connection con = ConnectionUtils.createConnection()){
			String Query = "DELETE FROM REIMBURSEMENT WHERE ID = ?";
			PreparedStatement post = con.prepareStatement(Query);
			post.setInt(1,r.getrId());
			boolean success = post.execute();
			return success;
		}catch(Exception ex){
			System.out.println(ex);
		}
			return false;
	}
	@Override
	public String getMostReimbursementMaker() {
		String most="";
		try(Connection con = ConnectionUtils.createConnection()) {
			
			
			String Query = "SELECT EMPLOYEE.NAME,COUNT(REIMBURSEMENT.ID) AS TOT FROM REIMBURSEMENT,EMPLOYEE WHERE REIMBURSEMENT.REQUESTERID = EMPLOYEE.ID GROUP BY REQUESTERID ORDER BY TOT DESC;";
			PreparedStatement recieve = con.prepareStatement(Query);
			ResultSet rs = recieve.executeQuery();
			
			if(rs.next()) {
				most = rs.getString("NAME");
				return most;
			}if(!rs.next())
				System.out.println("no records found");
	}catch(SQLException e){
		e.printStackTrace();
	}
		return most;
	}
	@Override
	public int getAverageReimbursementAmount() {
		int average = 0;
		try(Connection con = ConnectionUtils.createConnection()) {
			
			
			String Query = "SELECT AVG(AMOUNT)AS avg FROM REIMBURSEMENT";
			PreparedStatement recieve = con.prepareStatement(Query);
			ResultSet rs = recieve.executeQuery();
			
			if(rs.next()) {
				average = rs.getInt("avg");
				return average;
			}if(!rs.next())
				System.out.println("no records found");
	}catch(SQLException e){
		e.printStackTrace();
	}
		return average;
	}
	@Override
	public int getApprovedReimbursementAmount() {
		int average = 0;
		try(Connection con = ConnectionUtils.createConnection()) {
			
			
			String Query = "SELECT SUM(AMOUNT) AS sum FROM REIMBURSEMENT WHERE STATUS = 'approved'";			
			PreparedStatement recieve = con.prepareStatement(Query);
			ResultSet rs = recieve.executeQuery();
			
			if(rs.next()) {
				average = rs.getInt("sum");
				return average;
			}if(!rs.next())
				System.out.println("no records found");
	}catch(SQLException e){
		e.printStackTrace();
	}
		return average;
	}
	@Override
	public int getDeniedReimbursementAmount() {
		int average = 0;
		try(Connection con = ConnectionUtils.createConnection()) {
			
			
			String Query = "SELECT SUM(AMOUNT) AS sum FROM REIMBURSEMENT WHERE STATUS = 'denied'";
			PreparedStatement recieve = con.prepareStatement(Query);
			ResultSet rs = recieve.executeQuery();
			
			if(rs.next()) {
				average = rs.getInt("sum");
				return average;
			}if(!rs.next())
				System.out.println("no records found");
	}catch(SQLException e){
		e.printStackTrace();
	}
		return average;
	}
	@Override
	public String getMostReimbursementMakerforWorkForce(int id) {
		String most="";
		try(Connection con = ConnectionUtils.createConnection()) {
			
			
			String Query = "SELECT EMPLOYEE.NAME,COUNT(REIMBURSEMENT.ID) AS TOT FROM REIMBURSEMENT,EMPLOYEE WHERE REIMBURSEMENT.REQUESTERID = EMPLOYEE.ID AND EMPLOYEE.WORKFORCE_NAME IN (SELECT WORKFORCE_NAME FROM MANAGER WHERE ID = ? ) GROUP BY REQUESTERID ORDER BY TOT DESC;";
			PreparedStatement recieve = con.prepareStatement(Query);
			recieve.setInt(1, id);
			ResultSet rs = recieve.executeQuery();
			
			if(rs.next()) {
				most = rs.getString("NAME");
				return most;
			}if(!rs.next())
				System.out.println("no records found");
	}catch(SQLException e){
		e.printStackTrace();
	}
		return most;
	}
	
	@Override
	public int getAverageReimbursementAmountforWorkForce(int id) {
		int average = 0;
		try(Connection con = ConnectionUtils.createConnection()) {
			
			
			String Query = "SELECT AVG(AMOUNT)AS avg FROM REIMBURSEMENT WHERE REQUESTERID IN (SELECT ID FROM EMPLOYEE WHERE WORKFORCE_NAME IN (SELECT WORKFORCE_NAME FROM MANAGER WHERE ID =?)) ";
			PreparedStatement recieve = con.prepareStatement(Query);
			recieve.setInt(1, id);
			ResultSet rs = recieve.executeQuery();
			
			if(rs.next()) {
				average = rs.getInt("avg");
				return average;
			}if(!rs.next())
				System.out.println("no records found");
	}catch(SQLException e){
		e.printStackTrace();
	}
		return average;
	}
	@Override
	public int getApprovedReimbursementAmountforWorkForce(int id) {
		int SUM = 0;
		try(Connection con = ConnectionUtils.createConnection()) {
			
			
			String Query = "SELECT SUM(AMOUNT) AS sum FROM REIMBURSEMENT WHERE STATUS = 'approved' AND REQUESTERID IN (SELECT ID FROM EMPLOYEE WHERE WORKFORCE_NAME IN (SELECT WORKFORCE_NAME FROM MANAGER WHERE ID =?))";			
			PreparedStatement recieve = con.prepareStatement(Query);
			recieve.setInt(1, id);
			ResultSet rs = recieve.executeQuery();
			
			if(rs.next()) {
				SUM = rs.getInt("sum");
				return SUM;
			}if(!rs.next())
				System.out.println("no records found");
	}catch(SQLException e){
		e.printStackTrace();
	}
		return SUM;
	}
	@Override
	public int getDeniedReimbursementAmountforWorkForce(int id) {
		int SUM = 0;
		try(Connection con = ConnectionUtils.createConnection()) {
			
			
			String Query = "SELECT SUM(AMOUNT) AS sum FROM REIMBURSEMENT WHERE STATUS = 'denied' AND REQUESTERID IN (SELECT ID FROM EMPLOYEE WHERE WORKFORCE_NAME IN (SELECT WORKFORCE_NAME FROM MANAGER WHERE ID =?))";			
			PreparedStatement recieve = con.prepareStatement(Query);
			recieve.setInt(1, id);
			ResultSet rs = recieve.executeQuery();
			
			if(rs.next()) {
				SUM = rs.getInt("sum");
				return SUM;
			}if(!rs.next())
				System.out.println("no records found");
	}catch(SQLException e){
		e.printStackTrace();
	}
		return SUM;
	}
}






