/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
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
        form.setInvitations(new ArrayList<Invitation>());
        
        Page page = new Page();
        page.setForm(form);
        page.setTitle("Page 1");
        page.setPosition(0);
                
        form.setPages(Arrays.asList(page));
        
        return form;
    }
}
