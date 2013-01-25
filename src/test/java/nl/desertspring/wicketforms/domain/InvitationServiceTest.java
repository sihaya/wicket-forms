/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import nl.desertspring.wicketforms.domain.EmailService;
import nl.desertspring.wicketforms.domain.Invitation;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author sihaya
 */
public class InvitationServiceTest
{
    @Test
    public void givenATemplateAndInvitationItSendsAnEmail() {
        InvitationService invitationService = new InvitationService() {
            @Override
            protected String generateMessage(Invitation invitation) {
                return "content";
            }
        };
        EmailService emailService = mock(EmailService.class);                        
        InvitationRepository invitationRepository = mock(InvitationRepository.class);
        invitationService.setEmailService(emailService);
        invitationService.setInvitationRepository(invitationRepository);
        
        Invitation invitation = mock(Invitation.class);        
        invitationService.persistAndSend(invitation);
        
        verify(invitation).prepareForSend();
        verify(invitationRepository).persist(invitation);
    }
}
