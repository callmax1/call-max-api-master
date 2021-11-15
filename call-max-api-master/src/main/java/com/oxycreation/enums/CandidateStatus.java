package com.oxycreation.enums;


import java.util.Arrays;

public enum CandidateStatus {

    Completed(1,"Completed"),
    InProcess(2,"In Process"),
    OnHold(3,"On Hold"),
    AssignedToTeam(4,"Assigned to Team"),
    PendingNewHireOrientation(5,"Pending New Hire Orientation"),
    PendingOnBoarding(6,"Pending On boarding"),
    PendingProductSpecificTraining(7,"Pending Product Specific Training"),
    PaidByCompany(8," Paid by Company"),
    NotInterested(9,"Not Interested"),
    LookingForHigherPay(10,"Looking for Higher Pay"),
    NotReachableThroughPhone(11,"Not Reachable Through Phone "),
    NotReachableThroughEmail(12,"Not Reachable Through Email"),
    Others(13,"Others");

    private final Integer statusSeq;
    private final String status;

    CandidateStatus(Integer statusSeq, String status) {
        this.statusSeq = statusSeq;
        this.status = status;
    }

    public Integer getStatusSeq() {
        return statusSeq;
    }

    public String getStatus() {
        return status;
    }

    public CandidateStatus findOne(Integer statusSeq){
        return   Arrays.stream(CandidateStatus.values())
                .filter(x -> x.statusSeq.equals(statusSeq))
                .findFirst()
                .orElse(null);

    }
}
