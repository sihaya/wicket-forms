/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.Arrays;
import java.util.List;
import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.FormRepository;
import nl.desertspring.wicketforms.domain.Page;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import static org.mockito.Mockito.*;
/**
 *
 * @author sihaya
 */
public class FormEditorPanelTest
{

    @Test
    public void givenAFormItRendersTheName()
    {
        WicketTester wicketTester = new WicketTester();

        Form form = mock(Form.class);
        Page page = mock(Page.class);
        
        when(page.getTitle()).thenReturn("form name");        
        when(form.getPages()).thenReturn(Arrays.asList(page));
        
        FormRepository formRepository = mock(FormRepository.class);

        wicketTester.startComponentInPage(new FormEditorPanel("component", formRepository, Model.of(form)));

        wicketTester.assertContains("form name");
    }    
}
