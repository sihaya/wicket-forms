/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.Arrays;
import nl.desertspring.wicketforms.domain.*;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import static org.mockito.Mockito.*;
/**
 *
 * @author sihaya
 */
public class FormSubmissionPanelTest
{

    @Test
    public void givenASubmissionItRendersTheQuestions()
    {
        WicketTester wicketTester = new WicketTester();
        
        SubmissionRepository submissionRepository = mock(SubmissionRepository.class);
        Submission submission = mock(Submission.class);
        
        Question question1 = mock(Question.class);
        final String questionText = "question text for question 1";
        when(question1.getText()).thenReturn(questionText);
        
        Page page1 = mock(Page.class);
        when(page1.getQuestions()).thenReturn(Arrays.asList(question1));
        
        Form form = mock(Form.class);
        when(form.getPages()).thenReturn(Arrays.asList(page1));
                
        when(submission.getForm()).thenReturn(form);
        
        FormSubmissionPanel formSubmissionPanel = new FormSubmissionPanel("component", submissionRepository, Model.of(submission));
        wicketTester.startComponentInPage(formSubmissionPanel);
        
        wicketTester.assertContains(questionText);        
    }
}
