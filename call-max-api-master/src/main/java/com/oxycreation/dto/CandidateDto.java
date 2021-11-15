package com.oxycreation.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CandidateDto {

    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    @NotBlank(message = "lastName is mandatory")
    private String lastName;

    @NotBlank(message = "phoneNumber is mandatory")
    private String phoneNumber;

    @NotBlank(message = "gender is mandatory")
    private String gender;

    @NotBlank(message = "email is mandatory")
    private String email;

    @NotNull(message = "stateId is mandatory")
    private Long stateId;

    @NotBlank(message = "city is mandatory")
    private String city;

    @NotBlank(message = "address is mandatory")
    private String address;

    private Float workingExperience;

    @NotBlank(message = "companyName is mandatory")
    private String companyName;

    @NotBlank(message = "qualification is mandatory")
    private String qualification;

    @NotBlank(message = "university is mandatory")
    private String university;

    private String resume;

    private String resumeUrl;

    private String speedTest;

    private String speedTestUrl;

    private String skillTest;

    private String skillTestUrl;

    private Float speedTestUpload;

    private Float speedTestDownload;

    private Float skillTestSpeed;

    private Float skillTestAccuracy;

    private String description;

    private Long waveId;

    private Long userId;

    private Date assignDate;


}
