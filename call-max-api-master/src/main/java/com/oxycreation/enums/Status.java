package com.oxycreation.enums;


import java.util.Arrays;

public enum Status {
    DELETED(0,"Deleted"),
    Active(1,"Active"),
    InActive(2,"InActive"),
    OPEN(3,"Open");

    private final Integer statusSeq;
    private final String status;

    Status(Integer statusSeq, String status) {
        this.statusSeq = statusSeq;
        this.status = status;
    }

    public Integer getStatusSeq() {
        return statusSeq;
    }

    public String getStatus() {
        return status;
    }

    public  Status findOne(Integer statusSeq){
        return   Arrays.stream(Status.values())
                .filter(x -> x.statusSeq.equals(statusSeq))
                .findFirst()
                .orElse(null);

    }
}
