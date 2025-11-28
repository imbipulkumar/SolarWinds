package org.solarwinds.ApprovalWorkFlowEngine.Exception;

public class ApprovalEngineException extends Exception{
    public ApprovalEngineException (String msg) {
        super(msg);

        System.out.println(msg);
    }
}
