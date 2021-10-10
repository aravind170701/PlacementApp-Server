package app.placement.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.placement.dao.Notification;
import app.placement.dto.NotificationDto;
import app.placement.dto.NotificationsList;
import app.placement.helpers.NotificationHelper;
import app.placement.repositories.NotificationRepository;
import lombok.Getter;
import lombok.NonNull;
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

	public NotificationsList getNotifications(String branch) {
		try {
			if (StringUtils.isBlank(branch) || StringUtils.equals("ALL", branch)) {
				return getAllNotifications();
			}
			return getNotificationsByBranch(branch);
		} catch (RuntimeException e) {
			log.error("Error while fething the notification records: " + branch);
			var obj = new NotificationsList();
			obj.setNotifications(Collections.emptyList());
			return obj;
		}
	}

	private NotificationsList getAllNotifications() {
		return extractList(getNotificationRepository().findAllByOrderByNotificationIdDesc());
	}

	private NotificationsList getNotificationsByBranch(@NonNull String branch) {
		return extractList(getNotificationRepository().findAllByCompanyBranchOrderByNotificationIdDesc(branch));
	}

	private NotificationsList extractList(List<Notification> list) {
		var notifications = list.stream().map(getNotificationHelper()::convertEntityToDtoSimplified)
				.collect(Collectors.toList());
		var notificationsList = new NotificationsList();
		notificationsList.setNotifications(notifications);
		return notificationsList;
	}

	public NotificationDto getNotificationById(Integer notificationId) {
		if (notificationId == null || notificationId <= 0)
			return null;
		var notificationDetailsOpt = getNotificationRepository().findById(notificationId);
		if (notificationDetailsOpt.isEmpty()) {
			log.error("No notification details found by given ID: " + notificationId);
			return null;
		}
		return getNotificationHelper().convertEntityToDto(notificationDetailsOpt.get());
	}
}
