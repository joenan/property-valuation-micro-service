package com.mcb.bpea.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CommentDto {
    private long id;
    private LocalDateTime date;
    private String username;
    private String commentText;
    private long propertyValuationId;
}
