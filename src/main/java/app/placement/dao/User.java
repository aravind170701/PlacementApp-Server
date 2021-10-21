package app.placement.dao;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class User implements Serializable {

	private static final long serialVersionUID = 6244648778381076850L;

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

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "sem_results_id", referencedColumnName = "id")
	private StudentSemResult semResults;

	@Column
	private String cvUrl;

	// @OneToMany(mappedBy = "user")
	// private Set<Application> applications;
}
