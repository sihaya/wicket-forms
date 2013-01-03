/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author sihaya
 */
@Service
public class FormService
{
    
    private FormRepository formRepository;

    public void setFormRepository(FormRepository formRepository)
    {
        this.formRepository = formRepository;
    }    
}
