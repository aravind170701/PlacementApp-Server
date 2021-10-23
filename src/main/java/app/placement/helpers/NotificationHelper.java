package app.placement.helpers;

import org.springframework.stereotype.Component;

import app.placement.dao.Notification;
import app.placement.dto.NotificationDto;

@Component
public class NotificationHelper {

	public Notification convertDtoToEntity(NotificationDto dto) {
		if (dto == null)
			return null;

		var notification = new Notification();
		notification.setCompanyName(dto.getCompanyName());
		notification.setCompanyBranch(dto.getCompanyBranch());
		notification.setDate(dto.getDate());
		notification.setCompanyPackage(dto.getCompanyPackage());
		notification.setCompanyVenue(dto.getCompanyVenue());
		notification.setCompanyDescription(dto.getCompanyDescription());
		return notification;
	}

	public NotificationDto convertEntityToDto(Notification notification) {
		if (notification == null)
			return null;

		var dto = new NotificationDto();
		dto.setNotificationId(notification.getNotificationId());
		dto.setCompanyName(notification.getCompanyName());
		dto.setCompanyBranch(notification.getCompanyBranch());
		dto.setDate(notification.getDate());
		dto.setCompanyPackage(notification.getCompanyPackage());
		dto.setCompanyVenue(notification.getCompanyVenue());
		dto.setCompanyDescription(notification.getCompanyDescription());
		return dto;
	}

	public NotificationDto convertEntityToDtoSimplified(Notification notification) {
		if (notification == null)
			return null;

		var dto = new NotificationDto();
		dto.setNotificationId(notification.getNotificationId());
		dto.setCompanyName(notification.getCompanyName());
		dto.setCompanyBranch(notification.getCompanyBranch());
		return dto;
	}
}
