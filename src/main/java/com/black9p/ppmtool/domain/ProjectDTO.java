package com.black9p.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class ProjectDTO {
    @NotBlank(message = "Project Name is Required")
    private String projectName;
    @NotBlank(message = "Project Identifier is Required")
    @Size(min=4, max=5, message = "Please use 4 to 5 characters")
    private String projectIdentifier;
    @NotBlank(message = "Project Description is Required")
    private String description;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime endDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime updatedAt;
}
