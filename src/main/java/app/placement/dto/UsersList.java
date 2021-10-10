package app.placement.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UsersList implements Serializable {

	private static final long serialVersionUID = -3648456843117941529L;

	private List<UserDto> users;
}
