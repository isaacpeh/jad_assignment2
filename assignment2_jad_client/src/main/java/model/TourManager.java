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

public class TourManager {

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
	
	// SHOW TOUR BY POPULARITY (TOURRECORDS)
	public ArrayList<Tour> showToursPopularity() {
			Connection con = DatabaseConfig.getConn();
			String sql = "SELECT DISTINCT "
							+ "T.tourid, " 
							+ "T.tourname, " 
							+ "T.brief_description, " 
							+ "T.detailed_description, " 
							+ "T.price, "
							+ "T.slots_available, "
							+ "sum(O.quantity) AS sales " 
						+ "FROM " 
							+ "tour AS T , " 
							+ "order_history AS O " 
						+ "WHERE "
							+ "T.tourid = O.tourid "
						+ "GROUP BY "
							+ "T.tourid"
						+ "ORDER BY " 
							+ "sales DESC";

			Statement stmt = null;
			ResultSet rs = null; 
			ArrayList<Tour> result = new ArrayList<Tour>();
			
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);

				while (rs.next()) {
					Tour tempTour = new Tour();
					tempTour.setTourid(rs.getInt("tourid"));
					tempTour.setTourName(rs.getString("tourname"));
					tempTour.setbDescription(rs.getString("brief_description"));
					tempTour.setdDescription(rs.getString("detailed_description"));
					tempTour.setPrice(rs.getDouble("price"));
					tempTour.setSlotsAvailable(rs.getInt("slots_available"));
					tempTour.setTotalSales(rs.getInt("sales"));
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
		
	// SHOW TOUR BY 0 SALES
	public ArrayList<Tour> showToursNoSales() {
			Connection con = DatabaseConfig.getConn();
			String sql = "SELECT DISTINCT "
							+ "T.tourid, "
							+ "T.tourname, "
							+ "T.brief_description, "
							+ "T.detailed_description, "
							+ "T.price, "
							+ "T.slots_available "
						+ "FROM "
							+ "tour AS T "
						+ "LEFT JOIN "
							+ "order_history AS O "
						+ "ON"
							+ "T.tourid = O.tourid "
						+ "WHERE " 
							+ "O.tourid IS NULL";

			Statement stmt = null;
			ResultSet rs = null; 
			ArrayList<Tour> result = new ArrayList<Tour>();
			
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);

				while (rs.next()) {
					Tour tempTour = new Tour();
					tempTour.setTourid(rs.getInt("tourid"));
					tempTour.setTourName(rs.getString("tourname"));
					tempTour.setbDescription(rs.getString("brief_description"));
					tempTour.setdDescription(rs.getString("detailed_description"));
					tempTour.setPrice(rs.getDouble("price"));
					tempTour.setSlotsAvailable(rs.getInt("slots_available"));
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
	
	// SHOW TOUR BY SLOT
	public ArrayList<Tour> showToursSlot(int threshold) {
				Connection con = DatabaseConfig.getConn();
				String sql = "SELECT DISTINCT "
								+ "T.tourid, " 
								+ "T.tourname, " 
								+ "T.brief_description, " 
								+ "T.detailed_description, " 
								+ "T.price, "
								+ "T.slots_available "
							+ "FROM " 
								+ "tour AS T " 
							+ "WHERE "
								+ "T.slots_available <= ?"
							+ "ORDER BY " 
								+ "T.slots_available ASC";

				ResultSet rs = null; 
				PreparedStatement ps = null;
				ArrayList<Tour> result = new ArrayList<Tour>();
				
				try {
					ps = con.prepareStatement(sql);
					ps.setInt(1, threshold);
					rs = ps.executeQuery();

					while (rs.next()) {
						Tour tempTour = new Tour();
						tempTour.setTourid(rs.getInt("tourid"));
						tempTour.setTourName(rs.getString("tourname"));
						tempTour.setbDescription(rs.getString("brief_description"));
						tempTour.setdDescription(rs.getString("detailed_description"));
						tempTour.setPrice(rs.getDouble("price"));
						tempTour.setSlotsAvailable(rs.getInt("slots_available"));
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
	
	// SHOW TOUR BY CREATION (NEWEST)
	public ArrayList<Tour> showToursNew() {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT DISTINCT "
						+ "T.tourid, " 
						+ "T.tourname, " 
						+ "T.brief_description, " 
						+ "T.detailed_description, " 
						+ "T.price, "
						+ "T.slots_available, "
						+ "T.created_at"
					+ "FROM " 
						+ "tour AS T " 
					+ "ORDER BY " 
						+ "created_at DESC";
	
		ResultSet rs = null; 
		Statement stmt = null;
		ArrayList<Tour> result = new ArrayList<Tour>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
	
			while (rs.next()) {
				Tour tempTour = new Tour();
				tempTour.setTourid(rs.getInt("tourid"));
				tempTour.setTourName(rs.getString("tourname"));
				tempTour.setbDescription(rs.getString("brief_description"));
				tempTour.setdDescription(rs.getString("detailed_description"));
				tempTour.setPrice(rs.getDouble("price"));
				tempTour.setSlotsAvailable(rs.getInt("slots_available"));
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
						+ "I.tour_pic_url " 
					+ "FROM " 
						+ "tour AS T , " 
						+ "tour_category AS C, " 
						+ "tour_img AS I "
					+ "WHERE "
						+ "T.tourid = I.tourid AND "
						+ "T.tourid = C.tourid AND " 
						+ "C.categoryid = ? ";

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

	// SHOW TOUR BY FILTER ONLY
	public List<Tour> showToursFilter(String key) {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT "
						+ "T.tourid, "
						+ "T.tourname, " 
						+ "T.brief_description, " 
						+ "T.detailed_description, " 
						+ "T.price, "
						+ "T.slots_available, " 
						+ "I.tour_pic_url " 
					+ "FROM " 
						+ "tour AS T, " 
						+ "tour_img AS I " 
					+ "WHERE "
						+ "T.tourid = I.tourid AND "
						+ "tourname LIKE N? ";
		
		ResultSet rs = null; 
		PreparedStatement ps = null;
		List<Tour> result = null;

		try {
			ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, '%' + key + '%');
			rs = ps.executeQuery();
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
		    try { if (ps != null) ps.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
		return result;
	}

	// SHOW TOUR BY CATEGORY AND FILTER
	public List<Tour> showToursBoth(int catid, String key) {
			Connection con = DatabaseConfig.getConn();
			String sql = "SELECT "
							+ "T.tourid, "
							+ "T.tourname, " 
							+ "T.brief_description, " 
							+ "T.detailed_description, " 
							+ "T.price, "
							+ "T.slots_available, " 
							+ "I.tour_pic_url " 
						+ "FROM " 
							+ "tour AS T , " 
							+ "tour_img AS I, " 
							+ "tour_category AS C " 
						+ "WHERE "
							+ "T.tourid = I.tourid AND "
							+ "T.tourid = C.tourid AND " 
							+ "tourname LIKE N? AND "
							+ "C.categoryid = ? ";
			
			ResultSet rs = null; 
			PreparedStatement ps = null;
			List<Tour> result = null;

			try {
				ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ps.setString(1, '%' + key + '%');
				ps.setInt(2, catid);
				rs = ps.executeQuery();
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
			    try { if (ps != null) ps.close(); } catch (Exception e) {};
			    try { if (con != null) con.close(); } catch (Exception e) {};
			}
			return result;
		}
	
	// ADD TOUR
	public int addTour(Tour tour) {
		Connection con = DatabaseConfig.getConn();
		String sql = "INSERT INTO "
						+ "tour (tourname, brief_description, detailed_description, price, slots_available) "
					+ "VALUES "
						+ "(?,?,?,?,?)";
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tour.getTourName());
			ps.setString(2, tour.getbDescription());
			ps.setString(3, tour.getdDescription());
			ps.setDouble(4, tour.getPrice());
			ps.setInt(5, tour.getSlotsAvailable());
			ps.executeUpdate();

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {

				if (generatedKeys.next()) {
					return generatedKeys.getInt(1); // return insertID
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}

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

	// ADD TOUR IMAGES
	public int[] addTourImg(int tourid, String[] img_url) {
		Connection con = DatabaseConfig.getConn();
		String sql = "INSERT INTO "
						+ "tour_img (tourid, tour_pic_url) "
					+ "VALUES "
						+ "(?,?)";
		PreparedStatement ps = null;
		
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			for (int i = 0; i < img_url.length; i++) {
				ps.setInt(1, tourid);
				ps.setString(2, img_url[i]);
				ps.addBatch();
			}

			int result[] = ps.executeBatch();
			con.commit();
			con.setAutoCommit(true);
			return result;

		} catch (SQLException ex) {
			ex.printStackTrace();

			if (ex.getMessage().contains("Duplicate entry")) {
				int result[] = { -1 };
				return result;

			} else {
				int result[] = { -2 };
				return result;
			}

		} finally { 
		    try { if (ps != null) ps.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
	}

	// ADD TOUR CATEGORY
	public int[] addTourCat(int tourid, int[] categories) {
		Connection con = DatabaseConfig.getConn();
		String sql = "INSERT INTO " 
						+ "tour_category (tourid, categoryid) " 
					+ "VALUES " 
						+ "(?,?)";
		PreparedStatement ps = null;

		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			for (int i = 0; i < categories.length; i++) {
				ps.setInt(1, tourid);
				ps.setInt(2, categories[i]);
				ps.addBatch();
			}

			int result[] = ps.executeBatch();
			con.commit();
			con.setAutoCommit(true);
			return result;

		} catch (SQLException ex) {
			ex.printStackTrace();

			if (ex.getMessage().contains("Duplicate entry")) {
				int result[] = { -1 };
				return result;

			} else {
				int result[] = { -2 };
				return result;
			}

		} finally { 
		    try { if (ps != null) ps.close(); } catch (Exception e) {};
		    try { if (con != null) con.close(); } catch (Exception e) {};
		}
	}

	// DELETE TOUR
	public int deleteTour(int tourid) {
		Connection con = DatabaseConfig.getConn();
		String sql = "DELETE FROM "
						+ "tour "
					+ "WHERE "
						+ "tourid = ?";
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, tourid);
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
	
	// UPDATE TOUR
	public int updateTour(int tourid, Tour tour) {
		Connection con = DatabaseConfig.getConn();
		String sql = "UPDATE "
						+ "tour "
					+ "SET "
						+ "tourname = ?, "
						+ "brief_description = ?, "
						+ "detailed_description = ?, "
						+ "price = ?, "
						+ "slots_available = ? "
					+ "WHERE "
						+ "tourid = ?";
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, tour.getTourName());
			ps.setString(2, tour.getbDescription());
			ps.setString(3, tour.getdDescription());
			ps.setDouble(4, tour.getPrice());
			ps.setInt(5, tour.getSlotsAvailable());
			ps.setInt(6, tourid);
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

	// DELETE TOUR IMG
	public int deleteTourImg(int tourid) {
		Connection con = DatabaseConfig.getConn();
		String sql = "DELETE FROM "
						+ "tour_img "
					+ "WHERE "
						+ "tourid = ?";
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, tourid);
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
	
	// DELETE TOUR CATEGORY
	public int deleteTourCat(int tourid) {
		Connection con = DatabaseConfig.getConn();
		String sql = "DELETE FROM "
						+ "tour_category "
					+ "WHERE "
						+ "tourid = ?";
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, tourid);
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
