/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Submission;
import nl.desertspring.wicketforms.domain.SubmissionRepository;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 *
 * @author sihaya
 */
public class FormSubmissionPanel extends Panel
{

    public FormSubmissionPanel(String id, SubmissionRepository submissionRepository, IModel<Submission> submission)
    {
        super(id, submission);
        
        
    }
    
}
