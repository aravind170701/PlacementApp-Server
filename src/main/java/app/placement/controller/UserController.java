package app.placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.placement.dto.UserDto;
import app.placement.service.UserService;
import lombok.Getter;

@Getter
@RestController
@RequestMapping("rest/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public boolean registerUser(@RequestBody UserDto userDto) {
		return getUserService().registerUser(userDto);
	}

	@PostMapping("/login")
	public UserDto validateUserLogin(@RequestBody UserDto userDto) {
		return getUserService().validateUserCredentials(userDto);
	}
}
