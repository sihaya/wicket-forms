/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import java.util.List;
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
public class FormRepository
{    
    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    public void persist(Form form)
    {
        entityManager.persist(form);
    }

    public List<Form> findAll()
    {
        return entityManager.createQuery("from Form f order by f.creationDate", Form.class).getResultList();
    }    
    
    public Form getById(int formId) {
        return entityManager.find(Form.class, formId);
    }
}
