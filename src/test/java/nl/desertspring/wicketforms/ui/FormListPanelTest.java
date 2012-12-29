/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.Arrays;
import java.util.List;
import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.FormRepository;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author sihaya
 */
public class FormListPanelTest
{

    WicketTester wicketTester;

    @Before
    public void setUp()
    {
        wicketTester = new WicketTester();

        FormRepository formRepository = mock(FormRepository.class);

        Form form1 = new Form();
        Form form2 = new Form();

        form1.setName("form 1");
        form2.setName("form 2");

        List<Form> forms = Arrays.asList(form1, form2);

        when(formRepository.findAll()).thenReturn(forms);

        FormListPanel panel = new FormListPanel("panel", new FormListDataProvider(formRepository));

        wicketTester.startComponentInPage(panel);
    }

    @Test
    public void givenAListOfFormsItRendersAllAttributes()
    {
        wicketTester.assertContains("form 1");
        wicketTester.assertContains("form 2");
    }

    @Test
    public void givenAListOfClickingARowLeadsToFormPage()
    {
        wicketTester.clickLink("panel:formList:body:rows:1:cells:1:cell:link");

        wicketTester.assertRenderedPage(FormEditPage.class);
    }
}
