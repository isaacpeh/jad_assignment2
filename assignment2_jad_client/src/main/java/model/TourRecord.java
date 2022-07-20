package model;

public class TourRecord {
	private int userid;
	private int tourid;
	private int quantity;

	public TourRecord() {

	}

	public TourRecord(int userid, int tourid, int quantity) {
		this.userid = userid;
		this.tourid = tourid;
		this.quantity = quantity;
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
