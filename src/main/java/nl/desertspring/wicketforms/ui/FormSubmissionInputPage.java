/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.grunt.domain.SubmissionProcessor;
import nl.desertspring.wicketforms.domain.Submission;
import nl.desertspring.wicketforms.domain.SubmissionRepository;
import org.apache.wicket.model.IModel;

/**
 *
 * @author sihaya
 */
public class FormSubmissionInputPage extends BasePage
{

    public FormSubmissionInputPage(SubmissionRepository submissionRepository, SubmissionProcessor submissionProcessor, IModel<Submission> submission)
    {
        add(new FormSubmissionPanel("submissionPanel", submissionRepository, submissionProcessor, submission));
    }
    
}
