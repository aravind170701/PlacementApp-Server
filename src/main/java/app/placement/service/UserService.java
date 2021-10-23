package app.placement.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.placement.dao.StudentSemResult;
import app.placement.dao.User;
import app.placement.dto.UserDto;
import app.placement.dto.UsersList;
import app.placement.helpers.UserHelper;
import app.placement.repositories.UserRepository;
import app.placement.utils.GenericUtils;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Getter
@Service
@Slf4j
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserHelper userHelper;

	public boolean registerUser(UserDto userDto) {
		if (userDto == null || StringUtils.isBlank(userDto.getPrn())) {
			log.error("User Details/PRN Number is empty.");
			return false;
		}

		// check if the user already registered
		var existingUser = getUserRepository().findById(userDto.getPrn());
		if (existingUser.isPresent()) {
			log.error("User Details already registered with given PRN: " + userDto.getPrn());
			return false;
		}

		// save the User details
		var userEntity = getUserHelper().getUserEntityFromBean(userDto);
		getUserRepository().save(userEntity);
		return true;
	}

	public UserDto validateUserCredentials(UserDto userDto) {
		if (userDto == null || StringUtils.isBlank(userDto.getPrn()) || StringUtils.isBlank(userDto.getPassword())) {
			log.error("User Login creds are Empty. Cannot validtate user");
			return new UserDto();
		}

		/*
		 * Validate PRN and Password. If present, send the User type details in response
		 */
		var userDetailsOpt = getUserRepository().findByPrnAndPassword(userDto.getPrn(), userDto.getPassword());
		if (userDetailsOpt.isEmpty()) {
			log.debug("Given Login credentials does not exist");
			return new UserDto();
		}

		var returnedUserDto = getUserHelper().getUserBeanFromEntity(userDetailsOpt.get());
		returnedUserDto.setLoginSuccessful(true);
		return returnedUserDto;
	}

	public UsersList getUsers(String branch) {
		try {
			if (StringUtils.isBlank(branch) || StringUtils.equals("ALL", branch)) {
				return getAllUsers();
			}
			return getUsersByBranch(branch);
		} catch (RuntimeException e) {
			log.error("Error while fething the notification records: " + branch);
			var obj = new UsersList();
			obj.setUsers(Collections.emptyList());
			return obj;
		}
	}

	public  UsersList getAllUsers() {
		return extractList(getUserRepository().findAllByOrderByNameAsc());
	}

	private UsersList getUsersByBranch(@NonNull String branch) {
		return extractList(getUserRepository().findAllByBranchOrderByNameAsc(branch));
	}

	private UsersList extractList(List<User> list) {
		var users = list.stream().filter(Predicate.not(i -> "0".equals(i.getUserType())))
				.map(getUserHelper()::convertEntityToDtoSimplified).collect(Collectors.toList());
		var usersList = new UsersList();
		usersList.setUsers(users);
		return usersList;
	}

	public UserDto getUserByPrn(String prn) {
		if (StringUtils.isBlank(prn))
			return null;
		var usersDetailsOpt = getUserRepository().findByPrn(prn);
		if (usersDetailsOpt.isEmpty()) {
			log.error("No User details found by given PRN: " + prn);
			return null;
		}
		return getUserHelper().getUserBeanFromEntity(usersDetailsOpt.get());
	}

	public boolean updateProfile(UserDto userDto) {
		if (userDto == null || StringUtils.isBlank(userDto.getPrn())) {
			log.error("Cannot update Profile. Details are Empty");
			return false;
		}
		// check if the record is present
		var existingUserOpt = getUserRepository().findByPrn(userDto.getPrn());
		if (existingUserOpt.isEmpty()) {
			log.error("User not existing. Cannot update details");
			return false;
		}

		var user = existingUserOpt.get();
		if (userDto.getDivision() > 0) {
			user.setDivision(userDto.getDivision());
		}
		if (StringUtils.isNotBlank(userDto.getYear())) {
			user.setYear(userDto.getYear());
		}
		if (StringUtils.isNotBlank(userDto.getHscPercentage())) {
			user.setHscPercentage(userDto.getHscPercentage());
		}
		if (StringUtils.isNotBlank(userDto.getSscPercentage())) {
			user.setSscPercentage(userDto.getSscPercentage());
		}
		if (StringUtils.isNotBlank(userDto.getNoOfBacklogs())) {
			user.setNoOfBacklogs(userDto.getNoOfBacklogs());
		}
		if (StringUtils.isNotBlank(userDto.getMobile()) && userDto.getMobile().matches("[0-9]+")) {
			user.setMobile(userDto.getMobile());
		}
		if(StringUtils.isNotBlank(userDto.getCvUrl())){
		   user.setCvUrl(userDto.getCvUrl());
		}
		updateSemesterScores(user, userDto);
		getUserRepository().save(user);
		return true;
	}

	private void updateSemesterScores(User user, UserDto userDto) {
		if (GenericUtils.isNullOrEmptyMap(userDto.getSemResults()))
			return;

		var semResults = user.getSemResults();
		if (semResults == null) {
			semResults = new StudentSemResult();
		}
		var semMap = userDto.getSemResults();
		semResults.setSem1(semMap.getOrDefault("sem1", semResults.getSem1()));
		semResults.setSem2(semMap.getOrDefault("sem2", semResults.getSem2()));
		semResults.setSem3(semMap.getOrDefault("sem3", semResults.getSem3()));
		semResults.setSem4(semMap.getOrDefault("sem4", semResults.getSem4()));
		semResults.setSem5(semMap.getOrDefault("sem5", semResults.getSem5()));
		semResults.setSem6(semMap.getOrDefault("sem6", semResults.getSem6()));
		semResults.setSem7(semMap.getOrDefault("sem7", semResults.getSem7()));
		semResults.setSem8(semMap.getOrDefault("sem8", semResults.getSem8()));

		calculateScores(semResults, semMap);
		semResults.setUser(user);

		// set in user
		user.setSemResults(semResults);
	}

	private void calculateScores(StudentSemResult semResults, Map<String, Double> semMap) {
		double sum = 0;
		int count = 0;
		for (Map.Entry<String, Double> entry : semMap.entrySet()) {
			if (entry.getValue() > 0) {
				count++;
			}
			sum += entry.getValue();
		}
		if (count > 0) {
			semResults.setCgpa(Math.round((sum / count) * 100.0 / 100.0));
			semResults.setPercentage(Math.round((semResults.getCgpa() * 8.8) * 100.0 / 100.0));
		}
	}
}
