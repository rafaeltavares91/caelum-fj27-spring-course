package br.com.alura.forum.model;

import br.com.alura.forum.model.topic.domain.Topic;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Lob
	private String content;

	private Instant creationTime = Instant.now();

	private boolean solution = false;

	@ManyToOne
	private Topic topic;

	@ManyToOne
	private User owner;


	/**
	 * @deprecated
	 */
	public Answer() {
	}

	public Answer(String content, Topic topic, User owner) {
		this.content = content;
		this.topic = topic;
		this.owner = owner;

		topic.registerNewReply(this);
	}

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public Instant getCreationTime() {
		return creationTime;
	}

	public boolean isSolution() {
		return solution;
	}

	public Topic getTopic() {
		return topic;
	}

	public String getOwnerName() {
		return this.owner.getName();
	}

	public User getOwner() {
		return owner;
	}

	public void markAsSolution() {
		this.solution = true;
		this.topic.markAsSolved(this);
	}
}
