package model;

public class Cart {
	private int tourid;
	private int quantity;

	public Cart() {

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

	public Cart(int tourid, int quantity) {
		this.tourid = tourid;
		this.quantity = quantity;
	}

}
