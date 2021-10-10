package app.placement.helpers;

import org.springframework.stereotype.Component;

import app.placement.dao.StudentSemResult;
import app.placement.dao.User;
import app.placement.dto.SemResultsDto;
import app.placement.dto.UserDto;

@Component
public class UserHelper {
	public User getUserEntityFromBean(UserDto userDto) {
		if (userDto == null)
			return new User();

		var userEntity = new User();
		userEntity.setPrn(userDto.getPrn());
		userEntity.setName(userDto.getName());
		userEntity.setPassword(userDto.getPassword());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setBranch(userDto.getBranch());
		userEntity.setMobile(userDto.getMobile());
		userEntity.setDivision(userDto.getDivision());
		userEntity.setYear(userDto.getYear());
		userEntity.setUserType(userDto.getUserType());

		// Set Semester Results
		if (userDto.getSemResultsDto() != null) {
			var semDto = userDto.getSemResultsDto();
			var semResults = new StudentSemResult();

			semResults.setSem1(semDto.getSem1());
			semResults.setSem2(semDto.getSem2());
			semResults.setSem3(semDto.getSem3());
			semResults.setSem4(semDto.getSem4());
			semResults.setSem5(semDto.getSem5());
			semResults.setSem6(semDto.getSem6());
			semResults.setSem7(semDto.getSem7());
			semResults.setSem8(semDto.getSem8());

			// set CGPA
			calculateScores(semResults);
			semResults.setUser(userEntity);
			userEntity.setSemResults(semResults);
		}
		return userEntity;
	}

	private void calculateScores(StudentSemResult semResults) {
		int count = 0;
		int sum = 0;
		if (semResults.getSem1() > 0) {
			count++;
			sum += semResults.getSem1();
		}
		if (semResults.getSem2() > 0) {
			count++;
			sum += semResults.getSem2();
		}
		if (semResults.getSem3() > 0) {
			count++;
			sum += semResults.getSem3();
		}
		if (semResults.getSem4() > 0) {
			count++;
			sum += semResults.getSem4();
		}
		if (semResults.getSem5() > 0) {
			count++;
			sum += semResults.getSem5();
		}
		if (semResults.getSem6() > 0) {
			count++;
			sum += semResults.getSem6();
		}
		if (semResults.getSem7() > 0) {
			count++;
			sum += semResults.getSem7();
		}
		if (semResults.getSem8() > 0) {
			count++;
			sum += semResults.getSem8();
		}

		if (count > 0) {
			semResults.setCgpa((double) sum / count);
			semResults.setPercentage(semResults.getCgpa() * 8.8);
		}
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

		if (user.getSemResults() != null) {
			var results = user.getSemResults();
			var semDto = new SemResultsDto();
			semDto.setSem1(results.getSem1());
			semDto.setSem2(results.getSem2());
			semDto.setSem3(results.getSem3());
			semDto.setSem4(results.getSem4());
			semDto.setSem5(results.getSem5());
			semDto.setSem6(results.getSem6());
			semDto.setSem7(results.getSem7());
			semDto.setSem8(results.getSem8());
			semDto.setCgpa(results.getCgpa());
			semDto.setPercentage(results.getPercentage());
			userDto.setSemResultsDto(semDto);
		}
		return userDto;
	}

	public UserDto convertEntityToDtoSimplified(User user) {
		if (user == null)
			return null;

		var dto = new UserDto();
		dto.setPrn(user.getPrn());
		dto.setName(user.getName());
		return dto;
	}
}
