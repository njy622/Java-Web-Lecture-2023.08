package com.human.onnana.service;

import java.util.List;

import com.human.onnana.entity.Anniversary;

public interface AnniversaryService {
	
	List<Anniversary> getDayAnnivList(String sdate);

	List<Anniversary> getAnnivDays(String start, String end);
	
	void insert(Anniversary anniversary);
}
