package app.placement.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StudentApplicationList {

	private List<StudentApplicationDto> studentApplicationsDto;
}
