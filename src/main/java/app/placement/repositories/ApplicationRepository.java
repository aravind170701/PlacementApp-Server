package app.placement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.placement.dao.Application;


public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	List<Application> findAllByCompanyBranchOrderByNotificationIdDesc(String branch);

	List<Application> findAllByOrderByNotificationIdDesc();
}
