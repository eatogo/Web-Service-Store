package _01_authentication.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import _00_utility.model.User;

public class UserDao {
	DataSource dataSource;
	User user;
	private final int OBJECT_TYPE_USER = 1;

	public UserDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private String insertIntoUserSql = "INSERT INTO `USERS` "
			+ " (user_password, user_cellphone, user_name, user_create_time) "
			+ " VALUES "
			+ " (?, ?, ?, ?);";
	
	public boolean insertIntoUser(User user) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(insertIntoUserSql);) {
			ps.setString(1, user.getUser_password());
			ps.setString(2, user.getUser_cellphone());
			ps.setString(3, user.getUser_name());
			ps.setDate(4, (java.sql.Date) new Date(System.currentTimeMillis()));
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("SQL查詢指令有問題");
			e.printStackTrace();
			return false;
		}
	}

	private String findUserByStringSql = "SELECT * FROM `USERS` WHERE ";

	public User findUserByStringData(String typeOfData, String data) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(findUserByStringSql + " " + typeOfData + " = ?;");) {
			ps.setString(1, data);
			extractInfoFromQueryResult(ps.executeQuery(), OBJECT_TYPE_USER);
			return user;
		} catch (SQLException e) {
			System.out.println("SQL查詢指令有問題");
			e.printStackTrace();
			return null;
		}
	}

	private void extractInfoFromQueryResult(ResultSet result, int OBJECT_TYPE) {
		try {
			switch (OBJECT_TYPE) {
			case OBJECT_TYPE_USER:
				while (result.next()) {
					Integer user_id = result.getInt(1);
					String user_password = result.getString(2);
					String user_cellphone = result.getString(3);
					String user_name = result.getString(4);
					String user_email = result.getString(5);
					String user_avatar = result.getString(6);
					Date user_create_time = result.getDate(7);
					String user_status = result.getString(8);
					String user_identity = result.getString(9);
					Integer user_store = result.getInt(10);
					user = new User(user_id, user_password, user_name, user_cellphone, user_email, user_avatar,
							user_create_time, user_status, user_identity, user_store);
				}
				break;
			}
		} catch (SQLException e) {
			System.out.println("ResultSet指令有問題");
			e.printStackTrace();
		}
	}
}
