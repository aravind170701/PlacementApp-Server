package app.placement.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "sem_results")
public class StudentSemResult implements Serializable {

	private static final long serialVersionUID = -1753011092584862518L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private double sem1;
	@Column
	private double sem2;
	@Column
	private double sem3;
	@Column
	private double sem4;
	@Column
	private double sem5;
	@Column
	private double sem6;
	@Column
	private double sem7;
	@Column
	private double sem8;
	@Column(precision = 10, scale = 2)
	private double cgpa;
	@Column(precision = 10, scale = 2)
	private double percentage;

	@OneToOne(mappedBy = "semResults")
	private User user;
}
