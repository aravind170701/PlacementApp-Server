package app.placement.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.placement.dto.UserDto;
import app.placement.helpers.UserHelper;
import app.placement.repositories.UserRepository;
import lombok.Getter;
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
}
