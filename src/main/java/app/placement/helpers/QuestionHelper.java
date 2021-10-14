package app.placement.helpers;

import org.springframework.stereotype.Component;

import app.placement.dao.Question;
import app.placement.dto.QuestionDto;

@Component
public class QuestionHelper {
    
    public Question convertDtoToEntity(QuestionDto dto) {
		if (dto == null)
			return null;

		var question = new Question();
        question.setQuestion(dto.getQuestion());

		return question;
	}
}
