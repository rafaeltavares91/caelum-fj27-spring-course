package br.com.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
