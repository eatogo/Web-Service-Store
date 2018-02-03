package _00_utility.model;

public class Store {
	private Integer store_id;
	private String store_name;
	private String store_address;
	private String store_phone;
	private String store_email;
	private String store_logo;
	private String store_open_hour;
	private String store_intro;
	private Double store_latitude;
	private Double store_longitude;
	private String store_city;
	private String store_region;
	private String store_operate_type;
	private String store_status;
	
	public Store() {
		super();
	}

	public Store(Integer store_id, String store_name, String store_address, String store_phone, String store_email,
			String store_logo, String store_open_hour, String store_intro, Double store_latitude,
			Double store_longitude, String store_city, String store_region, String store_operate_type,
			String store_status) {
		super();
		this.store_id = store_id;
		this.store_name = store_name;
		this.store_address = store_address;
		this.store_phone = store_phone;
		this.store_email = store_email;
		this.store_logo = store_logo;
		this.store_open_hour = store_open_hour;
		this.store_intro = store_intro;
		this.store_latitude = store_latitude;
		this.store_longitude = store_longitude;
		this.store_city = store_city;
		this.store_region = store_region;
		this.store_operate_type = store_operate_type;
		this.store_status = store_status;
	}

	public Integer getStore_id() {
		return store_id;
	}

	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getStore_address() {
		return store_address;
	}

	public void setStore_address(String store_address) {
		this.store_address = store_address;
	}

	public String getStore_phone() {
		return store_phone;
	}

	public void setStore_phone(String store_phone) {
		this.store_phone = store_phone;
	}

	public String getStore_email() {
		return store_email;
	}

	public void setStore_email(String store_email) {
		this.store_email = store_email;
	}

	public String getStore_logo() {
		return store_logo;
	}

	public void setStore_logo(String store_logo) {
		this.store_logo = store_logo;
	}

	public String getStore_open_hour() {
		return store_open_hour;
	}

	public void setStore_open_hour(String store_open_hour) {
		this.store_open_hour = store_open_hour;
	}

	public String getStore_intro() {
		return store_intro;
	}

	public void setStore_intro(String store_intro) {
		this.store_intro = store_intro;
	}

	public Double getStore_latitude() {
		return store_latitude;
	}

	public void setStore_latitude(Double store_latitude) {
		this.store_latitude = store_latitude;
	}

	public Double getStore_longitude() {
		return store_longitude;
	}

	public void setStore_longitude(Double store_longitude) {
		this.store_longitude = store_longitude;
	}

	public String getStore_city() {
		return store_city;
	}

	public void setStore_city(String store_city) {
		this.store_city = store_city;
	}

	public String getStore_region() {
		return store_region;
	}

	public void setStore_region(String store_region) {
		this.store_region = store_region;
	}

	public String getStore_operate_type() {
		return store_operate_type;
	}

	public void setStore_operate_type(String store_operate_type) {
		this.store_operate_type = store_operate_type;
	}

	public String getStore_status() {
		return store_status;
	}

	public void setStore_status(String store_status) {
		this.store_status = store_status;
	}

}
