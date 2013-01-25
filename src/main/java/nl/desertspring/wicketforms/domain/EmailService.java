/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author sihaya
 */
@Service
public class EmailService
{
    
    private JavaMailSender mailSender;
    private SimpleMailMessage templateMessage;
    
    public void send(String email, String subject, String body)
    {
        
        MimeMessage msg = mailSender.createMimeMessage();
        
        try {
            MimeMailMessage message = new MimeMailMessage(new MimeMessageHelper(msg, true));
            message.setFrom("test@desert-spring.nl");
            message.setTo(email);
            message.setSubject(subject);       
            
            message.getMimeMessageHelper().setText(body, body);
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        
        mailSender.send(msg);
    }
    
    @Autowired
    public void setMailSender(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }
}
