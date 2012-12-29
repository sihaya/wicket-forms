/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Form;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author sihaya
 */
public class FormEditPage extends BasePage
{

    public FormEditPage(IModel<Form> form)
    {
        add(new BookmarkablePageLink("backLink", FormOverviewPage.class));
        add(new Label("formName", new PropertyModel<String>(form, "name")));
    }
    
}
