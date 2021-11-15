package com.oxycreation.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CandidateCommentDto {
    @NotNull(message = "candidateId is mandatory")
    private Long candidateId;

    @NotBlank(message = "comment is mandatory")
    private String comment;
}
