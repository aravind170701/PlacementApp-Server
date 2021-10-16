package app.placement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.placement.dao.Answer;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {

    List<Answer> findAllByOrderByAnswerIdDesc();

    List<Answer> findAllByQuestionIdOrderByAnswerIdDesc(Integer questionId);
}
