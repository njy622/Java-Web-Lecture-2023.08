package com.human.onnana.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.onnana.db.ScheduleDaoOracle;
import com.human.onnana.entity.Anniversary;
import com.human.onnana.entity.SchDay;
import com.human.onnana.entity.Schedule;
import com.human.onnana.service.AnniversaryService;
import com.human.onnana.service.ScheduleService;
import com.human.onnana.utility.SchedUtil;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired private ScheduleService schedService;
	@Autowired private AnniversaryService annivService;
	@Autowired private SchedUtil schedUtil;
	
	
	
	int electricity = 0;
	int gas = 0;
	int smoke = 0;
	int smoke2 = 0;
	
	@GetMapping(value = {"/calendar/{arrow}", "/calendar"})
	public String calendar(@PathVariable(required = false) String arrow, HttpSession session, Model model) {
		LocalDate today = LocalDate.now();
		String date = "일 월 화 수 목 금 토".split(" ")[today.getDayOfWeek().getValue() % 7];
		int year = 2023, month = 1;
		String sessionMonthYear = (String) session.getAttribute("scheduleMonthYear");
		if (sessionMonthYear == null) {
			year = today.getYear();
			month = today.getMonthValue();
		} else {
			year = Integer.parseInt(sessionMonthYear.substring(0,4));
			month = Integer.parseInt(sessionMonthYear.substring(5));
		}
		
		if (arrow != null) {
			switch(arrow) {
			case "left":
				month = month - 1;
				if (month == 0) {
					month = 12;
					year = year - 1;
				}
				break;
			case "right":
				month = month + 1;
				if (month == 13) {
					month = 1;
					year = year + 1;
				}
				break;
			case "left2":
				year = year - 1;
				break;
			case "right2":
				year = year + 1;
			}
		}
		sessionMonthYear = String.format("%d.%02d", year, month);
		session.setAttribute("scheduleMonthYear", sessionMonthYear);
		String sessUid = (String) session.getAttribute("sessUid");
		
		List<SchDay> week = new ArrayList<>();
		List<List<SchDay>> calendar = new ArrayList<>();
		LocalDate startDay = LocalDate.parse(String.format("%d-%02d-01", year, month));
		int startDate = startDay.getDayOfWeek().getValue() % 7;		// 1 ~ 7 사이의 값을 0 ~ 6 사이의 값으로
		LocalDate lastDay = startDay.withDayOfMonth(startDay.lengthOfMonth());
		int lastDate = lastDay.getDayOfWeek().getValue() % 7;
		
		// 아래에서 k는 날짜, i는 요일을 가리킴
		String sdate = null;
		// 첫번째 주
		if (startDate != 0) {
			LocalDate prevSunDay = startDay.minusDays(startDate);
			int prevDay = prevSunDay.getDayOfMonth();
			int prevYear = prevSunDay.getYear();
			int prevMonth = prevSunDay.getMonthValue();
			for (int i=0; i<startDate; i++) {
				sdate = String.format("%d%02d%d", prevYear, prevMonth, prevDay+i);
				week.add(schedService.generateSchDay(sessUid, sdate, i, 1));
			}
		}
		for (int i=startDate, k=1; i<7; i++, k++) {
			sdate = String.format("%d%02d%02d", year, month, k);
			week.add(schedService.generateSchDay(sessUid, sdate, i, 0));
		}
		calendar.add(week);
		
		// 둘째 주부터 해당월의 마지막까지
		int day = 8 - startDate;
		for (int k=day, i=0; k<=lastDay.getDayOfMonth(); k++, i++) {
			if (i % 7 == 0)
				week = new ArrayList<>();
			sdate = String.format("%d%02d%02d", year, month, k);
			week.add(schedService.generateSchDay(sessUid, sdate, i % 7, 0));
			if (i % 7 == 6)
				calendar.add(week);
		}
		// 마지막 주 다음달 내용
		if (lastDate != 6) {
			LocalDate nextDay = lastDay.plusDays(1);
			int nextYear = nextDay.getYear();
			int nextMonth = nextDay.getMonthValue();
			for (int i=lastDate+1, k=1; i<7; i++, k++) {
				sdate = String.format("%d%02d%02d", nextYear, nextMonth, k);
				week.add(schedService.generateSchDay(sessUid, sdate, i, 1));
			}
			calendar.add(week);
		}
		
		model.addAttribute("calendar", calendar);
		model.addAttribute("today", today + "(" + date + ")");
		model.addAttribute("year", year);
		model.addAttribute("month", String.format("%02d", month));
		model.addAttribute("numberOfWeeks", calendar.size());
		model.addAttribute("timeList", schedUtil.genTime());
		return "schedule/calendar";
	}
	
	@ResponseBody
	@PostMapping("/insert")
	public String insert(HttpServletRequest req, HttpSession session, String uid) {
		String title = req.getParameter("title");
		String startDate = req.getParameter("startDate");
		String place = req.getParameter("place");
		String place2 = req.getParameter("place2");
		electricity = Integer.parseInt("electricity");
		gas = Integer.parseInt("gas");
		smoke = Integer.parseInt("smoke");
		smoke2 = Integer.parseInt("smoke2");
		String emoge = req.getParameter("emoge");
		String sdate = startDate.replace("-", "");
		String sessUid = (String) session.getAttribute("sessUid");
		Schedule schedule = new Schedule(sessUid, sdate, title, place, place2, electricity, gas, smoke, smoke2, emoge);
//		System.out.println(schedule); 서버 읽히는지 테스트
		
		schedService.insert(schedule);
		return "";
	}

	// Ajax로 detail data를 전달함
	@ResponseBody
	@GetMapping("/detail/{sid}")
	public String detail(@PathVariable int sid) {
		Schedule sched = schedService.getSchedule(sid);
		JSONObject jSched = new JSONObject();
		jSched.put("sid", sid);
		jSched.put("title", sched.getTitle());
		jSched.put("place", sched.getPlace());
		jSched.put("place2", sched.getPlace());
		jSched.put("electricity", sched.getElectricity());
		jSched.put("gas", sched.getGas());
		jSched.put("smoke", sched.getSmoke());
		jSched.put("smoke2", sched.getSmoke2());
		jSched.put("emoge", sched.getEmoge());
		return jSched.toString();
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest req, HttpSession session) {
		String title = req.getParameter("title");
		String startDate = req.getParameter("startDate");
		String place = req.getParameter("place");
		String place2 = req.getParameter("place2");
		electricity = Integer.parseInt("electricity");
		gas = Integer.parseInt("gas");
		smoke = Integer.parseInt("smoke");
		smoke2 = Integer.parseInt("smoke2");
		String emoge = req.getParameter("emoge");
		String sdate = startDate.replace("-", "");
		String sessUid = (String) session.getAttribute("sessUid");
		Schedule schedule = new Schedule(sessUid, sdate, title, place, place2, electricity, gas, smoke, smoke2, emoge);
//		System.out.println(schedule); 서버 읽히는지 테스트
		
		schedService.update(schedule);
		return "redirect:/schedule/calendar";
	}
	
	@GetMapping("/delete/{sid}")
	public String delete(@PathVariable int sid) {
		schedService.delete(sid);
		return "redirect:/schedule/calendar";
	}
	
	@PostMapping("/insertAnniv")
	public String insertAnniv(HttpServletRequest req, HttpSession session) {
		String aname = req.getParameter("title");
		String sessUid = (String) session.getAttribute("sessUid");
		int isHoliday = (req.getParameter("holiday") == null) ? 0 : 1;
		String adate = req.getParameter("annivDate").replace("-", "");
		Anniversary anniv = new Anniversary(sessUid, aname, adate, isHoliday);
		annivService.insertAnniv(anniv);
		return "redirect:/schedule/calendar";
	}
	
	@PostMapping("/insertAnnivList")
	public String insertAnnivList(String option, int year) {
		List<Anniversary> list = schedUtil.getAnnivList(option, year);
		for (Anniversary anniv: list)
			annivService.insertAnniv(anniv);
		return "redirect:/schedule/calendar";
	}


}
