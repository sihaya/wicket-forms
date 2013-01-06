/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.Arrays;
import nl.desertspring.wicketforms.domain.*;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author sihaya
 */
public class FormSubmissionPanelTest
{

    private WicketTester wicketTester;
    private SubmissionRepository submissionRepository;
    private Submission submission;
    private final String questionText = "question text for question 1";
    private final String pageTitle = "Page title";

    @Before
    public void setUp()
    {
        wicketTester = new WicketTester();

        submissionRepository = mock(SubmissionRepository.class);
        submission = mock(Submission.class);

        Question question1 = mock(Question.class);

        when(question1.getText()).thenReturn(questionText);

        Page page1 = mock(Page.class);        
        when(page1.getTitle()).thenReturn(pageTitle);
        when(page1.getQuestions()).thenReturn(Arrays.asList(question1));

        Form form = mock(Form.class);
        when(form.getStartPage()).thenReturn(page1);

        when(submission.getForm()).thenReturn(form);

        Answer answer = mock(Answer.class);
        when(submission.getAnswer(question1)).thenReturn(answer);
        when(answer.getQuestion()).thenReturn(question1);

        FormSubmissionPanel formSubmissionPanel = new FormSubmissionPanel("component", submissionRepository, Model.of(submission));
        wicketTester.startComponentInPage(formSubmissionPanel);
    }

    @Test
    public void givenASubmissionItRendersTheQuestions()
    {
        wicketTester.assertContains(questionText);
        wicketTester.assertContains(pageTitle);
    }

    @Test
    public void givenASubmissionClickingNextSavesForm()
    {
        FormTester formTester = wicketTester.newFormTester("component:form");
        
        when(submissionRepository.merge(submission)).thenReturn(submission);
        
        formTester.submit("next");
        
        verify(submissionRepository).merge(submission);
    }
}
