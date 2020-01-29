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
import br.com.alura.forum.repository.TopicRepository;

public class NewTopicCustomValidator implements Validator {
	
	private TopicRepository topicRepository;
	private User user;

	public NewTopicCustomValidator(TopicRepository topicRepository, User user) {
		this.topicRepository = topicRepository;
		this.user = user;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NewTopicInputDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Instant oneHourAgo = Instant.now().minus(1, ChronoUnit.HOURS);
		List<Topic> topicos = topicRepository.findByOwnerAndCreationInstantAfterOrderByCreationInstantAsc(
				user, oneHourAgo);
		if (topicos.size() >= 4) {
			Instant instantOftheOldestTopic = topicos.get(0).getCreationInstant();
			long minutesToNextTopic = Duration.between(oneHourAgo, instantOftheOldestTopic).getSeconds() / 60;
			errors.reject(
					"newTopicInputDto.limit.exceeded", 
					new Object[] {minutesToNextTopic}, 
					"o limite de novos topicos por hora foi excedido.");
		}
	}

}
