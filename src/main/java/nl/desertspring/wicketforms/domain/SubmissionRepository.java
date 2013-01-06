/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sihaya
 */
@Repository
@Transactional
public class SubmissionRepository
{

    private EntityManager entityManager;

    public Submission merge(Submission submission)
    {
        Submission mergedSubmission = entityManager.merge(submission);
        mergedSubmission.getAnswers().size();
        
        return mergedSubmission;
    }

    public void save(Submission submission)
    {
        entityManager.persist(submission);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
}
