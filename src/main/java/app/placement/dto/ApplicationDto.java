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
public class ApplicationDto implements Serializable{
    
    private String applicationId;
    private Integer notificationId;
    private String prn;
    private String status;
}
