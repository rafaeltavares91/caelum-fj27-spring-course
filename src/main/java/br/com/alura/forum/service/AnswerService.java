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
	private ForumMailService mailService;
	
	public AnswerService(AnswerRepository answerRepository, TopicRepository topicRepository, ForumMailService mailService) {
		this.answerRepository = answerRepository;
		this.topicRepository = topicRepository;
		this.mailService = mailService;
	}

	public AnswerOutputDto create(NewAnswerInputDto newAnswerDto, Long topicId, User loggedUser) {
		Answer answer = new Answer(newAnswerDto.getContent(), topicRepository
				.findById(topicId)
				.orElseThrow(ResourceNotFoundException::new), loggedUser);
		answer = answerRepository.save(answer);
		mailService.sendNewReplyMail(answer);
		return new AnswerOutputDto(answer);
	}

}
