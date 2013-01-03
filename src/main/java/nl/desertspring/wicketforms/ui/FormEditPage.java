/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.FormRepository;
import nl.desertspring.wicketforms.domain.Page;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.internal.HtmlHeaderContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.CssPackageResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import wicketdnd.theme.WebTheme;

/**
 *
 * @author sihaya
 */
public class FormEditPage extends BasePage
{
    
    @SpringBean
    private FormRepository formRepository;

    @Override
    public void renderHead(IHeaderResponse response)
    {
        response.renderCSSReference(new WebTheme());
    }
    
    public FormEditPage(IModel<Form> form)
    {           
        
        add(new BookmarkablePageLink("backLink", FormOverviewPage.class));
        add(new Label("formName", new PropertyModel<String>(form, "name")));
        add(new ToolboxPanel("toolbox"));
        add(new FormPreviewPanel("preview", form, formRepository));
    }
    
}
