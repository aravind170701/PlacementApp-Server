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
public class NotificationDto implements Serializable {

	private static final long serialVersionUID = 5323005367561606611L;

	private Integer notificationId;
	private String companyName;
	private String companyBranch;
	private String date;
	private String companyPackage;
	private String companyVenue;
	private String companyDescription;
}
