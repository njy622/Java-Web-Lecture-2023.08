package com.human.onnana.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.onnana.dao.AnniversaryDao;
import com.human.onnana.entity.Anniversary;

@Service
public class AnniversaryServiceImpl implements AnniversaryService {

	@Autowired private AnniversaryDao annivDao;
	
	@Override
	public List<Anniversary> getDayAnnivList(String sdate) {
		List<Anniversary> list = annivDao.getAnnivList(sdate, sdate);
		return list;
	}
	
	@Override
	public List<Anniversary> getAnnivDays(String start, String end) {
		List<Anniversary> list = annivDao.getAnnivList(start, end);
		return list;
	}

	@Override
	public void insert(Anniversary anniversary) {
		annivDao.insert(anniversary);
	}

}
