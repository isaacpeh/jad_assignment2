/* 
	GROUP 3
	DIT/2A/01
	HA JIN 		P2100030
	ISAAC		P2107251
	GEORGE		P2143990
 */

package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfig {

	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//String connURL = "jdbc:mysql://ipecsql.ddns.net:3306/assignment2_jad_client?user=jadassignment&password=P@ssw0rd&serverTimezone=UTC";
			String connURL = "jdbc:mysql://localhost:3306/assignment2_jad_client?user=root&password=<yourownpassword>&serverTimezone=UTC";
			conn = DriverManager.getConnection(connURL);

		} catch (Exception ex) {
			System.out.println("Error connecting to database");
			ex.printStackTrace();
		}
		return conn;
	}
}
