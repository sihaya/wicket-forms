/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.Invitation;
import nl.desertspring.wicketforms.domain.InvitationService;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author sihaya
 */
public class SendInvitationPanel extends Panel
{

    private static class InvitationModel extends LoadableDetachableModel<Invitation>
    {

        private InvitationService invitationService;
        private IModel<Form> formModel;

        public InvitationModel(InvitationService invitationService, IModel<Form> formModel)
        {
            this.invitationService = invitationService;
            this.formModel = formModel;
        }

        @Override
        protected Invitation load()
        {
            return formModel.getObject().createInvitation();
        }
    }

    public SendInvitationPanel(String id, final InvitationService invitationService, IModel<Form> formModel)
    {
        super(id, formModel);

        final IModel<Invitation> invitation = new InvitationModel(invitationService, formModel);

        org.apache.wicket.markup.html.form.Form<Void> form = new org.apache.wicket.markup.html.form.Form("form")
        {

            @Override
            protected void onSubmit()
            {
                invitationService.persistAndSend(invitation.getObject());                
                invitation.setObject(null);
            }
            
        };
        
        form.add(new EmailTextField("emailAddress", new PropertyModel<String>(invitation, "emailAddress")));
        form.add(new Button("send"));
        add(form);
    }
}
