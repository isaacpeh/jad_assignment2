package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.DatabaseConfig;

public class TourRecordManager {
	
	// INSERT BOOKING RECORD
	public int[] addRecord(List<TourRecord> tourRecord) {
		Connection con = DatabaseConfig.getConn();
		String sql = "INSERT INTO "
						+ "order_history (userid, tourid, quantity) "
					+ "VALUES "
						+ "(?,?,?)";
		PreparedStatement ps = null;

		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			for (int i = 0; i < tourRecord.size(); i++) {
				ps.setInt(1, tourRecord.get(i).getUserid());
				ps.setInt(2, tourRecord.get(i).getTourid());
				ps.setInt(3, tourRecord.get(i).getQuantity());
				ps.addBatch();
			}
			int result[] = ps.executeBatch();
			con.commit();
			con.setAutoCommit(true);
			return result;

		} catch (SQLException ex) {
			ex.printStackTrace();
			int result[] = { -1 };
			return result;

		} finally { 
		    try { if (ps != null) ps.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
	}
	
	// SHOW TOP 10 USERS BY ORDERS
	public List<User> showTopUsersOrder() {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT "
						+ "U.userid, "
						+ "U.username, "
						+ "U.email, "
						+ "U.contact, "
						+ "U.role, "
						+ "U.address, "
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
	
	// SHOW TOP 10 USERS BY VALUE
	public List<User> showTopUsersValue() {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT "
						+ "U.userid, "
						+ "U.username, "
						+ "U.email, "
						+ "U.contact, "
						+ "U.role, "
						+ "U.address, "
						+ "sum(T.price * O.quantity) AS total "
					+ "FROM "
						+ "user AS U, "
						+ "order_history AS O, "
						+ "tour AS T "
					+ "WHERE "
						+ "U.userid = O.userid AND "
						+ "O.tourid = T.tourid "
					+ "GROUP BY "
						+ "username "
					+ "ORDER BY "
						+ "total DESC "
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
				tempUser.setValue(rs.getInt("total"));
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
	
	// GET BOOKING RECORDS BY DATE RANGE
	public List<TourRecord> getRecordByDate(String dateFrom, String dateTo) {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT "
						+ "U.userid, "
						+ "U.username, "
						+ "T.tourid, "
						+ "T.tourname, "
						+ "O.quantity, "
						+ "O.purchased_at "
					+ "FROM "
						+ "user AS U, "
						+ "tour AS T, "
						+ "order_history AS O "
					+ "WHERE "
				+ "O.purchased_at >= ? "
					+ "AND "
				+ "O.purchased_at <= ?  "
					+ "AND "
						+ "T.tourid = O.tourid "
					+ "AND "
						+ "U.userid = O.userid";
		
		ResultSet rs = null; 
		PreparedStatement ps = null;
		ArrayList<TourRecord> result = new ArrayList<TourRecord>();
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dateFrom);
			ps.setString(2, dateTo);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				TourRecord tempTourRecord = new TourRecord();
				tempTourRecord.setUserid(rs.getInt("userid"));
				tempTourRecord.setUsername(rs.getString("username"));
				tempTourRecord.setTourid(rs.getInt("tourid"));
				tempTourRecord.setTourname(rs.getString("tourname"));
				tempTourRecord.setQuantity(rs.getInt("quantity"));
				tempTourRecord.setPurchased_at(rs.getString("purchased_at"));
				result.add(tempTourRecord);
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

	// GET USER BY BOOKING RECORD (USERS WHO BOOK CERTAIN TOURS)
	public List<TourRecord> getUserByRecord(int tourid) {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT "
						+ "U.userid, "
						+ "U.username, "
						+ "T.tourid, "
						+ "T.tourname, "
						+ "O.quantity, "
						+ "O.purchased_at "
					+ "FROM "
						+ "user AS U, "
						+ "tour AS T, "
						+ "order_history AS O "
					+ "WHERE "
						+ "T.tourid = ? "
					+ "AND "
						+ "T.tourid = O.tourid "
					+ "AND "
						+ "U.userid = O.userid";
		
		ResultSet rs = null; 
		PreparedStatement ps = null;
		ArrayList<TourRecord> result = new ArrayList<TourRecord>();
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, tourid);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				TourRecord tempTourRecord = new TourRecord();
				tempTourRecord.setUserid(rs.getInt("userid"));
				tempTourRecord.setUsername(rs.getString("username"));
				tempTourRecord.setTourid(rs.getInt("tourid"));
				tempTourRecord.setTourname(rs.getString("tourname"));
				tempTourRecord.setQuantity(rs.getInt("quantity"));
				tempTourRecord.setPurchased_at(rs.getString("purchased_at"));
				result.add(tempTourRecord);
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
		
	// GET TOUR RECORD BY USER (USER PROFILE HISTORY)
	public List<TourRecord> getRecordByUser(int userid) {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT "
						+ "O.tourid, "
						+ "T.tourname, "
						+ "O.quantity, "
						+ "O.purchased_at "
					+ "FROM "
						+ "order_history AS O, "
						+ "tour AS T "
					+ "WHERE "
						+ "T.tourid = O.tourid AND "
						+ "O.userid = ?";
		
		ResultSet rs = null; 
		PreparedStatement ps = null;
		ArrayList<TourRecord> result = new ArrayList<TourRecord>();
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, userid);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				TourRecord tempTourRecord = new TourRecord();
				tempTourRecord.setTourname(rs.getString("tourname"));
				tempTourRecord.setQuantity(rs.getInt("quantity"));
				tempTourRecord.setPurchased_at(rs.getString("purchased_at"));
				result.add(tempTourRecord);
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
	
	// SHOW BEST AND LEAST SELLING CATEGORIES
	public List<TourRecord> showBestCategories() {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT "
						+ "C.categoryid, "
						+ "C.category, "
						+ "sum(O.quantity) as total_purchases, "
						+ "count(C.category) as total_orders "
					+ "FROM "
						+ "tour AS T, "
						+ "category AS C, "
						+ "tour_category AS TC, "
						+ "order_history AS O "
					+ "WHERE "
						+ "T.tourid = TC.tourid AND "
						+ "T.tourid = O.tourid AND "
						+ "C.categoryid = TC.categoryid "
					+ "GROUP BY "
						+ "C.category "
					+ "ORDER BY "
						+ "total_purchases DESC";
		
		ResultSet rs = null; 
		Statement stmt= null;
		List<TourRecord> result = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			result = new ArrayList<>();

			while (rs.next()) {
				TourRecord tempRecord = new TourRecord();
				tempRecord.setCategoryid(rs.getInt("categoryid"));
				tempRecord.setCategory(rs.getString("category"));
				tempRecord.setTotal_purchases(rs.getInt("total_purchases"));
				tempRecord.setTotal_orders(rs.getInt("total_orders"));
				result.add(tempRecord);
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

	// SHOW TOUR RECORDS BY CATEGORY ASC
	// SHOW TOUR RECORDS BY TOUR ASC
	// SHOW TOUR RECORD BY INDIVIDUAL TOUR SAME AS USER WHO BOOK ... ? MAYBE
	// COMBINE?
}
