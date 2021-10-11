package app.placement.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "user_details")
public class Application implements Serializable{

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
    private Integer applicationId;
    
    @Column
    private String prn;

    @Column
    private Integer notificationId;

    @Column
    private String status;
}
