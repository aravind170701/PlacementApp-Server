package app.placement.dao;

import java.io.Serializable;

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
@Table(name = "answers")
public class Answer implements Serializable{

    @Column
    private Integer questionId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column
    private Integer answerId;

    @Column
    private String answer;

    @Column
    private String studentName;

}
