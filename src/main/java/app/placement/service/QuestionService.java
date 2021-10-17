package app.placement.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.placement.dao.Question;
import app.placement.dto.QuestionDto;
import app.placement.dto.QuestionsList;
import app.placement.helpers.QuestionHelper;
import app.placement.repositories.QuestionRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Service
@Slf4j
@Transactional
public class QuestionService{

    @Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private QuestionHelper questionHelper;

    public boolean saveQuestion(QuestionDto questionDto) {
		if (questionDto == null) {
			log.error("Question DTO is null");
			return false;
		}

		// Persist question in DB
		var question = getQuestionHelper().convertDtoToEntity(questionDto);
		getQuestionRepository().save(question);
		return true;
	}
    
	public QuestionsList getAllQuestions() {
		return extractList(getQuestionRepository().findAllByOrderByQuestionIdDesc());
	}

	private QuestionsList extractList(List<Question> list) {
		var questions = list.stream().map(getQuestionHelper()::convertEntityToDtoSimplified)
				.collect(Collectors.toList());
		var questionsList = new QuestionsList();
		questionsList.setQuestionDtos(questions);
		return questionsList;
	}
}
