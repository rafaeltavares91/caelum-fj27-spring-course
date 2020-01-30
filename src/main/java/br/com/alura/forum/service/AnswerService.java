package br.com.alura.forum.service;

import org.springframework.stereotype.Service;

import br.com.alura.forum.dto.input.NewAnswerInputDto;
import br.com.alura.forum.dto.output.AnswerOutputDto;
import br.com.alura.forum.exception.ResourceNotFoundException;
import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.User;
import br.com.alura.forum.repository.AnswerRepository;
import br.com.alura.forum.repository.TopicRepository;

@Service
public class AnswerService {

	private AnswerRepository answerRepository;
	private TopicRepository topicRepository;
	
	public AnswerService(AnswerRepository answerRepository, TopicRepository topicRepository) {
		this.answerRepository = answerRepository;
		this.topicRepository = topicRepository;
	}

	public AnswerOutputDto create(NewAnswerInputDto newAnswerDto, Long topicId, User loggedUser) {
		Answer answer = new Answer(newAnswerDto.getContent(), topicRepository
				.findById(topicId)
				.orElseThrow(ResourceNotFoundException::new), loggedUser);
		return new AnswerOutputDto(answerRepository.save(answer));
	}

}
