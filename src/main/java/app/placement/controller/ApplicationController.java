package app.placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.placement.dto.ApplicationDto;
import app.placement.dto.ResponseObject;
import app.placement.service.ApplicationService;
import app.placement.utils.Constants;
import lombok.Getter;

@Getter
@RestController
@CrossOrigin
@RequestMapping("rest/application")
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@PostMapping("/save-application")
	public ResponseObject saveApplicationInDb(@RequestBody ApplicationDto dto) {
		var isPostSaved = getApplicationService().saveApplication(dto);
		var responseObj = new ResponseObject();
		if (isPostSaved) {
			responseObj.setStatusCode(Constants.HTTP_STATUS_SUCCESS);
			responseObj.setMessage("Applied Successfully");
			return responseObj;
		}
		responseObj.setStatusCode(Constants.HTTP_STATUS_ERROR);
		responseObj.setMessage("Application Form Failed");
		return responseObj;
	}

	// @GetMapping("/get-notifications")
	// public NotificationsList getNotifications(@RequestParam("branch") String branch) {
	// 	return getNotificationService().getNotifications(branch);
	// }

	// @GetMapping("/get-notification")
	// public NotificationDto getNotificationDetailsById(@RequestParam("id") Integer notificationId) {
	// 	return getNotificationService().getNotificationById(notificationId);
	// }
}
