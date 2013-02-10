/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.grunt.domain;

import java.math.BigDecimal;
import nl.desertspring.grunt.data.*;
import nl.desertspring.wicketforms.domain.Answer;
import nl.desertspring.wicketforms.domain.Question;
import nl.desertspring.wicketforms.domain.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sihaya
 */
@Service
public class SubmissionProcessor
{

    private DMSGateway dmsGateway;
    private MonumentRepository monumentRepository;
    private MonumentFactory monumentFactory;
    private GrantRepository grantRepository;
    private GrantFactory grantFactory;

    public void process(Submission submission)
    {
        processDms(submission);
        processMonument(submission);
        processGrant(submission);
    }

    @Autowired
    public void setDMSGateway(DMSGateway dmsGateway)
    {
        this.dmsGateway = dmsGateway;
    }

    @Autowired
    public void setMonumentFactory(MonumentFactory monumentFactory)
    {
        this.monumentFactory = monumentFactory;
    }

    @Autowired
    public void setMonumentRepository(MonumentRepository monumentRepository)
    {
        this.monumentRepository = monumentRepository;
    }

    @Autowired
    public void setGrantFactory(GrantFactory grantFactory)
    {
        this.grantFactory = grantFactory;
    }

    @Autowired
    public void setGrantRepository(GrantRepository grantRepository)
    {
        this.grantRepository = grantRepository;
    }

    private void processDms(Submission submission)
    {
        String foldername = Integer.toString(submission.getSubmissionId());

        dmsGateway.createFolder(foldername);

        for (Answer answer : submission.getAnswers()) {
            if (answer.getQuestion().getType() != Question.Type.ATTACHMENT) {
                continue;
            }

            dmsGateway.attachFile(foldername, answer.getFilename(), answer.getFileContent());
        }
    }

    private void processMonument(Submission submission)
    {
        Monument monument = monumentFactory.createMonument(submission.getQuestionValue(1, 0), submission.getQuestionValue(1, 1), submission.getQuestionValue(1, 2));

        monumentRepository.persist(monument);
    }

    private void processGrant(Submission submission)
    {
        Grant grant = grantFactory.createGrant(new BigDecimal(submission.getQuestionValue(0, 0)));
     
        grantRepository.persist(grant);
    }
}
