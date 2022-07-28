/* 
	GROUP 3
	DIT/2A/01
	HA JIN 		P2100030
	ISAAC		P2107251
	GEORGE		P2143990
 */

package model;

import java.util.List;

public class Tour {
	private String tourName;
	private String bDescription;
	private String dDescription;
	private double price;
	private int slotsAvailable;
	private List<String> picUrl;
	private int tourid;
	private List<Integer> categoryid;
	private int totalSales;

	public Tour() {

	}

	public Tour(String tourName, String bDescription, String dDescription, double price, int slotsAvailable) {
		this.tourName = tourName;
		this.bDescription = bDescription;
		this.dDescription = dDescription;
		this.price = price;
		this.slotsAvailable = slotsAvailable;
	}

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public String getbDescription() {
		return bDescription;
	}

	public void setbDescription(String bDescription) {
		this.bDescription = bDescription;
	}

	public String getdDescription() {
		return dDescription;
	}

	public void setdDescription(String dDescription) {
		this.dDescription = dDescription;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSlotsAvailable() {
		return slotsAvailable;
	}

	public void setSlotsAvailable(int slotsAvailable) {
		this.slotsAvailable = slotsAvailable;
	}

	public List<String> getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(List<String> picUrl) {
		this.picUrl = picUrl;
	}

	public int getTourid() {
		return tourid;
	}

	public void setTourid(int tourid) {
		this.tourid = tourid;
	}
	public List<Integer> getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(List<Integer>categoryid) {
		this.categoryid = categoryid;
	}

	public int getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}
}
