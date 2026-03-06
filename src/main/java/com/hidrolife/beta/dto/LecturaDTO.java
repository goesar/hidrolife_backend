
package com.hidrolife.beta.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LecturaDTO {
    private Double humedad;
    private Double temperatura;
    private Double ph;
    private Double tds;
}
