package br.com.alura.forum.validation;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.alura.forum.dto.input.NewTopicInputDto;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.service.TopicService;

public class NewTopicCustomValidator implements Validator {
	
	private static final int LIMIT_TOPICS_PER_HOUR = 4;
	
	private TopicService topicService;
	private User user;

	public NewTopicCustomValidator(TopicService topicService, User user) {
		this.topicService = topicService;
		this.user = user;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NewTopicInputDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Instant oneHourAgo = Instant.now().minus(1, ChronoUnit.HOURS);
		List<Topic> topicos = topicService.findByOwnerAndCreationInstantAfterOrderByCreationInstantAsc(
				user, oneHourAgo);
		if (topicos.size() >= LIMIT_TOPICS_PER_HOUR) {
			Instant instantOftheOldestTopic = topicos.get(0).getCreationInstant();
			long minutesToNextTopic = Duration.between(oneHourAgo, instantOftheOldestTopic).getSeconds() / 60;
			errors.reject(
					"newTopicInputDto.limit.exceeded", 
					new Object[] {minutesToNextTopic}, 
					"o limite de novos topicos por hora foi excedido.");
		}
	}

}
