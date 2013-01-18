/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

/**
 *
 * @author sihaya
 */
public class InvitationTest
{

    @Test
    public void givenAnInvitationItSendsOutEmail()
    {
        EmailService emailService = mock(EmailService.class);

        Invitation invitation = new Invitation();

        String email = "someone@test.lan";
        invitation.setEmailAddress(email);
        invitation.setMessage("Hello there");

        invitation.send("http://www.wicketforms.com/submit", emailService);

        verify(emailService).send(eq(email), anyString(), anyString());
        
        assertNotNull(invitation.getSecret());
    }
}
