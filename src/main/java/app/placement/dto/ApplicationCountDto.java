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
public class ApplicationCountDto implements Serializable {
    private int onHoldCount  = 0;
    private int inProcessCount = 0;
    private int placedCount = 0;
    private int notPlacedCount = 0;
}
