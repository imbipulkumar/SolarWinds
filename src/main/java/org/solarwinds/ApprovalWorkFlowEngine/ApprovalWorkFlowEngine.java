package org.solarwinds.ApprovalWorkFlowEngine;

import lombok.NonNull;
import org.solarwinds.ApprovalWorkFlowEngine.Exception.ApprovalEngineException;
import org.solarwinds.WorkFlow.WorkFlowInstance;
import org.solarwinds.WorkFlow.WorkFlowTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ApprovalWorkFlowEngine is the core class of the solution which carries out the approval work flow state management, transition and validations
 * based on the workflow policies defined in the corresponding WorkFlowTemplate for the active/live WorkFlowInstance.
 *
 * NOTE:
 * Since workflow state transitions are liner meaning level0 -> level1 -> level2 -> level1 -> level2 .... till last level,
 * the corresponding WorkFlowTemplate for the active/live WorkFlowInstance will aid in validating and executing the workflow state transitions.
 */
public class ApprovalWorkFlowEngine {
    private Map<Integer, WorkFlowTemplate> approvalWorkFlowTemplatesRepository;

    private Map<Integer, WorkFlowInstance> approvalWorkFlowInstancesRepository;

    public ApprovalWorkFlowEngine() {
        this.approvalWorkFlowTemplatesRepository = new HashMap<>();
        this.approvalWorkFlowInstancesRepository = new HashMap<>();
    }

    public int addTemplate(WorkFlowTemplate workFlowTemplate) throws ApprovalEngineException {
        validateTemplate(workFlowTemplate);

        workFlowTemplate.setId(UUID.randomUUID().hashCode());

        approvalWorkFlowTemplatesRepository.put(workFlowTemplate.getId(), workFlowTemplate);

        return workFlowTemplate.getId();
    }

    public String submitRequest(@NonNull ApprovalRequest approvalRequest) throws ApprovalEngineException {
        switch (approvalRequest.getActionTaken()) {
            case CREATE -> {
                executeCreateRequest(approvalRequest);
            }

            case REVISE -> {
                // TODO:
            }

            case APPROVE -> {
                // TODO:
            }

            case REJECT -> {
                // TODO:
            }
        }

        return "Success!";
    }

    /**
     * ApprovalAction CREATE marks the beginning of an approval workflow thus this method is responsible for creating
     * object of WorkFlowInstance and storing the current workflow state details and histories along with CREATE transition validations.
     * @param approvalRequest
     * @throws ApprovalEngineException
     */
    private void executeCreateRequest(ApprovalRequest approvalRequest) throws ApprovalEngineException {
        if (approvalRequest.getWorkFlowTemplateId() == 0) {
            throw new ApprovalEngineException("Cannot submit this approval request! " +
                    "Please add WorkFlowTemplateId in your approval request as the actionTaken is for CREATE.");
        }

        if (!approvalWorkFlowTemplatesRepository.containsKey(approvalRequest.getWorkFlowTemplateId())) {
            throw new ApprovalEngineException("Approval request has invalid WorkFlowTemplateId!");
        }

        WorkFlowTemplate workFlowTemplate = approvalWorkFlowTemplatesRepository.get(approvalRequest.getWorkFlowTemplateId());

        WorkFlowInstance workFlowInstance = new WorkFlowInstance();
        workFlowInstance.setId(UUID.randomUUID().hashCode());
        workFlowInstance.setTemplateId(workFlowTemplate.getId());
        workFlowInstance.setCurrentLevel(0); // ApprovalRequest action CREATE marks the beginning of an approval workflow from Level 0
        workFlowInstance.setCurrentApprovalStatus(ApprovalStatus.PENDING);
        workFlowInstance.setCurrentActionTakers(workFlowTemplate.getPolicies().get(0).getAllowedActors());
        workFlowInstance.getApprovalHistories().add(new ApprovalHistory(0, approvalRequest.getActionTaker(), approvalRequest.getActionTaken(), approvalRequest.getApprovalRemark()));

        approvalWorkFlowInstancesRepository.put(workFlowInstance.getId(), workFlowInstance);
    }

    public void validateTemplate(WorkFlowTemplate workFlowTemplate) throws ApprovalEngineException {
        if (workFlowTemplate == null) {
            throw new ApprovalEngineException("ApprovalWorkFlowTemplate can't be NULL!");
        }

        if (workFlowTemplate.getTenantId() == 0) {
            throw new ApprovalEngineException("ApprovalWorkFlowTemplate TenantId can't be NULL/0!");
        }

        if (workFlowTemplate.getCategory() == null || workFlowTemplate.getCategory().isEmpty() || workFlowTemplate.getCategory().isBlank()) {
            throw new ApprovalEngineException("ApprovalWorkFlowTemplate Category can't be NULL/Empty/Blank!");
        }

        if (workFlowTemplate.getLevels() == 0) {
            throw new ApprovalEngineException("ApprovalWorkFlowTemplate Levels can't be NULL/0!");
        }

        if (workFlowTemplate.getPolicies() == null) {
            throw new ApprovalEngineException("ApprovalWorkFlowTemplate Policies can't be NULL");
        }
    }
}
