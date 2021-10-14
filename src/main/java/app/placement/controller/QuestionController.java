package app.placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.placement.dto.QuestionDto;
import app.placement.dto.ResponseObject;
import app.placement.service.QuestionService;
import app.placement.utils.Constants;
import lombok.Getter;

@Getter
@RestController
@CrossOrigin
@RequestMapping("rest/question")
public class QuestionController {

    @Autowired
	private QuestionService questionService;

	@PostMapping("/submit-question")
	public ResponseObject saveQuestionInDb(@RequestBody QuestionDto dto) {
		var isPostSaved = getQuestionService().saveQuestion(dto);
		var responseObj = new ResponseObject();
		if (isPostSaved) {
			responseObj.setStatusCode(Constants.HTTP_STATUS_SUCCESS);
			responseObj.setMessage("Question saved Successfully");
			return responseObj;
		}
		responseObj.setStatusCode(Constants.HTTP_STATUS_ERROR);
		responseObj.setMessage("Question could not be saved in DB");
		return responseObj;
	}
    
}
