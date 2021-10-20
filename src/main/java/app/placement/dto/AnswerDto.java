package app.placement.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AnswerDto implements Serializable {

	private static final long serialVersionUID = -2466272553538522457L;
	private Integer questionId;
	private Integer answerId;
	private String answer;
	private String studentName;

}
