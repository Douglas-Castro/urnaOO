package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaVerao", "root", "");
		} catch (SQLException e) {
			e.getMessage();
		}
		return con;
	}
}