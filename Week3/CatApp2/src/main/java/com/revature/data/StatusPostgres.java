package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class StatusPostgres implements StatusDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private Logger log = Logger.getLogger(StatusPostgres.class);

	@Override
	public Status getById(Integer id) {
		Status status = null;
		try(Connection conn = (Connection) ConnectionUtil.getConnectionUtil()){
			String sql = ("select * from status where id = ?");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				status = new Status();
				status.setId(rs.getInt("id"));
				status.setName(rs.getString("Name"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}


	@Override
	public Set<Status> getAll() {
		Set<Status> statuses = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "select * from status";
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Status s = new Status();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				
				statuses.add(s);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statuses;
	}

	@Override
	public void update(Status t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "UPDATE status " +
						 "SET name = ? " +
						 "WHERE id = ?";
			
			PreparedStatement prepSt = conn.prepareStatement(sql);
			
			prepSt.setString(1, t.getName());
			prepSt.setInt(2, t.getId());
			
			int rowsAffected = prepSt.executeUpdate();
			if (rowsAffected > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Status t) {
		try(Connection conn = cu.getConnection()){
			log.info("Attempting to delete status: "+t.toString());
			conn.setAutoCommit(false);

			String sql = "delete from status where name =?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, t.getName());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				conn.commit();
				log.info("Deleted status: "+t.toString());
				
			} else {
				conn.rollback();
				log.info("Error deleting status: "+t.toString());
			}
		}catch(Exception e) {
			log.debug("Exception thrown when trying to delete status: "+t.toString());
			e.printStackTrace();
		}

	}

	@Override
	public Status add(Status s) {
		Status newStatus = null;
		try(Connection conn = cu.getConnection()){
			log.info("Attempting to add status: "+s.toString());
			conn.setAutoCommit(false);
			String sql = "insert into status values (default,?)";
			String[] keys = {"id"};
			PreparedStatement stmt = conn.prepareStatement(sql,keys);
			stmt.setString(1,s.getName());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				log.info("New status added: "+s.toString());
				newStatus = s;
				newStatus.setId(rs.getInt(1));
				conn.commit();
			}else {
				log.debug("Attempted to add a status but an error occured");
				conn.rollback();
			}
		}catch (Exception e) {
			log.debug("Exception thrown: "+e.getMessage());
			
			e.printStackTrace();
		}
		return newStatus;
	}

}
