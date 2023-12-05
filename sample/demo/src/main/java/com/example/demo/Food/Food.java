package com.example.demo.Food;

import java.time.LocalDateTime;

public class Food {
	
	private int id;
	private String nickName;
	private String foodType;
	private String content;
	private String taste;
	private LocalDateTime modTime;
	private int viewCount;
	private int isDeleted;
	
	
	public Food() {
	}


	public Food(int id, String nickName, String foodType, String content, String taste, LocalDateTime modTime,
			int viewCount, int isDeleted) {
		this.id = id;
		this.nickName = nickName;
		this.foodType = foodType;
		this.content = content;
		this.taste = taste;
		this.modTime = modTime;
		this.viewCount = viewCount;
		this.isDeleted = isDeleted;
	}


	@Override
	public String toString() {
		return "Food [id=" + id + ", nickName=" + nickName + ", foodType=" + foodType + ", content=" + content + ", taste="
				+ taste + ", modTime=" + modTime + ", viewCount=" + viewCount + ", isDeleted=" + isDeleted + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getfoodType() {
		return foodType;
	}


	public void setfoodType(String foodType) {
		this.foodType = foodType;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getTaste() {
		return taste;
	}


	public void setTaste(String taste) {
		this.taste = taste;
	}


	public LocalDateTime getModTime() {
		return modTime;
	}


	public void setModTime(LocalDateTime modTime) {
		this.modTime = modTime;
	}


	public int getViewCount() {
		return viewCount;
	}


	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}


	public int getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	

}
