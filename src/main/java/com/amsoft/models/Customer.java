package com.amsoft.models;

public class Customer {
	private Long idCustomer;
	private String doi;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String password;

	public Customer() {
	}

	public Customer(Long idCustomer, String doi, String firstName, String lastName, String address, String email,
			String password) {
		this.idCustomer = idCustomer;
		this.doi = doi;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.password = password;
	}

	public Long getIdCustomer() {
		return idCustomer;
	}

	public String getDoi() {
		return doi;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
