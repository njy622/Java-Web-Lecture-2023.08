package com.example.demo.Food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceOracleImpl implements FoodService{

	@Autowired private FoodDaoOracle foodDao;
	
	@Override
	public Food getFood(int id) {
		Food food = foodDao.getFood(id);
		return food;
	}

	@Override
	public List<Food> getFoodList(String field, String query) {
		query = "%" + query + "%";
		List<Food> list = foodDao.getFoodList(field, query);
		return list;
	}

	@Override
	public void insertFood(Food food) {
		foodDao.insertFood(food);
	}

	@Override
	public void updateFood(Food food) {
		foodDao.updateFood(food);
	}

	@Override
	public void deleteFood(int id) {
		foodDao.deleteFood(id);
		
	}

	@Override
	public void increaseViewCount(int id) {
		foodDao.increaseViewCount(id);
		
	}
	

}
