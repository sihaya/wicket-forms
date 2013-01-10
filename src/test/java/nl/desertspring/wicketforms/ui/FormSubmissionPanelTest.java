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
    private Page page1;
    private FormSubmissionPanel formSubmissionPanel;

    @Before
    public void setUp()
    {
        wicketTester = new WicketTester();

        submissionRepository = mock(SubmissionRepository.class);
        submission = mock(Submission.class);

        Question question1 = mock(Question.class);

        when(question1.getText()).thenReturn(questionText);

        page1 = mock(Page.class);
        when(page1.getTitle()).thenReturn(pageTitle);
        when(page1.getQuestions()).thenReturn(Arrays.asList(question1));

        Form form = mock(Form.class);
        when(form.getStartPage()).thenReturn(page1);

        when(submission.getForm()).thenReturn(form);

        Answer answer = mock(Answer.class);
        when(submission.getAnswer(question1)).thenReturn(answer);
        when(answer.getQuestion()).thenReturn(question1);

        formSubmissionPanel = new FormSubmissionPanel("component", submissionRepository, Model.of(submission));
    }

    @Test
    public void givenASubmissionItRendersTheQuestions()
    {
        wicketTester.startComponentInPage(formSubmissionPanel);

        wicketTester.assertContains(questionText);
        wicketTester.assertContains(pageTitle);
    }

    @Test
    public void givenASubmissionClickingNextSavesForm()
    {
        wicketTester.startComponentInPage(formSubmissionPanel);

        FormTester formTester = wicketTester.newFormTester("component:form");

        when(submissionRepository.merge(submission)).thenReturn(submission);
        when(submission.getForm().getNextPage(page1)).thenReturn(page1);

        formTester.submit("next");

        verify(submissionRepository).merge(submission);
    }

    @Test
    public void givenASubmissionClickingNextLoadsNextPage()
    {
        Page page2 = mock(Page.class);
        String page2Title = "Page 2 title";
        when(page2.getTitle()).thenReturn(page2Title);
        when(submission.getForm().getNextPage(page1)).thenReturn(page2);

        pageSwitchTest("next", page2Title);
    }

    @Test
    public void givenASubmissionClickingPrevLoadsPrevPage()
    {
        Page page2 = mock(Page.class);
        String page2Title = "Page 2 title";
        when(page2.getTitle()).thenReturn(page2Title);
        when(submission.getForm().getPreviousPage(page1)).thenReturn(page2);

        pageSwitchTest("previous", page2Title);
    }

    @Test
    public void givenLastPageOnlyPrevAndSubmitAreVisible()
    {
        when(page1.isLast()).thenReturn(true);

        wicketTester.startComponentInPage(formSubmissionPanel);

        wicketTester.assertVisible("component:form:submit");
        wicketTester.assertVisible("component:form:previous");
        wicketTester.assertInvisible("component:form:next");
    }

    @Test
    public void givenFirstPageOnlyPrevAndSubmitAreVisible()
    {
        when(page1.isFirst()).thenReturn(true);

        wicketTester.startComponentInPage(formSubmissionPanel);

        wicketTester.assertInvisible("component:form:submit");
        wicketTester.assertInvisible("component:form:previous");
        wicketTester.assertVisible("component:form:next");
    }

    @Test
    public void givenLastPageClickingSubmitCallsSubmit()
    {
        when(page1.isLast()).thenReturn(true);
        when(submission.getSubmissionId()).thenReturn(32423);
        when(submissionRepository.merge(submission)).thenReturn(submission);
        wicketTester.startComponentInPage(formSubmissionPanel);
        
        FormTester formTester = wicketTester.newFormTester("component:form");
        formTester.submit("submit");
        
        verify(submission).submit();
        verify(submissionRepository).merge(submission);
                
        wicketTester.assertContains("Your form has been submitted with id.*32423");
    }

    private void pageSwitchTest(String command, String assertion)
    {
        wicketTester.startComponentInPage(formSubmissionPanel);

        FormTester formTester = wicketTester.newFormTester("component:form");

        when(submissionRepository.merge(submission)).thenReturn(submission);

        formTester.submit(command);

        wicketTester.assertContains(assertion);
    }
}
