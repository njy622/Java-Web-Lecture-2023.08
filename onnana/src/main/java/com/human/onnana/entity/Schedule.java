package com.human.onnana.entity;

import java.time.LocalDateTime;

public class Schedule {
	private double sid;
	private String uid;
	private String sdate;
	private String title;
	private String place;
	private String place2;
	private double electricty;
	private double gas;
	private double smoke;
	private double smoke2;
	private String emoge;
	

	public Schedule() {
	}


	public Schedule(double sid, String uid, String sdate, String title, String place, String place2, double electricty,
			double gas, double smoke, double smoke2, String emoge) {
		this.sid = sid;
		this.uid = uid;
		this.sdate = sdate;
		this.title = title;
		this.place = place;
		this.place2 = place2;
		this.electricty = electricty;
		this.gas = gas;
		this.smoke = smoke;
		this.smoke2 = smoke2;
		this.emoge = emoge;
	}


	public Schedule(String uid, String sdate, String title, String place, String place2, double electricty, double gas,
			double smoke, double smoke2, String emoge) {
		this.uid = uid;
		this.sdate = sdate;
		this.title = title;
		this.place = place;
		this.place2 = place2;
		this.electricty = electricty;
		this.gas = gas;
		this.smoke = smoke;
		this.smoke2 = smoke2;
		this.emoge = emoge;
	}


	@Override
	public String toString() {
		return "Schedule [sid=" + sid + ", uid=" + uid + ", sdate=" + sdate + ", title=" + title + ", place=" + place
				+ ", place2=" + place2 + ", electricty=" + electricty + ", gas=" + gas + ", smoke=" + smoke
				+ ", smoke2=" + smoke2 + ", emoge=" + emoge + "]";
	}


	public double getSid() {
		return sid;
	}


	public void setSid(double sid) {
		this.sid = sid;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getSdate() {
		return sdate;
	}


	public void setSdate(String sdate) {
		this.sdate = sdate;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public String getPlace2() {
		return place2;
	}


	public void setPlace2(String place2) {
		this.place2 = place2;
	}


	public double getElectricty() {
		return electricty;
	}


	public void setElectricty(double electricty) {
		this.electricty = electricty;
	}


	public double getGas() {
		return gas;
	}


	public void setGas(double gas) {
		this.gas = gas;
	}


	public double getSmoke() {
		return smoke;
	}


	public void setSmoke(double smoke) {
		this.smoke = smoke;
	}


	public double getSmoke2() {
		return smoke2;
	}


	public void setSmoke2(double smoke2) {
		this.smoke2 = smoke2;
	}


	public String getEmoge() {
		return emoge;
	}


	public void setEmoge(String emoge) {
		this.emoge = emoge;
	}


	
}
