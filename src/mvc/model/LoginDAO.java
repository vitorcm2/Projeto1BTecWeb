package mvc.model;

import java.io.*;
import java.sql.*;
import java.util.*;
import org.springframework.web.multipart.MultipartFile;

public class LoginDAO {
	private Connection connection = null;
	String url = System.getenv("mysql_url");
	String user = System.getenv("mysql_user");
	String password = System.getenv("mysql_password");

	public LoginDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//connection = DriverManager.getConnection("jdbc:mysql://localhost/meusdados", "root", "Insper119960");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void adiciona(Cadastro usuario) throws IOException {
		try {
			String sql = "INSERT INTO Login (user, password) values(?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getUser());
			stmt.setString(2, usuario.getPassword());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean verifica(Cadastro usuario) {
		boolean st = false;
		String sql = "SELECT * FROM Login WHERE user=? and password=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getUser());
			stmt.setString(2, usuario.getPassword());
			ResultSet rs = stmt.executeQuery();
			st = rs.next();
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return st;
	}
}