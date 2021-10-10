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
public class ResponseObject implements Serializable {

	private static final long serialVersionUID = -3626714222444014620L;

	private String statusCode;
	private String response;
	private String message;
}
