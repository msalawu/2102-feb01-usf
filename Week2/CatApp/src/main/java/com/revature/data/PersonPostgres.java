package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonPostgres implements PersonDAO {

	@Override
	public Person getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Person t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Person t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Person add(Person p) throws NonUniqueUsernameException {
Person p = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into person values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getPassword());
			pstmt.setInt(3, t.getRole().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				p = t;
				p.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public Person getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Set<Cat> getCatsByPersonId(Integer id, Connection conn) throws SQLException {
		Set<Cat> cats = new HashSet<>();
		CatDAO catDao = new CatPostgres();
		
		String sql = "select * from person_cat where person_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Cat pet = catDao.getById(rs.getInt("cat_id"));
			cats.add(pet);
		}
		
		return cats;
	}

}
