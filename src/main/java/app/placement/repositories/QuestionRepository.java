package app.placement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.placement.dao.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
}
