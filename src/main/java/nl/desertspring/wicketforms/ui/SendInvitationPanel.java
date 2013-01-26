/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.Invitation;
import nl.desertspring.wicketforms.domain.InvitationRepository;
import nl.desertspring.wicketforms.domain.InvitationService;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
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

    private static class InvitationDataProvider extends SortableDataProvider<Invitation>
    {

        private final IModel<Form> form;

        public InvitationDataProvider(IModel<Form> form)
        {
            this.form = form;
        }

        @Override
        public Iterator<? extends Invitation> iterator(int first, int count)
        {
            return form.getObject().getInvitations().subList(first, count).iterator();
        }

        @Override
        public int size()
        {
            return form.getObject().getInvitations().size();
        }

        @Override
        public IModel<Invitation> model(Invitation object)
        {
            return Model.of(object);
        }
    }

    private void createListPanel(IModel<Form> formModel)
    {

        IColumn<Invitation> emailAddress = new PropertyColumn<Invitation>(Model.of("Email address"), "emailAddress");
        IColumn<Invitation> sentDate = new PropertyColumn<Invitation>(Model.of("Sent date"), "sentAt");
        
        DefaultDataTable<Invitation> dataTable = new DefaultDataTable<Invitation>("invitations", Arrays.asList(emailAddress, sentDate), new InvitationDataProvider(formModel), 10);
        
        add(dataTable);
    }

    private static class InvitationModel extends LoadableDetachableModel<Invitation>
    {
        
        private IModel<Form> formModel;

        public InvitationModel(InvitationService invitationService, IModel<Form> formModel)
        {
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

        createListPanel(formModel);
    }
}
