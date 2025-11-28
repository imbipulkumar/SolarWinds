package org.solarwinds.WorkFlow;

import lombok.Data;

import java.util.Map;

/**
 * This class is a representation of configurable WorkFlowTemplate per tenantId and category which holds the policies(rules)
 * for this workflow.
 *
 * Policies are defined for each level of the workflow with details like allowedActors, allowedActions, etc.
 * eg.:
 * work_flow_policies{
 *     level_0: {
 *         allowed_actors:[...],
 *         allowed_actions:[...]
 *     },
 *     level_1: {
 *         allowed_actors:[...],
 *  *      allowed_actions:[...]
 *     }
 * }
 */
@Data
public class WorkFlowTemplate {
    private int id;
    private int tenantId;
    private String category;
    private int levels;
    private Map<Integer, Policy> policies;

    public WorkFlowTemplate(int id, int tenantId, String category, int levels, Map<Integer, Policy> policies) {
        this.id = id;
        this.tenantId = tenantId;
        this.category = category;
        this.levels = levels;
        this.policies = policies;
    }
}

