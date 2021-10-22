package app.placement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.placement.dao.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	public Optional<Application> findAllByPrnAndNotificationId(String prn, Integer notificationId);

	public List<Application> findByNotificationId(Integer notificationId);
}
