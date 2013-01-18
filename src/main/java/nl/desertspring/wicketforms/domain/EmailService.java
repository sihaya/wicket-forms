/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 *
 * @author sihaya
 */
@Service
public class EmailService
{

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void send(String email, String subject, String body)
    {
        SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
        msg.setTo(email);
        msg.setSubject(subject);
        msg.setText(body);
        
        mailSender.send(msg);
    }

    @Autowired
    public void setMailSender(MailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    @Autowired
    public void setTemplateMessage(SimpleMailMessage templateMessage)
    {
        this.templateMessage = templateMessage;
    }
}
