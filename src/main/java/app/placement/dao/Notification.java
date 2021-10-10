package app.placement.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "company_notifications")
public class Notification {

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
}