package app.placement.helpers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import app.placement.dao.StudentSemResult;
import app.placement.dao.User;
import app.placement.dto.UserDto;
import app.placement.utils.GenericUtils;

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
		if (!GenericUtils.isNullOrEmptyMap(userDto.getSemResults())) {
			var semResults = new StudentSemResult();
			var semResultsMap = userDto.getSemResults();
			semResults.setSem1(semResultsMap.get("sem1"));
			semResults.setSem2(semResultsMap.get("sem2"));
			semResults.setSem3(semResultsMap.get("sem3"));
			semResults.setSem4(semResultsMap.get("sem4"));
			semResults.setSem5(semResultsMap.get("sem5"));
			semResults.setSem6(semResultsMap.get("sem6"));
			semResults.setSem7(semResultsMap.get("sem7"));
			semResults.setSem8(semResultsMap.get("sem8"));

			// set CGPA
			calculateScores(semResults);
			semResults.setUser(userEntity);
			userEntity.setSemResults(semResults);
		}
		return userEntity;
	}

	private void calculateScores(StudentSemResult semResults) {
		int count = 0;
		double sum = 0;
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
			semResults.setCgpa(Math.round((sum / count) * 100.0 / 100.0));
			semResults.setPercentage(Math.round((semResults.getCgpa() * 8.8) * 100.0 / 100.0));
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
			Map<String, Double> semResults = new HashMap<>();
			semResults.put("sem1", results.getSem1());
			semResults.put("sem2", results.getSem2());
			semResults.put("sem3", results.getSem3());
			semResults.put("sem4", results.getSem4());
			semResults.put("sem5", results.getSem5());
			semResults.put("sem6", results.getSem6());
			semResults.put("sem7", results.getSem7());
			semResults.put("sem8", results.getSem8());
			semResults.put("cgpa", results.getCgpa());
			semResults.put("percentage", results.getPercentage());
			userDto.setSemResults(semResults);
		}
		return userDto;
	}

	public UserDto convertEntityToDtoSimplified(User user) {
		if (user == null)
			return null;

		var dto = new UserDto();
		dto.setPrn(user.getPrn());
		dto.setName(user.getName());
		dto.setBranch(user.getBranch());
		return dto;
	}
}
