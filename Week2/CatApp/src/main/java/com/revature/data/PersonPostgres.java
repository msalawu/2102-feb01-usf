package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private Logger log = Logger.getLogger(PersonPostgres.class);
	
	@Override
	public Person getById(Integer id) {
		Person person = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select person.id as person_id, user_role.id as role_id, username, passwd, "
					+ "user_role.name as role_name from person join user_role on user_role_id = user_role.id "
					+ "where person.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				person = new Person();
				person.setId(rs.getInt("person_id"));
				person.setUsername(rs.getString("username"));
				person.setPassword(rs.getString("passwd"));
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setName(rs.getString("role_name"));
				person.setRole(role);
				
				person.setCats(getCatsByPersonId(person.getId(), conn));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return person;
	}

	@Override
	public Set<Person> getAll() {
		Set<Person> people = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select person.id as person_id, user_role.id as role_id, username, passwd, "
					+ "user_role.name as role_name from person join user_role on user_role_id = user_role.id";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next()) {
				Person human = new Person();
				human.setId(rs.getInt("person_id"));
				human.setUsername(rs.getString("username"));
				human.setPassword(rs.getString("passwd"));
				Role job = new Role();
				job.setId(rs.getInt("role_id"));
				job.setName(rs.getString("role_name"));
				human.setRole(job);
				
				human.setCats(getCatsByPersonId(human.getId(), conn));
				
				people.add(human);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return people;
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
		Person newPerson = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into person values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, p.getUsername());
			pstmt.setString(2, p.getPassword());
			pstmt.setInt(3, p.getRole().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				newPerson = p;
				newPerson.setId(rs.getInt(1));
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
		
		return newPerson;
	}

	@Override
	public Person getByUsername(String username) {
		Person human = null;
		
		try (Connection conn = cu.getConnection())
		{
			String sql = "select person.id as person_id, user_role.id as role_id, username, passwd, "
					+ "user_role.name as role_name from person "
					+ "join user_role on user_role_id = user_role.id where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				human = new Person();
				human.setUsername(rs.getString("username"));
				human.setId(rs.getInt("person_id"));
				human.setPassword(rs.getString("passwd"));
				Role job = new Role();
				job.setId(rs.getInt("role_id"));
				job.setName(rs.getString("role_name"));
				human.setRole(job);
				human.setCats(getCatsByPersonId(human.getId(), conn));
			}
						
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return human;
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
