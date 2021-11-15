package com.oxycreation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class WaveDto {

    @NotNull(message = "verticalId is mandatory")
    private Long lobId;

    @NotBlank(message = "name is mandatory")
    private String name;

    private Date newHireOrientation;

    private Date onBoarding;

    private Date productSpecificTraining;

    private Integer numberOfCandidate;


    private String comment;

    public Long getLobId() {
        return lobId;
    }

    public void setLobId(Long lobId) {
        this.lobId = lobId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getNewHireOrientation() {
        return newHireOrientation;
    }

    public void setNewHireOrientation(Date newHireOrientation) {
        this.newHireOrientation = newHireOrientation;
    }

    public Date getOnBoarding() {
        return onBoarding;
    }

    public void setOnBoarding(Date onBoarding) {
        this.onBoarding = onBoarding;
    }

    public Date getProductSpecificTraining() {
        return productSpecificTraining;
    }

    public void setProductSpecificTraining(Date productSpecificTraining) {
        this.productSpecificTraining = productSpecificTraining;
    }

    public Integer getNumberOfCandidate() {
        return numberOfCandidate;
    }

    public void setNumberOfCandidate(Integer numberOfCandidate) {
        this.numberOfCandidate = numberOfCandidate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
