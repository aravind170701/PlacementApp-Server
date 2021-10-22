package app.placement.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "application_details")
public class Application implements Serializable {

	private static final long serialVersionUID = 4985753136026648777L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer applicationId;

	@Column
	private String overallStatus;

	@ManyToOne
	@JoinColumn(name = "prn", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "notificationId", nullable = false)
	private Notification notification;
}
