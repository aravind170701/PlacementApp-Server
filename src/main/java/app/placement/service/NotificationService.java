package app.placement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.placement.dto.NotificationDto;
import app.placement.helpers.NotificationHelper;
import app.placement.repositories.NotificationRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Service
@Slf4j
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private NotificationHelper notificationHelper;

	public boolean saveNotification(NotificationDto notificationDto) {
		if (notificationDto == null) {
			log.error("Notification DTO is null");
			return false;
		}

		// Persist notification in DB
		var notificationDao = getNotificationHelper().convertDtoToEntity(notificationDto);
		getNotificationRepository().save(notificationDao);
		return true;
	}
}
