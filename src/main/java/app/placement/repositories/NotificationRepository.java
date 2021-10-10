package app.placement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.placement.dao.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

	List<Notification> findAllByCompanyBranch(String branch);
}
