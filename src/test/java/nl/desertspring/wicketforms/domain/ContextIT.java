/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author sihaya
 */
public class ContextIT
{

    @Test
    public void givenAnXMLFileItLoadsTheContext()
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/nl/desertspring/wicketforms/wicket-forms.xml");
        ctx.getBean(FormRepository.class);
    }
}
