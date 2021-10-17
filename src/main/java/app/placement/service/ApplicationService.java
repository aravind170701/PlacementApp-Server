package app.placement.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.placement.dao.Application;
import app.placement.dto.ApplicationDto;
import app.placement.helpers.ApplicationHelper;
import app.placement.repositories.ApplicationRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Service
@Slf4j
@Transactional
public class ApplicationService {

    @Autowired
	private ApplicationRepository applicationRepository;
	@Autowired
	private ApplicationHelper applicationHelper;

    public ApplicationDto saveApplication(ApplicationDto applicationDto) {
		if (applicationDto == null) {
			log.error("Application Form DTO is null");
			return null;
		}

		// Persist notification in DB
		var application = getApplicationHelper().convertDtoToEntity(applicationDto);
		Optional<Application> optional = getApplicationRepository().findByPrnAndNotificationId(application.getPrn(),
										application.getNotificationId());

		if(optional.isEmpty())
		{
			application = getApplicationRepository().save(application);
			return getApplicationHelper().convertEntityToDto(application);
		}

		Application dbFetchedApplication = optional.get();
		dbFetchedApplication.setOverallStatus(application.getOverallStatus());
		getApplicationRepository().save(dbFetchedApplication);
		return getApplicationHelper().convertEntityToDto(dbFetchedApplication);
	}

	// public NotificationsList getNotifications(String branch) {
	// 	try {
	// 		if (StringUtils.isBlank(branch) || StringUtils.equals("ALL", branch)) {
	// 			return getAllNotifications();
	// 		}
	// 		return getNotificationsByBranch(branch);
	// 	} catch (RuntimeException e) {
	// 		log.error("Error while fething the notification records: " + branch);
	// 		var obj = new NotificationsList();
	// 		obj.setNotifications(Collections.emptyList());
	// 		return obj;
	// 	}
	// }

	// private NotificationsList getAllNotifications() {
	// 	return extractList(getNotificationRepository().findAllByOrderByNotificationIdDesc());
	// }

	// private NotificationsList getNotificationsByBranch(@NonNull String branch) {
	// 	return extractList(getNotificationRepository().findAllByCompanyBranchOrderByNotificationIdDesc(branch));
	// }

	// private NotificationsList extractList(List<Notification> list) {
	// 	var notifications = list.stream().map(getNotificationHelper()::convertEntityToDtoSimplified)
	// 			.collect(Collectors.toList());
	// 	var notificationsList = new NotificationsList();
	// 	notificationsList.setNotifications(notifications);
	// 	return notificationsList;
	// }

	// public NotificationDto getNotificationById(Integer notificationId) {
	// 	if (notificationId == null || notificationId <= 0)
	// 		return null;
	// 	var notificationDetailsOpt = getNotificationRepository().findById(notificationId);
	// 	if (notificationDetailsOpt.isEmpty()) {
	// 		log.error("No notification details found by given ID: " + notificationId);
	// 		return null;
	// 	}
	// 	return getNotificationHelper().convertEntityToDto(notificationDetailsOpt.get());
	// }
    
}
