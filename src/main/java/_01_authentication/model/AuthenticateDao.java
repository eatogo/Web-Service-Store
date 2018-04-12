package _01_authentication.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import _00_global.model.User;

public class AuthenticateDao {
	DataSource dataSource;

	public AuthenticateDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private final String AUTHENTICATE_SQL = "SELECT user_id, user_password FROM `USERS` WHERE user_cellphone = ?";

	public User authenticate(String user_cellphone, String user_password) {
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(AUTHENTICATE_SQL);
			ps.setString(1, user_cellphone);
			ResultSet rs = ps.executeQuery();
			User user = new User();
			while (rs.next()) {
				Integer id = rs.getInt(1);
				String password = rs.getString(2);
				user.setUser_id(id);
				user.setUser_cellphone(user_cellphone);
				user.setUser_password(password);
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
