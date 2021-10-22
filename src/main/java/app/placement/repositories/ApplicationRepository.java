package app.placement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.placement.dao.Application;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Query("FROM Application a where a.user.prn = ?1 and a.notification.notificationId = ?2")
	public Optional<Application> findAllByPrnAndNotificationId(String prn, Integer notificationId);

	public List<Application> findByNotificationId(Integer notificationId);
}
