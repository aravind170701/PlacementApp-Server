package app.placement.helpers;

import org.springframework.stereotype.Component;

import app.placement.dao.Answer;
import app.placement.dto.AnswerDto;

@Component
public class AnswerHelper {
    
    public Answer convertDtoToEntity(AnswerDto dto) {
		if (dto == null)
			return null;

		var answer = new Answer();
        answer.setQuestionId(dto.getQuestionId());
		answer.setAnswer(dto.getAnswer());
		answer.setStudentName(dto.getStudentName());

		return answer;
	}

	public AnswerDto convertEntityToDtoSimplified(Answer answer) {
		if (answer == null)
			return null;

		var dto = new AnswerDto();
		dto.setQuestionId(answer.getQuestionId());
		dto.setAnswerId(answer.getAnswerId());
		dto.setAnswer(answer.getAnswer());
		dto.setStudentName(answer.getStudentName());
		return dto;
	}
}
