/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author sihaya
 */
@Ignore
public class InvitationIT
{
    @Test
    public void givenAnInvitationItSendsAnEmail() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/nl/desertspring/wicketforms/wicket-forms.xml");
        EmailService emailService = ctx.getBean(EmailService.class);
        FormRepository formRepository = ctx.getBean(FormRepository.class);
        FormFactory formFactory = ctx.getBean(FormFactory.class);
        
        
        Form form = formFactory.createForm("blahh");
        formRepository.persist(form);
        
        Invitation invitation = form.createInvitation();
        invitation.setEmailAddress("test@desert-spring.nl");
        invitation.setMessage("the message");
        
        invitation.send("http://someurl", emailService);
        
        formRepository.merge(form);
        
        System.out.println("the id: " + invitation.getInvitationId());
    }
}
