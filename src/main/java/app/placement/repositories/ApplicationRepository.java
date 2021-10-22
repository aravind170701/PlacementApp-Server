package app.placement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.placement.dao.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Query("FROM Application a where a.user.prn = ?1 and a.notification.notificationId = ?2")
	public Optional<Application> findAllByPrnAndNotificationId(String prn, Integer notificationId);
	
    @Query("FROM Application a where a.notification.notificationId = ?1")
	public List<Application> findByNotificationId(Integer notificationId);
}
