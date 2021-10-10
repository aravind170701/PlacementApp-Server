package app.placement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

	private boolean loginSuccessful;

	private String prn;
	private String name;
	private String password;
	private String email;
	private String branch;
	private int division;
	private String mobile;
	private String userType;
	private String year;
	private SemResultsDto semResultsDto;
}
