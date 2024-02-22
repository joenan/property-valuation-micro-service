package com.mcb.commons.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ValuationProjection {

    private Long id;
    private String reference;
    private LocalDateTime receivedOn;
    private String borrowersName;
    private String fosRef;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
}
