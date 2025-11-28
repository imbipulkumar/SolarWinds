package org.solarwinds.ApprovalWorkFlowEngine;

import lombok.Data;

@Data
public class ApprovalHistory {
    private int approvalLevel;
    private Actor actionTaker;
    private ApprovalAction actionTaken;
    private String approvalRemark;
    private long actionTimeStamp;

    public ApprovalHistory(int approvalLevel, Actor actionTaker, ApprovalAction actionTaken, String approvalRemark) {
        this.approvalLevel = approvalLevel;
        this.actionTaker = actionTaker;
        this.actionTaken = actionTaken;
        this.approvalRemark = approvalRemark;
        this.actionTimeStamp = System.currentTimeMillis();
    }
}
