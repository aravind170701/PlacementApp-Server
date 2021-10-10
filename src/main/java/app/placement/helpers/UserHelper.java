package app.placement.helpers;

import org.springframework.stereotype.Component;

import app.placement.dao.User;
import app.placement.dto.UserDto;

@Component
public class UserHelper {
	public User getUserEntityFromBean(UserDto userDto) {
		if (userDto == null)
			return new User();

		var userEntiry = new User();
		userEntiry.setPrn(userDto.getPrn());
		userEntiry.setName(userDto.getName());
		userEntiry.setPassword(userDto.getPassword());
		userEntiry.setEmail(userDto.getEmail());
		userEntiry.setBranch(userDto.getBranch());
		userEntiry.setMobile(userDto.getMobile());
		userEntiry.setDivision(userDto.getDivision());
		userEntiry.setYear(userDto.getYear());
		userEntiry.setUserType(userDto.getUserType());

		return userEntiry;
	}

	public UserDto getUserBeanFromEntity(User user) {
		if (user == null)
			return new UserDto();

		var userDto = new UserDto();
		userDto.setPrn(user.getPrn());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setBranch(user.getBranch());
		userDto.setMobile(user.getMobile());
		userDto.setDivision(user.getDivision());
		userDto.setYear(user.getYear());
		userDto.setUserType(user.getUserType());

		return userDto;
	}
}
