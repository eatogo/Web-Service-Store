package _07_storeInterface.model;

import javax.sql.DataSource;

public class StoreDao {
	DataSource dataSource;

	public StoreDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
}
