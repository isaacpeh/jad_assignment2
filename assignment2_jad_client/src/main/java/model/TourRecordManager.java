package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.DatabaseConfig;

public class TourRecordManager {
	
	//INSERT RECORD
	public int[] addRecord(ArrayList<TourRecord> tourRecord) {
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
}
