package com.oxycreation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LobDto {

    @NotNull(message = "companyId is mandatory")
    private Long companyId;

    @NotNull(message = "verticalId is mandatory")
    private Long verticalId;

    @NotBlank(message = "name is mandatory")
    private String name;

    private String pitch;

    private String clientObservedHoliday;

    private String hoursOfOperation;

    private String trainingDuration;

    private Double missedInTrainingTermination;

    private Double payRateDuringTraining;

    private Double payRateDuringProduction;

    private Boolean backgroundCheck;

    private Boolean drugScreening;

    private Double missedInTraining;



    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getVerticalId() {
        return verticalId;
    }

    public void setVerticalId(Long verticalId) {
        this.verticalId = verticalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public String getClientObservedHoliday() {
        return clientObservedHoliday;
    }

    public void setClientObservedHoliday(String clientObservedHoliday) {
        this.clientObservedHoliday = clientObservedHoliday;
    }

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    public String getTrainingDuration() {
        return trainingDuration;
    }

    public void setTrainingDuration(String trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

    public Double getMissedInTrainingTermination() {
        return missedInTrainingTermination;
    }

    public void setMissedInTrainingTermination(Double missedInTrainingTermination) {
        this.missedInTrainingTermination = missedInTrainingTermination;
    }

    public Double getPayRateDuringTraining() {
        return payRateDuringTraining;
    }

    public void setPayRateDuringTraining(Double payRateDuringTraining) {
        this.payRateDuringTraining = payRateDuringTraining;
    }

    public Double getPayRateDuringProduction() {
        return payRateDuringProduction;
    }

    public void setPayRateDuringProduction(Double payRateDuringProduction) {
        this.payRateDuringProduction = payRateDuringProduction;
    }

    public Boolean getBackgroundCheck() {
        return backgroundCheck;
    }

    public void setBackgroundCheck(Boolean backgroundCheck) {
        this.backgroundCheck = backgroundCheck;
    }

    public Boolean getDrugScreening() {
        return drugScreening;
    }

    public void setDrugScreening(Boolean drugScreening) {
        this.drugScreening = drugScreening;
    }

    public Double getMissedInTraining() {
        return missedInTraining;
    }

    public void setMissedInTraining(Double missedInTraining) {
        this.missedInTraining = missedInTraining;
    }

}
