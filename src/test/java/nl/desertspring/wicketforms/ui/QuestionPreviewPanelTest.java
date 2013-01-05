/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.Arrays;
import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.FormRepository;
import nl.desertspring.wicketforms.domain.Page;
import nl.desertspring.wicketforms.domain.Question;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author sihaya
 */
public class QuestionPreviewPanelTest
{
    @Test
    public void givenAPageWithQuestionsItRendersTheTexts() {
        WicketTester wicketTester = new WicketTester();
        
        Question question = mock(Question.class);
        Page page = mock(Page.class);
        when(page.getQuestions()).thenReturn(Arrays.asList(question));
        
        QuestionPreviewPanel questionPreviewPanel = new QuestionPreviewPanel("component", mock(FormRepository.class), Model.of(mock(Form.class)), Model.of(page));
        
        wicketTester.startComponentInPage(questionPreviewPanel);
                
        wicketTester.assertComponent("component:questionListContainer:questionList:0:component", ClosedYesNoQuestion.class);
    }
}
