package com.mcb.reports.entities.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import java.time.LocalDateTime;

@Entity
@Immutable
@Getter
@Setter
@NoArgsConstructor
public class PvsValuationRequestViews {

    @Id
    private Long id;
    private String reference;
    private LocalDateTime receivedOn;
    private String borrowersName;
    private String fosRef;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;

 
}
