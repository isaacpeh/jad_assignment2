/* 
	GROUP 3
	DIT/2A/01
	HA JIN 		P2100030
	ISAAC		P2107251
	GEORGE		P2143990
 */

package dbaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

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

}
