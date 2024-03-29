package app.placement.dao;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "company_notifications")
public class Notification implements Serializable {

	private static final long serialVersionUID = 3444763993159840377L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer notificationId;
	@Column
	private String companyName;
	@Column
	private String companyBranch;
	@Column
	private String date;
	@Column
	private String companyPackage;
	@Column
	private String companyVenue;
	@Column
	private String companyDescription;

	@OneToMany(targetEntity = Application.class, mappedBy = "notification")
	private Set<Application> applications;
}
