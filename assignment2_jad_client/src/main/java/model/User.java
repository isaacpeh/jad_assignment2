/* 
	GROUP 3
	DIT/2A/01
	HA JIN 		P2100030
	ISAAC		P2107251
	GEORGE		P2143990
 */

package model;

public class User {
	private String username;
	private String email;
	private String contact;
	private String password;
	private String role;
	private String picUrl;
	private int userid;
	private String address;
	private int purchases;


	public User() {

	}

	public User(String username, String email, String contact, String password, String role, String address) {
		this.username = username;
		this.email = email;
		this.contact = contact;
		this.password = password;
		this.role = role;
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPurchases() {
		return purchases;
	}

	public void setPurchases(int purchases) {
		this.purchases = purchases;
	}
}
