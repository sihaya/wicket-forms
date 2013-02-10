/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.grunt.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import nl.desertspring.grunt.data.*;
import nl.desertspring.wicketforms.domain.*;
import org.junit.Before;
import static org.mockito.Mockito.*;
import org.junit.Test;

/**
 *
 * @author sihaya
 */
public class SubmissionProcessorTest
{
    DMSGateway dmsGateway;
    SubmissionProcessor submissionProcessor;
    Submission submission;
    byte[] fileContent;
    MonumentRepository monumentRepository;
    Monument monument;
    
    Grant grant;
    GrantRepository grantRepository;
    GrantFactory grantFactory;

    @Before
    public void setUp()
    {
        dmsGateway = mock(DMSGateway.class);
        MonumentFactory monumentFactory = mock(MonumentFactory.class);
        monumentRepository = mock(MonumentRepository.class);

        submissionProcessor = new SubmissionProcessor();
        submissionProcessor.setMonumentFactory(monumentFactory);
        submissionProcessor.setMonumentRepository(monumentRepository);
        submissionProcessor.setDMSGateway(dmsGateway);

        submission = mock(Submission.class);

        when(submission.getQuestionValue(1, 0)).thenReturn("monumentname");
        when(submission.getQuestionValue(1, 1)).thenReturn("monumentstreet");
        when(submission.getQuestionValue(1, 2)).thenReturn("monumentcity");

        monument = mock(Monument.class);

        when(monumentFactory.createMonument("monumentname", "monumentstreet", "monumentcity")).thenReturn(monument);

        Answer answer = mock(Answer.class);
        fileContent = new byte[0];
        when(answer.getFileContent()).thenReturn(fileContent);
        when(answer.getFilename()).thenReturn("filename.txt");

        Question question = mock(Question.class);
        when(question.getType()).thenReturn(Question.Type.ATTACHMENT);
        when(answer.getQuestion()).thenReturn(question);
        when(submission.getAnswers()).thenReturn(Arrays.asList(answer));
        when(submission.getSubmissionId()).thenReturn(100);
        
        grant = mock(Grant.class);
        
        grantRepository = mock(GrantRepository.class);
        grantFactory = mock(GrantFactory.class);
        
        when(grantFactory.createGrant(new BigDecimal("1000"))).thenReturn(grant);
        
        submissionProcessor.setGrantRepository(grantRepository);
        submissionProcessor.setGrantFactory(grantFactory);
        
        when(submission.getQuestionValue(0, 0)).thenReturn("1000");
    }

    @Test
    public void givenASubmissionProcessFormUploadsDocumentsIntoDMS()
    {
        submissionProcessor.process(submission);

        verify(dmsGateway).createFolder("100");
        verify(dmsGateway).attachFile("100", "filename.txt", fileContent);
    }

    @Test
    public void givenASubmissionItCreatesAMonumentBasedOnQuestions()
    {
        submissionProcessor.process(submission);

        verify(monumentRepository).persist(monument);
    }
    
    @Test
    public void givenASubmissionItCreateAGrantBasedOnQuestions() {
        submissionProcessor.process(submission);
        
        verify(grantRepository).persist(grant);
    }
}
