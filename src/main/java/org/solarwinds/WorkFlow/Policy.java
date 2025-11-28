package org.solarwinds.WorkFlow;

import lombok.Data;
import org.solarwinds.ApprovalWorkFlowEngine.Actor;
import org.solarwinds.ApprovalWorkFlowEngine.ApprovalAction;

import java.util.Set;

@Data
public class Policy {
    private Set<Actor> allowedActors;
    private Set<ApprovalAction> allowedActions;
    private double allowedMinAmount;
    private double allowedMaxAmount;
}
