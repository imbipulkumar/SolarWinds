package org.solarwinds.ApprovalWorkFlowEngine;

import lombok.Data;

@Data
public class ApprovalRequest {
    private int workFlowTemplateId;
    private int workflowInstanceId;
    private Actor actionTaker;
    private ApprovalAction actionTaken;
    private String approvalRemark;
}
