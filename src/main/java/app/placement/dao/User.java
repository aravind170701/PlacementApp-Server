package app.placement.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "user_details")
public class User {

	@Id
	@Column
	private String prn;

	@Column
	private String name;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column
	private String password;

	@Column
	private String email;

	@Column
	private String branch;

	@Column
	private int division;

	@Column
	private String mobile;

	@Column(name = "user_type")
	private String userType;

	@Column
	private String year;
}
