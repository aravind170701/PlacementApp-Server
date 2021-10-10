package app.placement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.placement.dto.ResponseObject;
import app.placement.dto.UserDto;
import app.placement.dto.UsersList;
import app.placement.service.UserService;
import app.placement.utils.Constants;
import lombok.Getter;

@Getter
@RestController
@CrossOrigin
@RequestMapping("rest/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseObject registerUser(@RequestBody UserDto userDto) {
		var response = new ResponseObject();
		if (getUserService().registerUser(userDto)) {
			response.setStatusCode(Constants.HTTP_STATUS_USER_REGISTERED);
			response.setResponse("true");
			response.setMessage("User Registered Sucessfully");
		} else {
			response.setStatusCode(Constants.HTTP_STATUS_USER_EXISTING);
			response.setResponse("false");
			response.setMessage("User already Registered");
		}
		return response;
	}

	@PostMapping("/login")
	public UserDto validateUserLogin(@RequestBody UserDto userDto) {
		return getUserService().validateUserCredentials(userDto);
	}

	@GetMapping("/get-users")
	public UsersList getUsers(@RequestParam("branch") String branch) {
		return getUserService().getUsers(branch);
	}

	@GetMapping("/get-user")
	public UserDto getUser(@RequestParam("prn") String prn) {
		return getUserService().getUserByPrn(prn);
	}

	@PutMapping("/update-profile")
	public ResponseObject updateUserProfile(@RequestBody UserDto userDto) {
		var isProfileUpdated = getUserService().updateProfile(userDto);
		var response = new ResponseObject();
		if (isProfileUpdated) {
			response.setStatusCode(Constants.HTTP_STATUS_SUCCESS);
			response.setMessage("User profie updated Sucessfully");
		} else {
			response.setStatusCode(Constants.HTTP_STATUS_ERROR);
			response.setMessage("User profile update failed");
		}
		return response;
	}
}
