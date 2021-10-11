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
        application.setNotificationId(dto.getNotificationId());
        application.setPrn(dto.getPrn());
        application.setStatus(dto.getStatus());

		return application;
	}

	// public NotificationDto convertEntityToDto(Notification notification) {
	// 	if (notification == null)
	// 		return null;

	// 	var dto = new NotificationDto();
	// 	dto.setNotificationId(notification.getNotificationId());
	// 	dto.setCompanyName(notification.getCompanyName());
	// 	dto.setCompanyBranch(notification.getCompanyBranch());
	// 	dto.setDate(notification.getDate());
	// 	dto.setCompanyPackage(notification.getCompanyPackage());
	// 	dto.setCompanyVenue(notification.getCompanyVenue());
	// 	dto.setCompanyDescription(notification.getCompanyDescription());
	// 	return dto;
	// }

	// public NotificationDto convertEntityToDtoSimplified(Notification notification) {
	// 	if (notification == null)
	// 		return null;

	// 	var dto = new NotificationDto();
	// 	dto.setNotificationId(notification.getNotificationId());
	// 	dto.setCompanyName(notification.getCompanyName());
	// 	return dto;
	// }
}
