package br.com.alura.forum.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.alura.forum.model.OpenTopicsByCategory;
import br.com.alura.forum.repository.OpenTopicsByCategoryRepository;
import br.com.alura.forum.repository.TopicRepository;

@Component
public class RegisterUnansweredTopicsTask {

	private TopicRepository topicRepository;
	private OpenTopicsByCategoryRepository openTopicsRepository;

	public RegisterUnansweredTopicsTask(TopicRepository topicRepository, OpenTopicsByCategoryRepository openTopicsRepository) {
		this.topicRepository = topicRepository;
		this.openTopicsRepository = openTopicsRepository;
	}
	
	@Scheduled(fixedDelay = 30000)
	public void execute() {
		List<OpenTopicsByCategory> topicosAbertosPorCategoria = topicRepository.findOpenTopicsByCategory();
		openTopicsRepository.saveAll(topicosAbertosPorCategoria);
	}
	
}
