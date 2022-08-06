package model;

public class TourRecord {
	private int userid;
	private String username;
	private int tourid;
	private String tourname;
	private int quantity;
	private int total_orders;
	private int total_purchases;
	private String purchased_at;
	private String category;
	private int categoryid;

	public TourRecord() {

	}

	public TourRecord(int userid, int tourid, int quantity) {
		this.userid = userid;
		this.tourid = tourid;
		this.quantity = quantity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTourname() {
		return tourname;
	}

	public void setTourname(String tourname) {
		this.tourname = tourname;
	}

	public String getPurchased_at() {
		return purchased_at;
	}

	public void setPurchased_at(String purchased_at) {
		this.purchased_at = purchased_at;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getTourid() {
		return tourid;
	}

	public void setTourid(int tourid) {
		this.tourid = tourid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public int getTotal_orders() {
		return total_orders;
	}

	public void setTotal_orders(int total_orders) {
		this.total_orders = total_orders;
	}

	public int getTotal_purchases() {
		return total_purchases;
	}

	public void setTotal_purchases(int total_purchases) {
		this.total_purchases = total_purchases;
	}

}
