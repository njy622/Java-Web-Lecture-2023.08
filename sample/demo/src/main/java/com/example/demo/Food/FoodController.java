package com.example.demo.Food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/food")
public class FoodController {

	@Autowired private FoodService foodService;
	
	@GetMapping("/list")
	public String list(@RequestParam(name="f", defaultValue = "foodType") String field,
					   @RequestParam(name="q", defaultValue = "") String query,
					   Model model) {
		
		List<Food> list = foodService.getFoodList(field, query);
		model.addAttribute("FoodList", list);
		model.addAttribute("menu", "food");
		model.addAttribute("field", field);
		model.addAttribute("query", query);
		return "food/list";
		
	}
	
	@GetMapping("/write")
	public String writeForm(Model model) {
		model.addAttribute("menu", "food");
		return "food/write";
	}
	
	@PostMapping("/write")
	public String writeProc(Food food) {
		foodService.insertFood(food);
		return "redirect:food/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable int id, String option, Model model) {
		if(option == null  || option.equals(""))
			foodService.increaseViewCount(id);
		Food food = foodService.getFood(id);
		model.addAttribute("food", food);
		model.addAttribute("menu", food);
		return "food/detail";
	}
	
	@GetMapping("/update/{id}")
	public String updateForm(@PathVariable int id, Model model) {
		Food food = foodService.getFood(id);
		model.addAttribute("food", food);
		model.addAttribute("menu", food);
		return "food/update";
	}


	@PostMapping("/update")
	public String updateProc(Food food) {
		foodService.updateFood(food);
		return "redirect:food/detail" + food.getId() + "?option=DNI";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("menu", "food");
		return "food/delete";
	}

	@GetMapping("/deleteConfirm/{id}")
	public String deleteContirm(@PathVariable int id) {
		foodService.deleteFood(id);
		return "redirect:food/list";
	}

}
