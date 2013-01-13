/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Answer;
import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.FormRepository;
import nl.desertspring.wicketforms.domain.Question;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
/**
 *
 * @author sihaya
 */
public class OpenQuestionTest
{
    @Test
    public void givenAnAnswerItRendersATextBox() {
        WicketTester wicketTester = new WicketTester() {

            @Override
            protected String createPageMarkup(String componentId)
            {
                return "<html><head></head><body><form wicket:id='form'><span wicket:id='component'></span></form></body></html>";
            }            
        };
        
        org.apache.wicket.markup.html.form.Form<Void> form = new org.apache.wicket.markup.html.form.Form<Void>("form");
        
        Answer answer = new Answer();        
        Question question = new Question();
        
        answer.setQuestion(question);
        
        String questionText = "What do you want to know?";
        question.setText(questionText);
        
        OpenQuestion openQuestion = new OpenQuestion("component", Model.of(answer));
        
        form.add(openQuestion);
        
        wicketTester.startComponentInPage(form);
        
        FormTester formTester = wicketTester.newFormTester("form");
        String answerText = "the answer";
        formTester.setValue("component:answer", answerText);
        formTester.submit();
        
        wicketTester.assertContains(questionText);
        assertEquals(answerText, answer.getValue());
    }
    
    public void givenAQuestionItRendersAEditableSpan() {
        Question question = new Question();
        question.setText("the questiontext");
        
        new OpenQuestion("component", mock(FormRepository.class), Model.of(mock(Form.class)), Model.of(question));
    }
}
