import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.fields.config.manager.IssueTypeSchemeManager
import com.atlassian.jira.workflow.WorkflowManager
import org.apache.log4j.Level

log.setLevel(Level.DEBUG)

// Get managers from ComponentAccessor
IssueTypeSchemeManager issueTypeSchemeManager = ComponentAccessor.getIssueTypeSchemeManager()
WorkflowManager workflowManager = ComponentAccessor.getWorkflowManager()

// Define variables
def workflowName = "Simple Software Workflow"
def issueTypeName = "Story"
def IssueTypeId = "10000"

// Get all issuetype schemes
issueTypeSchemeManager.getAllSchemes()*.getAssociatedProjectObjects().each { projects ->
    
    projects.each { project ->
        
        // If the scheme contains Story issue type
        if (project.getIssueTypes()*.name.contains(issueTypeName)) {
            
            // Get the workflow for the current project/issuetype pair
            if (workflowManager.getWorkflow(project.id, IssueTypeId).getName().contains(workflowName)) {
                
                // Log the current project and its associated workflow
                log.debug(project.name + " -> " + workflowManager.getWorkflow(project.id, IssueTypeId).getName())
            }    
        }
    }
}
