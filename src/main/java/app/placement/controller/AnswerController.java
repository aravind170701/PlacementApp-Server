package app.placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.placement.dto.AnswerDto;
import app.placement.dto.AnswersList;
import app.placement.dto.ResponseObject;
import app.placement.service.AnswerService;
import app.placement.utils.Constants;
import lombok.Getter;

@Getter
@RestController
@CrossOrigin
@RequestMapping("rest/answer")
public class AnswerController {

    @Autowired
	private AnswerService answerService;

	@PostMapping("/submit-answer")
	public ResponseObject saveAnswerInDb(@RequestBody AnswerDto dto) {
		var isPostSaved = getAnswerService().saveAnswer(dto);
		var responseObj = new ResponseObject();
		if (isPostSaved) {
			responseObj.setStatusCode(Constants.HTTP_STATUS_SUCCESS);
			responseObj.setMessage("Answer saved Successfully");
			return responseObj;
		}
		responseObj.setStatusCode(Constants.HTTP_STATUS_ERROR);
		responseObj.setMessage("Answer could not be saved in DB");
		return responseObj;
	}

	@GetMapping("/get-answers")
	public AnswersList getNotifications() {
		return getAnswerService().getAllAnswers();
	}
    
}
