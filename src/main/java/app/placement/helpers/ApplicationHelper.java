package app.placement.helpers;

import org.springframework.stereotype.Component;

import app.placement.dao.Application;
import app.placement.dto.ApplicationDto;


@Component
public class ApplicationHelper {

	public Application convertDtoToEntity(ApplicationDto dto) {
		if (dto == null)
			return null;

		var application = new Application();
        application.setPrn(dto.getPrn());
        application.setOverallStatus(dto.getOverallStatus());
		return application;
	}

	public ApplicationDto convertEntityToDto(Application application) {
		if (application == null)
			return null;

		var dto = new ApplicationDto();
		dto.setApplicationId(application.getApplicationId());
		dto.setPrn(application.getPrn());
		dto.setNotificationId(application.getNotification().getNotificationId());
		dto.setOverallStatus(application.getOverallStatus());

		return dto;
	}

	// public NotificationDto convertEntityToDtoSimplified(Notification notification) {
	// 	if (notification == null)
	// 		return null;

	// 	var dto = new NotificationDto();
	// 	dto.setNotificationId(notification.getNotificationId());
	// 	dto.setCompanyName(notification.getCompanyName());
	// 	return dto;
	// }
}
