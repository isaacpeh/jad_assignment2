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

public class CategoryManager {

	// SHOW ALL
	public List<Category> showCategories() {
		Connection con = DatabaseConfig.getConn();
		String sql = "SELECT * FROM category";
		ResultSet rs = null;
		Statement stmt = null;
		List<Category> result = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			result = new ArrayList<>();

			while (rs.next()) {
				Category tempCat = new Category();
				tempCat.setCatID(rs.getInt("categoryid"));
				tempCat.setCategoryName(rs.getString("category"));
				tempCat.setDescription(rs.getString("description"));
				result.add(tempCat);
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

	// ADD CATEGORY
	public int addCategory(Category category) {
		Connection con = DatabaseConfig.getConn();
		String sql = "INSERT INTO "
						+ "category (category, description) "
					+ "VALUES "
						+ "(?,?)";
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, category.getCategoryName());
			ps.setString(2, category.getDescription());
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
	
	// DELETE CATEGORY
	public int deleteCategory(int catid) {
		Connection con = DatabaseConfig.getConn();
		String sql = "DELETE FROM "
						+ "category "
					+ "WHERE "
						+ "categoryid = ?";
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, catid);
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
