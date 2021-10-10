package app.placement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NotificationDto {
	private String notificationId;
	private String companyName;
	private String companyBranch;
	private String date;
	private String companyPackage;
	private String companyVenue;
	private String companyDescription;
}
