package com.example.demo.Food;

import java.time.LocalDateTime;
import java.util.List;

public interface FoodService {

	Food getFood(int id);
		
	List<Food> getFoodList(String field, String query);
	
	void insertFood(Food food);
	
	void updateFood(Food food);
	
	void deleteFood(int id);
	
	void increaseViewCount(int id);
	
}