/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.matchers.JUnitMatchers.*;
/**
 *
 * @author sihaya
 */
public class SubmissionTest
{
    Submission submission;
    
    @Before
    public void setUp() {
        submission = new Submission();
        submission.setAnswers(new ArrayList<Answer>());
    }
    
    @Test
    public void givenAQuestionGetAnswerReturnsAnswer() {        
        Question question = mock(Question.class);
        
        Answer answer = submission.getAnswer(question);
        
        assertEquals(question, answer.getQuestion());
        assertThat(submission.getAnswers(), hasItem(answer));
    }
    
    @Test
    public void givenAQuestionGetAnswerReturnsTheSameAnswer() {
        Question question = mock(Question.class);
        
        Answer answer1 = submission.getAnswer(question);
        Answer answer2 = submission.getAnswer(question);
        
        assertEquals(answer1, answer2);
    }
}
