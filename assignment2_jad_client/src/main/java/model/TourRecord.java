package model;

public class TourRecord {
	private int userid;
	private String username;
	private int tourid;
	private String tourname;
	private int quantity;
	private String purchased_at;

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

}
