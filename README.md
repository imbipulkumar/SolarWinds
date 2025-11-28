> **Note**: Due to time constraints, the driver code (test scenarios) and certain TODO implementations (APPROVE/REJECT/REVISE actions) are not completed. The core architecture and CREATE workflow functionality are fully implemented.
### Core Components

#### Domain Models

- **ApprovalRequest**: Represents a request for approval with metadata (requester, category, tenantId, amount)
- **WorkFlowTemplate**: Defines reusable workflow configurations per tenant and category
- **WorkFlowInstance**: Active workflow execution instance tracking current state
- **Actor**: Represents users with roles (Manager, Finance, Legal)
- **Policy**: Defines rules for each workflow level (allowed actors, actions, amount limits)

#### State Management

The system manages workflow states through the following transitions:
```
PENDING → IN_REVIEW → APPROVED/REJECTED
```

#### Workflow Engine

The `ApprovalWorkFlowEngine` class serves as the core orchestrator handling:
- Template validation and registration
- Request submission and processing
- State transitions and validations
- Access control enforcement
