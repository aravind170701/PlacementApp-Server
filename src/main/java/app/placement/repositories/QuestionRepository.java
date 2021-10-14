package app.placement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.placement.dao.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer> {

    List<Question> findAllByOrderByQuestionIdDesc();
}
