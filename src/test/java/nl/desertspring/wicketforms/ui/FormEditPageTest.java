/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

/**
 *
 * @author sihaya
 */
public class FormEditPageTest
{

    @Test
    public void givenAFormItRendersTheName()
    {
        WicketTester wicketTester = new WicketTester();

        Form form = new Form();
        form.setName("form name");

        wicketTester.startPage(new FormEditPage(Model.of(form)));

        wicketTester.assertContains("form name");
    }    
}
