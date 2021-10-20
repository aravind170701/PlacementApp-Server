package app.placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.placement.dto.ApplicationDto;
import app.placement.dto.StudentApplicationList;
import app.placement.service.ApplicationService;
import lombok.Getter;

@Getter
@RestController
@CrossOrigin
@RequestMapping("rest/application")
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@PostMapping("/save-application")
	public ApplicationDto saveApplicationInDb(@RequestBody ApplicationDto dto) {
		var applicationDto = getApplicationService().saveApplication(dto);
		return applicationDto;
	}

	// @GetMapping("/get-notifications")
	// public NotificationsList getNotifications(@RequestParam("branch") String
	// branch) {
	// return getNotificationService().getNotifications(branch);
	// }

	// @GetMapping("/get-notification")
	// public NotificationDto getNotificationDetailsById(@RequestParam("id") Integer
	// notificationId) {
	// return getNotificationService().getNotificationById(notificationId);
	// }

	@GetMapping("/get-applications")
	public StudentApplicationList getApplicationsByNotificationId(
			@RequestParam("notificationId") String notificationId) {
		return null;
	}
}
