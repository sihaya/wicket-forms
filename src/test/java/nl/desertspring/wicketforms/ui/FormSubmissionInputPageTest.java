/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.Page;
import nl.desertspring.wicketforms.domain.Submission;
import nl.desertspring.wicketforms.domain.SubmissionRepository;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author sihaya
 */
public class FormSubmissionInputPageTest
{
    @Test
    public void givenASubmissionItRendersSubmissionPanel() {
        WicketTester wicketTester = new WicketTester();
        
        SubmissionRepository submissionRepository = mock(SubmissionRepository.class);
        Submission submission = mock(Submission.class);
        when(submission.getForm()).thenReturn(mock(Form.class));
        when(submission.getForm().getStartPage()).thenReturn(mock(Page.class));
                
        FormSubmissionInputPage page = new FormSubmissionInputPage(submissionRepository, Model.of(submission));
        
        wicketTester.startPage(page);
        
        wicketTester.assertComponent("submissionPanel", FormSubmissionPanel.class);
    }
    
}
