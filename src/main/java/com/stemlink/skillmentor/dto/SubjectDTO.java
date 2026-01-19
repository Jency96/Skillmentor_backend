package com.stemlink.skillmentor.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.modelmapper.internal.bytebuddy.build.HashCodeAndEqualsPlugin;


//create this class to validate the fields
//not expose sensitive data
//loose coupling

@Data
public class SubjectDTO {


    @NotNull(message = "cannot be null")
    @Size(min = 5, message = "Subject must be at least 5 characters long")
    private String subjectName;

    @Size(max = 100, message = "Description must not exceed 100 characters")
    private String description;
}
