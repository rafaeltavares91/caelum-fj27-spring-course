package br.com.alura.forum.model.topic.domain;

import br.com.alura.forum.model.Answer;

public interface TopicState {

    void registerNewReply(Topic topic, Answer newReply);
	void markAsSolved(Topic topic);
	void close(Topic topic);
}
