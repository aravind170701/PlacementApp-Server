package app.placement.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.placement.dao.User;
import app.placement.dto.UserDto;
import app.placement.dto.UsersList;
import app.placement.helpers.UserHelper;
import app.placement.repositories.UserRepository;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Getter
@Service
@Slf4j
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

	private UsersList getAllUsers() {
		return extractList(getUserRepository().findAllByOrderByNameAsc());
	}

	private UsersList getUsersByBranch(@NonNull String branch) {
		return extractList(getUserRepository().findAllByBranchOrderByNameAsc(branch));
	}

	private UsersList extractList(List<User> list) {
		var users = list.stream().map(getUserHelper()::convertEntityToDtoSimplified).collect(Collectors.toList());
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
}
