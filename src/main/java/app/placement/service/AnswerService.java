package app.placement.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.placement.dao.Answer;
import app.placement.dto.AnswerDto;
import app.placement.dto.AnswersList;
import app.placement.helpers.AnswerHelper;
import app.placement.repositories.AnswerRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Service
@Slf4j
@Transactional
public class AnswerService{

    @Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private AnswerHelper answerHelper;

    public boolean saveAnswer(AnswerDto answerDto) {
		if (answerDto == null) {
			log.error("Answer DTO is null");
			return false;
		}

		// Persist answer in DB
		var answer = getAnswerHelper().convertDtoToEntity(answerDto);
		getAnswerRepository().save(answer);
		return true;
	}
    
	public AnswersList getAllAnswers() {
		return extractList(getAnswerRepository().findAllByOrderByAnswerIdDesc());
	}

	private AnswersList extractList(List<Answer> list) {
		var answers = list.stream().map(getAnswerHelper()::convertEntityToDtoSimplified)
				.collect(Collectors.toList());
		var answersList = new AnswersList();
		answersList.setAnswerDtos(answers);
		return answersList;
	}
}
