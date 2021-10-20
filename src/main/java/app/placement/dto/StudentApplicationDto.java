package app.placement.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StudentApplicationDto implements Serializable {

	private static final long serialVersionUID = -4975369538230466360L;

	private String prn;
	private String name;
	private String branch;
	private String email;
	private String companyName;
}
