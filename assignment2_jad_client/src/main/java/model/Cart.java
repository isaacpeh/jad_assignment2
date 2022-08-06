package model;

public class Cart {
	private String tourName;
	private double price;
	private double totalPrice;
	private String picUrl;
	private int quantity;
	private int tourId;

	public Cart() {

	}

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTourId() {
		return tourId;
	}

	public void setTourId(int tourId) {
		this.tourId = tourId;
	}

	public double getTotalPrice() {
		totalPrice = price * quantity;
		return totalPrice;
	}

	public void addQuantity() {
		quantity += 1;
	}

	public void minusQuantity() {
		quantity -= 1;
	}
}
