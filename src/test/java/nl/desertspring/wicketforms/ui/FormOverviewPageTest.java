/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.FormFactory;
import nl.desertspring.wicketforms.domain.FormRepository;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author sihaya
 */
public class FormOverviewPageTest
{

    @Test
    public void givenANameItCreatesANewForm()
    {
        WicketTester wicketTester = new WicketTester();

        Form newForm = mock(Form.class);
        FormRepository formRepository = mock(FormRepository.class);
        FormFactory formFactory = mock(FormFactory.class);

        String formName = "name";
        when(formFactory.createForm(formName)).thenReturn(newForm);

        FormOverviewPage page = new FormOverviewPage(formRepository, formFactory);

        wicketTester.startPage(page);

        FormTester formTester = wicketTester.newFormTester("newForm");

        formTester.setValue("name", formName);
        formTester.submit();

        verify(formRepository).persist(newForm);
    }
}
