/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

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
}
