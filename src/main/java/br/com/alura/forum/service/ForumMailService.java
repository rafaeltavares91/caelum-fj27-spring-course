package br.com.alura.forum.service;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.alura.forum.exception.MailServiceException;
import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.topic.domain.Topic;

@Service
public class ForumMailService {

    private JavaMailSender mailSender;
    private NewReplyMailFactory newReplyMailFactory;

    public ForumMailService(JavaMailSender mailSender, NewReplyMailFactory newReplyMailFactory) {
		this.mailSender = mailSender;
		this.newReplyMailFactory = newReplyMailFactory;
	}

	@Async
    public void sendNewReplyMail(Answer answer) {
        Topic answeredTopic = answer.getTopic();
        MimeMessagePreparator messagePreparator = (mimeMessage) -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(answeredTopic.getOwnerEmail());
            messageHelper.setSubject("Novo comentário em: " + answeredTopic.getShortDescription());

            String messageContent = this.newReplyMailFactory.generateNewReplyMailContent(answer);
            messageHelper.setText(messageContent, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            throw new MailServiceException("Não foi possível enviar o email.", e);
        }
    }
}
