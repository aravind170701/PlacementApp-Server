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
}
