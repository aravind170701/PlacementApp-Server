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
public class NotificationsList implements Serializable {

	private static final long serialVersionUID = 8214059974252396564L;

	private List<NotificationDto> notifications;
}
