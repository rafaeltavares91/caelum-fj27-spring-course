package br.com.alura.forum.model.topic.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.User;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.util.Assert;

@Entity
public class Topic {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String shortDescription;
	
	@Lob
	private String content;
	
	private Instant creationInstant = Instant.now();
	private Instant lastUpdate = Instant.now();
	
	@Enumerated(EnumType.STRING)
	private TopicStatus status = TopicStatus.NOT_ANSWERED;

	@ManyToOne
	private User owner;
	
	@ManyToOne
	private Course course;
	
	@OneToMany(mappedBy = "topic")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<Answer> answers = new ArrayList<>();
	
	/**
	 * @deprecated
	 */
	public Topic() {}
	
	public Topic(String shortDescription, String content, User owner, Course course) {
		super();
		this.shortDescription = shortDescription;
		this.content = content;
		this.owner = owner;
		this.course = course;
	}

	public Long getId() {
		return id;
	}

	public String getShortDescription() {
		return shortDescription;
	}
	
	public String getContent() {
		return content;
	}
	
	public Instant getCreationInstant() {
		return creationInstant;
	}

	public Instant getLastUpdate() {
		return lastUpdate;
	}

	public User getOwner() {
		return owner;
	}

	public String getOwnerName() {
		return owner.getName();
	}

	public Course getCourse() {
		return course;
	}

	public String getOwnerEmail() {
		return owner.getEmail();
	}

	public Integer getNumberOfAnswers() {
		return this.answers.size();
	}

	public TopicStatus getStatus() {
		return status;
	}

	void setStatus(TopicStatus status) {
		this.status = status;
	}

	public List<Answer> getAnswers() {
		return Collections.unmodifiableList(this.answers);
	}

	void addAnswer(Answer answer) {
		this.answers.add(answer);
	}

	public void registerNewReply(Answer newReply) {
		Assert.notNull(newReply, "Nova resposta não pode ser nula");

		this.status.registerNewReply(this, newReply);
	}

	public void markAsSolved(Answer solution) {
		Assert.notNull(solution, "A resposta de solução não pode ser nula");

		this.status.markAsSolved(this);
	}

	public void close() {
		this.status.close(this);
	}
}
