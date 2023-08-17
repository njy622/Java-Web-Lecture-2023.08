package com.example.demo;

public class Address {  
	//멤버 필드영역
	private int zipCode; //우편번호
	private String city;
	private String country;
	
	//생성자 영역
	public Address(int zipCode, String city, String country) {
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
	}


	@Override //오버라이딩한 멤버
	public String toString() {
		return "Address [zipCode=" + zipCode + ", city=" + city + ", country=" + country + "]";
	}

	//getter, setter
	public int getZipCode() {
		return zipCode;
	}


	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	
}
