/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.FormRepository;
import nl.desertspring.wicketforms.domain.InvitationService;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import wicketdnd.theme.WebTheme;

/**
 *
 * @author sihaya
 */
public class FormEditPage extends BasePage
{

    private static final String ACTIVE_PANEL = "activePanel";
    @SpringBean
    private FormRepository formRepository;
    @SpringBean
    private InvitationService invitationService;
    private Component activePanel;

    @Override
    public void renderHead(IHeaderResponse response)
    {
        response.renderCSSReference(new WebTheme());
    }

    public FormEditPage(final IModel<Form> form)
    {
        add(new BookmarkablePageLink("backLink", FormOverviewPage.class));
        add(new Label("formName", new PropertyModel<String>(form, "name")));

        final IModel<String> formEditorLinkModel = new Model<String>("active");
        final IModel<String> invitationsLinkModel = new Model<String>("");

        activePanel = new FormEditorPanel(ACTIVE_PANEL, formRepository, form);
                
        final WebMarkupContainer formEditorLinkContainer = new WebMarkupContainer("formEditorLinkContainer");
        formEditorLinkContainer.add(new AttributeAppender("class", formEditorLinkModel));
        formEditorLinkContainer.add(new Link<Void>("formEditorLink")
        {

            @Override
            public void onClick()
            {
                final FormEditorPanel formEditorPanel = new FormEditorPanel(ACTIVE_PANEL, formRepository, form);
                activePanel.replaceWith(formEditorPanel);
                formEditorLinkModel.setObject("active");
                invitationsLinkModel.setObject("");
                activePanel = formEditorPanel;
            }
        });
        add(formEditorLinkContainer);

        final WebMarkupContainer invitationsLinkContainer = new WebMarkupContainer("invitationsLinkContainer");
        invitationsLinkContainer.add(new AttributeAppender("class", invitationsLinkModel));
        invitationsLinkContainer.add(new Link<Void>("invitationsLink")
        {

            @Override
            public void onClick()
            {
                final SendInvitationPanel sendInvitationPanel = new SendInvitationPanel(ACTIVE_PANEL, invitationService, form);
                activePanel.replaceWith(sendInvitationPanel);
                invitationsLinkModel.setObject("active");
                formEditorLinkModel.setObject("");
                activePanel = sendInvitationPanel;
            }
        });
        add(invitationsLinkContainer);

        add(activePanel);
    }
}
