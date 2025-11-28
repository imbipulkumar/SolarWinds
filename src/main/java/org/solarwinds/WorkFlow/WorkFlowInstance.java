package org.solarwinds.WorkFlow;

import lombok.Data;
import org.solarwinds.ApprovalWorkFlowEngine.Actor;
import org.solarwinds.ApprovalWorkFlowEngine.ApprovalHistory;
import org.solarwinds.ApprovalWorkFlowEngine.ApprovalStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This class is a representation of the active/live workflow instance for a WorkFlowTemplate.
 *
 * approval_histories: [
 *     {
 *         approvalLevel:<>,
 *         actionTaker:<>,
 *         approvalAction:<>,
 *         approvalRemark:<>,
 *         auditInfo:<actionTimeStamp,...>,
 *     },...
 *  ]
 */
@Data
public class WorkFlowInstance {
    private int id;
    private int templateId;
    private int currentLevel;
    private ApprovalStatus currentApprovalStatus;
    private Set<Actor> currentActionTakers;
    private List<ApprovalHistory> approvalHistories;

    public WorkFlowInstance() {
        this.approvalHistories = new ArrayList<>();
    }
}
