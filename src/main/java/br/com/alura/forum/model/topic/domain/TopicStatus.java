package br.com.alura.forum.model.topic.domain;

import br.com.alura.forum.model.Answer;

public enum TopicStatus implements TopicState {

	NOT_ANSWERED {

		@Override
		public void registerNewReply(Topic topic, Answer newReply) {
			if (! topic.getOwner().equals(newReply.getOwner())) {
				topic.setStatus(NOT_SOLVED);
			}

			topic.addAnswer(newReply);
		}

		@Override
		public void markAsSolved(Topic topic) {
			topic.setStatus(SOLVED);
		}

		@Override
		public void close(Topic topic) {
			topic.setStatus(CLOSED);
		}
	},

	NOT_SOLVED {

		@Override
		public void registerNewReply(Topic topic, Answer newReply) {
			topic.addAnswer(newReply);
		}

		@Override
		public void markAsSolved(Topic topic) {
			topic.setStatus(SOLVED);
		}

		@Override
		public void close(Topic topic) {
			topic.setStatus(CLOSED);
		}
	},

	SOLVED {

		@Override
		public void registerNewReply(Topic topic, Answer newReply) {
			topic.addAnswer(newReply);
		}

		@Override
		public void markAsSolved(Topic topic) {
			throw new RuntimeException("Essa dúvida já foi solucionada!");
		}

		@Override
		public void close(Topic topic) {
			throw new RuntimeException("A dúvida já foi solucionada e deve ser mantida aberta para fins de registro!");
		}
	},

	CLOSED {

		@Override
		public void registerNewReply(Topic topic, Answer newReply) {
			throw new RuntimeException("Tópico fechado! Não é possível adicionar novas respostas");
		}

		@Override
		public void markAsSolved(Topic topic) {
			throw new RuntimeException("Essa dúvida já está fechada!");
		}

		@Override
		public void close(Topic topic) {
			throw new RuntimeException("A dúvida já está fechada!");
		}
	};
}
