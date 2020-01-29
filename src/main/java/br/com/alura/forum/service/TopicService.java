package br.com.alura.forum.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import br.com.alura.forum.dto.input.NewTopicInputDto;
import br.com.alura.forum.dto.output.TopicOutputDto;
import br.com.alura.forum.exception.ResourceNotFoundException;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;
import br.com.alura.forum.validation.NewTopicCustomValidator;

@Service
public class TopicService {

	private TopicRepository topicRepository;
	private CourseRepository courseRepository;

	public TopicService(TopicRepository topicRepository, CourseRepository courseRepository) {
		this.topicRepository = topicRepository;
		this.courseRepository = courseRepository;
	}

	public Page<Topic> findAll(Specification<Topic> topicSearchSpecification, Pageable pageable) {
		return topicRepository.findAll(topicSearchSpecification, pageable);
	}

	public TopicOutputDto createTopic(NewTopicInputDto newTopicDto, User user) {
		Course course = courseRepository
				.findByName(newTopicDto.getCourseName())
				.orElseThrow(ResourceNotFoundException::new);
		Topic topic = new Topic(
				newTopicDto.getShortDescription(), 
				newTopicDto.getContent(), 
				user, 
				course);
		return new TopicOutputDto(topicRepository.save(topic));
	}
	
	@InitBinder("newTopicInputDto")
	public void initBinder(WebDataBinder binder, @AuthenticationPrincipal User user) {
		binder.addValidators(new NewTopicCustomValidator(topicRepository, user));
	}
	
}
