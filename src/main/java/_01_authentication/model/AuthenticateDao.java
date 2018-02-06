package _01_authentication.model;

import javax.sql.DataSource;

public class AuthenticateDao {
	DataSource dataSource;

	public AuthenticateDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
