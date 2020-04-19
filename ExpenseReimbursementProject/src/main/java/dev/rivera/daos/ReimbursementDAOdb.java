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





}
