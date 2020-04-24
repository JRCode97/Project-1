package dev.rivera.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dev.rivera.backend.ConnectionUtils;
import dev.rivera.entities.WorkForce;

public class WorkForceDAOdb implements WorkForceDAO {

	@Override
	public WorkForce updateWorkForce(WorkForce wf) {
		try (Connection con = ConnectionUtils.createConnection()){
			String sql = "UPDATE WORKFORCE SET WORKFORCE_NAME = ?,BUDGET = ? WHERE WORKFORCE_NAME=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, wf.getName());
			ps.setInt(2, wf.getBudget());
			ps.setString(3, wf.getName());

			ps.executeUpdate();
			return wf;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return wf;
	}

}
