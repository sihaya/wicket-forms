/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.Arrays;
import java.util.Date;
import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.Invitation;
import nl.desertspring.wicketforms.domain.InvitationService;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.apache.wicket.util.tester.WicketTesterHelper;
import org.junit.Test;
import static org.mockito.Mockito.*;
/**
 *
 * @author sihaya
 */
public class SendInvitationPanelTest
{
    @Test
    public void givenInvitationDataItSendsInvitation() {
        WicketTester wicketTester = new WicketTester();
        
        InvitationService invitationService = mock(InvitationService.class);        
        Form form = mock(Form.class);
        Invitation invitation = mock(Invitation.class);
        
        when(form.createInvitation()).thenReturn(invitation);
                
        SendInvitationPanel sendInvitationPanel = new SendInvitationPanel("component", invitationService, Model.of(form));
        
        wicketTester.startComponentInPage(sendInvitationPanel);
        
        FormTester formTester = wicketTester.newFormTester("component:form");
        
        String emailAddress = "email@email.com";
        formTester.setValue("emailAddress", emailAddress);
        formTester.submit("send");
        
        verify(invitationService).persistAndSend(invitation);
        verify(invitation).setEmailAddress(emailAddress);
    }
    
    @Test
    public void givenAFormItRendersAListOfInvitations() {
         WicketTester wicketTester = new WicketTester();
         
         Invitation invitation1 = mock(Invitation.class);
        final String emailAddress = "test@test.lan";
         when(invitation1.getEmailAddress()).thenReturn(emailAddress);
         when(invitation1.getSentAt()).thenReturn(new Date(0));
                           
         Form form = mock(Form.class);
         when(form.getInvitations()).thenReturn(Arrays.asList(invitation1));
         
         SendInvitationPanel sendInvitationPanel = new SendInvitationPanel("component", mock(InvitationService.class), Model.of(form));
         
         wicketTester.startComponentInPage(sendInvitationPanel);
         
         wicketTester.assertContains("Email address");
         wicketTester.assertContains("Sent date");
         wicketTester.assertContains(emailAddress);
         wicketTester.assertContains("1/1/70");
    }
}
