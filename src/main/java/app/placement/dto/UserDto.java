package app.placement.dto;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto implements Serializable {

	private static final long serialVersionUID = -2561159185495962167L;

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
	private String cvUrl;
	private Map<String, Double> semResults;
}
