/* 
	GROUP 3
	DIT/2A/01
	HA JIN 		P2100030
	ISAAC		P2107251
	GEORGE		P2143990
 */

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.DatabaseConfig;

public class UserManager {

	// VERIFY USER
	public int verifyUser(String username, String password) {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT "
						+ "* "
					+ "FROM "
						+ "user "
					+ "WHERE "
						+ "username = ? AND "
						+ "password = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("userid");

			} else {
				return 0;
			}

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			return -1;

		} finally {
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (ps != null) ps.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}

	}
	
	// SHOW TOP 10 USERS BY PURCHASE VALUE
	public List<User> showTopUsers() {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT "
						+ "U.userid, "
						+ "U.username, "
						+ "U.email, "
						+ "U.contact, "
						+ "U.role, "
						+ "U.address, "
						+ "O.quantity, "
						+ "O.tourid, "
						+ "count(U.username) AS purchases "
					+ "FROM "
						+ "user AS U, "
						+ "order_history AS O "
					+ "WHERE "
						+ "U.userid = O.userid "
					+ "GROUP BY "
						+ "username "
					+ "ORDER BY "
						+ "purchases DESC "
					+ "LIMIT 10";
		ResultSet rs = null; 
		Statement stmt= null;
		List<User> result = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			result = new ArrayList<>();

			while (rs.next()) {
				User tempUser = new User();
				tempUser.setUserid(rs.getInt("userid"));
				tempUser.setUsername(rs.getString("username"));
				tempUser.setEmail(rs.getString("email"));
				tempUser.setContact(rs.getString("contact"));
				tempUser.setRole(rs.getString("role"));
				tempUser.setAddress(rs.getString("address"));
				tempUser.setPurchases(rs.getInt("purchases"));
				result.add(tempUser);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (stmt != null) stmt.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
		return result;
	}
	
	// SHOW ALL USERS BY ADDRESS
	public List<User> showAllUsersAddr() {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT * FROM user ORDER BY address ASC";
		ResultSet rs = null; 
		Statement stmt= null;
		List<User> result = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			result = new ArrayList<>();

			while (rs.next()) {
				User tempUser = new User();
				tempUser.setUserid(rs.getInt("userid"));
				tempUser.setUsername(rs.getString("username"));
				tempUser.setEmail(rs.getString("email"));
				tempUser.setContact(rs.getString("contact"));
				tempUser.setRole(rs.getString("role"));
				tempUser.setAddress(rs.getString("address"));
				result.add(tempUser);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (stmt != null) stmt.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
		return result;
	}
	
	// SHOW ALL INACTIVE USERS (90 DAYS)
	public List<User> showAllUsersInactive(String date) {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT "
						+ "* "
					+ "FROM "
						+ "user "
					+ "WHERE "
						+ "last_logged_in < ? "
					+ "ORDER BY "
						+ "last_logged_in ASC";

		ResultSet rs = null; 
		PreparedStatement ps = null;
		List<User> result = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, date);
			rs = ps.executeQuery();
			result = new ArrayList<>();

			while (rs.next()) {
				User tempUser = new User();
				tempUser.setUserid(rs.getInt("userid"));
				tempUser.setUsername(rs.getString("username"));
				tempUser.setEmail(rs.getString("email"));
				tempUser.setContact(rs.getString("contact"));
				tempUser.setRole(rs.getString("role"));
				tempUser.setAddress(rs.getString("address"));
				result.add(tempUser);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (ps != null) ps.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
		return result;
	}
	
	// SHOW ALL USERS BY NUMBER
	public List<User> showAllUsersNumber(String num) {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT "
						+ "* "
					+ "FROM "
						+ "user "
					+ "WHERE "
						+ "contact LIKE N? "
					+ "ORDER BY "
						+ "contact ASC";

		ResultSet rs = null; 
		PreparedStatement ps = null;
		List<User> result = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, '%' + num + '%');
			rs = ps.executeQuery();
			result = new ArrayList<>();

			while (rs.next()) {
				User tempUser = new User();
				tempUser.setUserid(rs.getInt("userid"));
				tempUser.setUsername(rs.getString("username"));
				tempUser.setEmail(rs.getString("email"));
				tempUser.setContact(rs.getString("contact"));
				tempUser.setRole(rs.getString("role"));
				tempUser.setAddress(rs.getString("address"));
				result.add(tempUser);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (ps != null) ps.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
		return result;
	}
	
	// SHOW ALL USERS BY NAME
	public List<User> showAllUsersName(String name) {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT "
						+ "* "
					+ "FROM "
						+ "user "
					+ "WHERE "
						+ "username LIKE N? "
					+ "ORDER BY "
						+ "username ASC";

		ResultSet rs = null; 
		PreparedStatement ps = null;
		List<User> result = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, '%' + name + '%');
			rs = ps.executeQuery();
			result = new ArrayList<>();

			while (rs.next()) {
				User tempUser = new User();
				tempUser.setUserid(rs.getInt("userid"));
				tempUser.setUsername(rs.getString("username"));
				tempUser.setEmail(rs.getString("email"));
				tempUser.setContact(rs.getString("contact"));
				tempUser.setRole(rs.getString("role"));
				tempUser.setAddress(rs.getString("address"));
				result.add(tempUser);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (ps != null) ps.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
		return result;
	}
		
	// ADD USER
	public int addUser(User user) {
		Connection con = DatabaseConfig.getConn();
		String sql = "INSERT INTO "
						+ "user (username, email, contact, password, role) "
					+ "VALUES "
						+ "(?,?,?,?,?)";
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getContact());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getRole());
			int result = ps.executeUpdate();
			return result;

		} catch (SQLException ex) {
			ex.printStackTrace();

			if (ex.getMessage().contains("Duplicate entry")) {
				return -1;

			} else {
				return 0;
			}

		} finally { 
		    try { if (ps != null) ps.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
	}

	// SHOW ONE USER
	
	// SHOW USER
	public User showUser(int userid) {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT "
						+ "* "
					+ "FROM "
						+ "user "
					+ "WHERE "
						+ "userid = ?";
		PreparedStatement ps = null;
		User tempuser = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, userid);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				tempuser = new User();
				tempuser.setUsername(rs.getString("username"));
				tempuser.setEmail(rs.getString("email"));
				tempuser.setContact(rs.getString("contact"));
				tempuser.setRole(rs.getString("role"));
				tempuser.setPicUrl(rs.getString("profile_pic_url"));
				tempuser.setPassword(rs.getString("password"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (ps != null) ps.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
		return tempuser;

	}
	
	// UPDATE USER
	public int updateUser(int userid, User user) {
		Connection con = DatabaseConfig.getConn();
		String sql = "UPDATE "
						+ "user "
					+ "SET "
						+ "username = ?, "
						+ "email = ?, "
						+ "contact = ?, "
						+ "password = ?, "
						+ "profile_pic_url = ? "
					+ "WHERE "
						+ "userid = ?";
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getContact());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getPicUrl());
			ps.setInt(6, userid);
			int result = ps.executeUpdate();
			return result;

		} catch (SQLException ex) {
			ex.printStackTrace();

			if (ex.getMessage().contains("Duplicate entry")) {
				return -1;

			} else {
				return 0;
			}

		} finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
	}
	
	// SHOW ALL USERS
	public List<User> showAllUsers() {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT * FROM user";
		ResultSet rs = null;
		Statement stmt = null;
		List<User> result = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			result = new ArrayList<>();

			while (rs.next()) {
				User tempUser = new User();
				tempUser.setUserid(rs.getInt("userid"));
				tempUser.setUsername(rs.getString("username"));
				tempUser.setEmail(rs.getString("email"));
				tempUser.setContact(rs.getString("contact"));
				tempUser.setRole(rs.getString("role"));
				result.add(tempUser);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (stmt != null) stmt.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
		return result;
	}
	
	// DELETE USER
	public int deleteUser(int userid) {
		Connection con = DatabaseConfig.getConn();
		String sql = "DELETE FROM "
						+ "user "
					+ "WHERE "
						+ "userid = ?";
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, userid);
			int result = ps.executeUpdate();
			return result;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return 0;

		} finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}

	}
}
