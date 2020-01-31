package br.com.alura.forum.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OpenTopicsByCategory {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    private int topicCount;
    private LocalDate date;

    @Deprecated
    public OpenTopicsByCategory() {
    }

    public OpenTopicsByCategory(String categoryName, Number topicCount, Date instant) {
        this.categoryName = categoryName;
        this.topicCount = topicCount.intValue();
        this.date = instant.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getTopicCount() {
        return topicCount;
    }

    public LocalDate getInstant() {
        return date;
    }

}
