package br.com.alura.forum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.alura.forum.model.Answer;

@Component
public class ForumMailService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ForumMailService.class);
	
	private MailSender mailSender;

	public ForumMailService(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Async
	public void sendNewReplyMail(Answer answer) {
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo(answer.getTopic().getOwnerEmail());
		simpleMessage.setSubject("Novo comentário em " + answer.getTopic().getShortDescription());
		simpleMessage.setText("Olá " + answer.getTopic().getOwnerName() + " Há uma nova mensagem no fórum! \n\n " + 
				answer.getOwnerName() + " comentou no tópico: " + answer.getTopic().getShortDescription());
		try {
			mailSender.send(simpleMessage);
		} catch (MailException e) {
			LOGGER.error("Não foi possível enviar email para " + answer.getTopic().getOwnerEmail(), e.getMessage());
		}
	}
	
}