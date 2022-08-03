/* 
	GROUP 3
	DIT/2A/01
	HA JIN 		P2100030
	ISAAC		P2107251
	GEORGE		P2143990
 */

package dbaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TourDAO {

	// SHOW ALL TOURS
	public List<Tour> showTours() {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT DISTINCT "
						+ "T.tourid, " 
						+ "T.tourname, " 
						+ "T.brief_description, " 
						+ "T.detailed_description, " 
						+ "T.price, "
						+ "T.slots_available, "
						+ "I.tour_pic_url " 
					+ "FROM " 
						+ "tour AS T , " 
						+ "tour_img AS I " 
					+ "WHERE "
						+ "T.tourid = I.tourid ";

		Statement stmt = null;
		ResultSet rs = null; 
		List<Tour> result = null;
		
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			result = new ArrayList<>();
			List<String> imgArr = new ArrayList<>();
			Tour tempTour = new Tour();
			String prevTour = "";
			
			if (rs.next()) { // Tour 1
				prevTour = rs.getString("tourname");
				tempTour.setTourid(rs.getInt("tourid"));
				tempTour.setTourName(rs.getString("tourname"));
				tempTour.setbDescription(rs.getString("brief_description"));
				tempTour.setdDescription(rs.getString("detailed_description"));
				tempTour.setPrice(rs.getDouble("price"));
				tempTour.setSlotsAvailable(rs.getInt("slots_available"));
			}

			rs.beforeFirst();
			while (rs.next()) {
				if (!prevTour.equals(rs.getString("tourname"))) { // if current not same as previous

					if (!imgArr.isEmpty()) { // from previous loop
						tempTour.setPicUrl(imgArr); // set imageArr to object
						result.add(tempTour);
					}

					tempTour = new Tour(); // clear tour object
					imgArr = new ArrayList<>(); // clear array
					prevTour = rs.getString("tourname"); // update previous to current
					
					tempTour.setTourid(rs.getInt("tourid"));
					tempTour.setTourName(rs.getString("tourname"));
					tempTour.setbDescription(rs.getString("brief_description"));
					tempTour.setdDescription(rs.getString("detailed_description"));
					tempTour.setPrice(rs.getDouble("price"));
					tempTour.setSlotsAvailable(rs.getInt("slots_available"));
					imgArr.add(rs.getString("tour_pic_url")); // add image to array

				} else { // if current same as previous
					prevTour = rs.getString("tourname"); // update previous to current
					imgArr.add(rs.getString("tour_pic_url")); // add image to array
				}
			}

			// For last object
			if (!imgArr.isEmpty()) { // from previous loop
				tempTour.setPicUrl(imgArr); // set imageArr to object
				result.add(tempTour);
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
	
	// SHOW TOUR BY CATEGORY ONLY
	public List<Tour> showToursCategory(int catid) {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT "
						+ "T.tourid, "
						+ "T.tourname, " 
						+ "T.brief_description, " 
						+ "T.detailed_description, " 
						+ "T.price, "
						+ "T.slots_available, " 
						+ "I.tour_pic_url, " 
						+ "C.category " 
					+ "FROM " 
						+ "tour AS T, " 
						+ "tour_category AS TC, " 
						+ "category AS C, "
						+ "tour_img AS I "
					+ "WHERE "
						+ "T.tourid = I.tourid AND "
						+ "T.tourid = TC.tourid AND " 
						+ "TC.categoryid = C.categoryid AND "
						+ "TC.categoryid = ? ";

		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Tour> result = null;

		try {
			ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, catid);
			rs = ps.executeQuery();
			result = new ArrayList<>();
			List<String> imgArr = new ArrayList<>();
			Tour tempTour = new Tour();
			String prevTour = "";

			if (rs.next()) { // Tour 1
				prevTour = rs.getString("tourname");
				tempTour.setTourid(rs.getInt("tourid"));
				tempTour.setTourName(rs.getString("tourname"));
				tempTour.setbDescription(rs.getString("brief_description"));
				tempTour.setdDescription(rs.getString("detailed_description"));
				tempTour.setPrice(rs.getDouble("price"));
				tempTour.setSlotsAvailable(rs.getInt("slots_available"));
				tempTour.setCategory(rs.getString("category"));
			}

			rs.beforeFirst();
			while (rs.next()) {
				if (!prevTour.equals(rs.getString("tourname"))) { // if current not same as previous

					if (!imgArr.isEmpty()) { // from previous loop
						tempTour.setPicUrl(imgArr); // set imageArr to object
						result.add(tempTour);
					}

					tempTour = new Tour(); // clear tour object
					imgArr = new ArrayList<>(); // clear array
					prevTour = rs.getString("tourname"); // update previous to current

					tempTour.setTourid(rs.getInt("tourid"));
					tempTour.setTourName(rs.getString("tourname"));
					tempTour.setbDescription(rs.getString("brief_description"));
					tempTour.setdDescription(rs.getString("detailed_description"));
					tempTour.setPrice(rs.getDouble("price"));
					tempTour.setSlotsAvailable(rs.getInt("slots_available"));
					tempTour.setCategory(rs.getString("category"));
					imgArr.add(rs.getString("tour_pic_url")); // add image to array

				} else { // if current same as previous
					prevTour = rs.getString("tourname"); // update previous to current
					imgArr.add(rs.getString("tour_pic_url")); // add image to array
				}
			}

			// For last object
			if (!imgArr.isEmpty()) { // from previous loop
				tempTour.setPicUrl(imgArr); // set imageArr to object
				result.add(tempTour);
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
	
	// SHOW ONE TOUR
	public Tour showTour(int tourid) {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT "
						+ "* "
					+ "FROM "
						+ "tour "
					+ "WHERE "
						+ "tourid = ?";
		PreparedStatement ps = null;
		Tour tempTour = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, tourid);
			rs = ps.executeQuery();
			System.out.println(tourid);
			if(rs.next()) {
				tempTour = new Tour();
				tempTour.setTourName(rs.getString("tourname"));
				tempTour.setbDescription(rs.getString("brief_description"));
				tempTour.setdDescription(rs.getString("detailed_description"));
				tempTour.setPrice(rs.getDouble("price"));
				tempTour.setSlotsAvailable(rs.getInt("slots_available"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (ps != null) ps.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
		return tempTour;

	}
	
	// UPDATE TOUR
	public int updateTour(int tourid, int slots) {
		Connection con = DatabaseConfig.getConn();
		String sql = "UPDATE "
						+ "tour "
					+ "SET "
						+ "slots_available = ? "
					+ "WHERE "
						+ "tourid = ?";
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, slots);
			ps.setInt(2, tourid);
			result = ps.executeUpdate();
	
		} catch (SQLException ex) {
			ex.printStackTrace();
	
		} finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
		return result;
	}

	// GET TOUR SLOTS
	public int getSlot(int tourid) {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT "
						+ "slots_available "
					+ "FROM "
						+ "tour "
					+ "WHERE "
						+ "tourid = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = -1;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, tourid);
			rs = ps.executeQuery();

			if (rs.next()) {
				result = rs.getInt("slots_available");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
	
		} finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
		return result;
	}
	
}
