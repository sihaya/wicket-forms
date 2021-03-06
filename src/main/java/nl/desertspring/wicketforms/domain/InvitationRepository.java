/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sihaya
 */
@Repository
@Transactional
public class InvitationRepository
{
    private EntityManager entityManager;

    @PersistenceContext(unitName = "wicketforms")
    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
    
    public void persist(Invitation invitation) {
        entityManager.persist(invitation);
        
        invitation.getForm().getInvitations().add(invitation);
    }
    
    public Invitation findBySecret(String secret) {
        return entityManager.createQuery("from Invitation i where i.secret = :secret", Invitation.class).setParameter("secret", secret).getSingleResult();
    }

    public Invitation merge(Invitation invitation)
    {
        return entityManager.merge(invitation);
    }
}
