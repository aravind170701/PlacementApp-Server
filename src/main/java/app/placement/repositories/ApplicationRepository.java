package app.placement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.placement.dao.Application;


public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}
