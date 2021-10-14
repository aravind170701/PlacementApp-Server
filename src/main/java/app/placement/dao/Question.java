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
@Table(name = "questions")
public class Question implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column
    private Integer questionId;

    @Column
    private String question;
}
