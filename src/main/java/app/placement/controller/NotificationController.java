package app.placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.placement.dto.NotificationDto;
import app.placement.dto.ResponseObject;
import app.placement.service.NotificationService;
import app.placement.utils.Constants;
import lombok.Getter;

@Getter
@RestController
@RequestMapping("rest/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping("/save-notification")
	public ResponseObject saveNotificationInDb(@RequestBody NotificationDto dto) {
		var isPostSaved = getNotificationService().saveNotification(dto);
		var responseObj = new ResponseObject();
		if (isPostSaved) {
			responseObj.setStatusCode(Constants.HTTP_STATUS_SUCCESS);
			responseObj.setMessage("Notification saved Successfully");
			return responseObj;
		}
		responseObj.setStatusCode(Constants.HTTP_STATUS_ERROR);
		responseObj.setMessage("Notification could not be saved in DB");
		return responseObj;
	}
}
