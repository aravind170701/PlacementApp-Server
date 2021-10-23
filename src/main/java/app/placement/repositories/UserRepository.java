package app.placement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.placement.dao.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByPrnAndPassword(String prn, String password);

	Optional<User> findByPrn(String prn);
    
	List<User> findAllByOrderByNameAsc();

	List<User> findAllByBranchOrderByNameAsc(String branch);
}
