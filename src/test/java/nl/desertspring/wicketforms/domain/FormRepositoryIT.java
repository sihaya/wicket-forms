/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 *
 * @author sihaya
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/nl/desertspring/wicketforms/wicket-forms.xml")
public class FormRepositoryIT
{

    @Autowired
    private FormRepository formRepository;

    @Test
    public void givenExistingFormsItReturnsTheListOfFormsInOrder()
    {
        Form form = new Form();
        form.setName("The form 1");
        form.setName("The form 2");
        
        formRepository.persist(form);
        
        List<Form> forms = formRepository.findAll();
        
        assertEquals(1, forms.size());
        
    }
}
