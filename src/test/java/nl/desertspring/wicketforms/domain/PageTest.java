/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import java.util.Arrays;
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
    
    @Test
    public void givenAPageThatIsLastInTheListOfFormsIsLastReturnsTrue() {
        Page page1 = new Page();
        Page page2 = new Page();
        
        Form form = new Form();
        form.setPages(Arrays.asList(page1, page2));
        
        page1.setForm(form);
        page2.setForm(form);
        
        assertTrue(page2.isLast());
        assertTrue(page1.isFirst());
        assertTrue(!page2.isFirst());
        assertTrue(!page1.isLast());
    }
}
