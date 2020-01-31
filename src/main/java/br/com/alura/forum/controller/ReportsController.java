package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.forum.repository.OpenTopicsByCategoryRepository;

@Controller
@RequestMapping("/admin/reports")
public class ReportsController {
	
	private OpenTopicsByCategoryRepository openTopicsRepository;
	
	public ReportsController(OpenTopicsByCategoryRepository openTopicsRepository) {
		this.openTopicsRepository = openTopicsRepository;
	}

	@GetMapping("/open-topics-by-category")
	public String showOpenTopicsByCategoryReport(Model model) {
		model.addAttribute("openTopics", openTopicsRepository.findAllByCurrentMonth());
		return "report";
	}

}
