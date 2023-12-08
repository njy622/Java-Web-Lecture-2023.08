package com.human.onnana.entity;

import java.time.LocalDateTime;

public class Schedule {
	private int sid;
	private String uid;
	private String sdate;
	private String title;
	private String place;
	private String place2;
	private int electricity;
	private int gas;
	private int smoke;
	private int smoke2;
	private String emoge;
	

	public Schedule() {
	}


	public Schedule(int sid, String uid, String sdate, String title, String place, String place2, int electricity,
			int gas, int smoke, int smoke2, String emoge) {
		this.sid = sid;
		this.uid = uid;
		this.sdate = sdate;
		this.title = title;
		this.place = place;
		this.place2 = place2;
		this.electricity = electricity;
		this.gas = gas;
		this.smoke = smoke;
		this.smoke2 = smoke2;
		this.emoge = emoge;
	}


	public Schedule(String uid, String sdate, String title, String place, String place2, int electricity, int gas,
			int smoke, int smoke2, String emoge) {
		this.uid = uid;
		this.sdate = sdate;
		this.title = title;
		this.place = place;
		this.place2 = place2;
		this.electricity = electricity;
		this.gas = gas;
		this.smoke = smoke;
		this.smoke2 = smoke2;
		this.emoge = emoge;
	}


	@Override
	public String toString() {
		return "Schedule [sid=" + sid + ", uid=" + uid + ", sdate=" + sdate + ", title=" + title + ", place=" + place
				+ ", place2=" + place2 + ", electricity=" + electricity + ", gas=" + gas + ", smoke=" + smoke
				+ ", smoke2=" + smoke2 + ", emoge=" + emoge + "]";
	}


	public int getSid() {
		return sid;
	}


	public void setSid(int sid) {
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


	public int getElectricity() {
		return electricity;
	}


	public void setElectricity(int electricity) {
		this.electricity = electricity;
	}


	public int getGas() {
		return gas;
	}


	public void setGas(int gas) {
		this.gas = gas;
	}


	public int getSmoke() {
		return smoke;
	}


	public void setSmoke(int smoke) {
		this.smoke = smoke;
	}


	public int getSmoke2() {
		return smoke2;
	}


	public void setSmoke2(int smoke2) {
		this.smoke2 = smoke2;
	}


	public String getEmoge() {
		return emoge;
	}


	public void setEmoge(String emoge) {
		this.emoge = emoge;
	}


	
}
