/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.Arrays;
import java.util.List;
import nl.desertspring.wicketforms.domain.*;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author sihaya
 */
public class FormSubmissionPageTest
{

    WicketTester wicketTester;
    FormSubmissionPage formSubmissionPage;
    String form1Name = "form1name";
    String form2Name = "form2name";
    Form form1;

    @Before
    public void setUp()
    {
        wicketTester = new WicketTester();

        FormRepository formRepository = mock(FormRepository.class);
        SubmissionRepository submissionRepository = mock(SubmissionRepository.class);

        form1 = mock(Form.class);
        Form form2 = mock(Form.class);

        when(form1.toString()).thenReturn(form1Name);
        when(form2.toString()).thenReturn(form2Name);

        List<Form> forms = Arrays.asList(form1, form2);

        when(formRepository.findAll()).thenReturn(forms);

        formSubmissionPage = new FormSubmissionPage(formRepository, submissionRepository);

        wicketTester.startPage(formSubmissionPage);
    }

    @Test
    public void itRendersADropDownBoxWithCurrentForms()
    {
        wicketTester.assertContains(form1Name);
        wicketTester.assertContains(form2Name);
    }

    @Test
    public void givenASelectionOfAFormItCallsCreateSubmission()
    {
        FormTester formTester = wicketTester.newFormTester("newSubmissionForm");
        
        Submission submission = mock(Submission.class);
        when(submission.getForm()).thenReturn(form1);
        when(form1.getStartPage()).thenReturn(mock(Page.class));
        
        when(form1.createSubmission()).thenReturn(submission);
        
        formTester.select("forms", 0);
        formTester.submit();
        
        verify(form1).createSubmission();
    }
}
