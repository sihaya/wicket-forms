/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

/**
 *
 * @author sihaya
 */
public class ClosedYesNoQuestionTest
{

    @Test
    public void givenAnAnswerItRendersABooleanBox()
    {
        WicketTester wicketTester = new WicketTester(new WicketFormsApplication()) {

            @Override
            protected String createPageMarkup(String componentId)
            {
                return "<html><head></head><body><form wicket:id='form'><span wicket:id='component'></span></form></body></html>";
            }            
        };
        
        org.apache.wicket.markup.html.form.Form<Void> form = new org.apache.wicket.markup.html.form.Form<Void>("form");
        
        Answer answer = new Answer();        
        ClosedYesNoQuestion closedYesNoQuestion = new ClosedYesNoQuestion("component", Model.of(answer));
        
        form.add(closedYesNoQuestion);
        
        wicketTester.startComponentInPage(form);
        
        wicketTester.assertContains("Yes");
        wicketTester.assertContains("No");
        
        FormTester formTester = wicketTester.newFormTester("form");
        formTester.select("component:question", 1);
    }
}
