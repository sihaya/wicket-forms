/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

/**
 *
 * @author sihaya
 */
public class PageTest
{
    
    @Test
    public void givenAPageCreateQuestionReturnsNewQuestion() {
        Page page = new Page();
        Question question = mock(Question.class);
        
        Question newQuestion = page.createQuestionAfter(question);
        
        assertThat(page.getQuestions(), hasItem(newQuestion));
    }
}
