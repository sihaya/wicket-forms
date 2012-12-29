/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import java.util.Date;
import org.springframework.stereotype.Service;

/**
 *
 * @author sihaya
 */
@Service
public class FormFactory
{
    public Form createForm(String name) {
        Form form = new Form();
        form.setName(name);
        form.setCreationDate(new Date());
        
        return form;
    }
}
