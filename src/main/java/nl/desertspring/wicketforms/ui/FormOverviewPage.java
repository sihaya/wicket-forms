/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.FormFactory;
import nl.desertspring.wicketforms.domain.FormRepository;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author sihaya
 */
public class FormOverviewPage extends BasePage
{

    @SpringBean
    private FormRepository formRepository;
    @SpringBean
    private FormFactory formFactory;
    
    private String formName;

    public FormOverviewPage(FormRepository formRepository, FormFactory formFactory)
    {
        this.formRepository = formRepository;
        this.formFactory = formFactory;

        init();
    }

    public FormOverviewPage()
    {
        init();
    }

    private void init()
    {
        add(new FormListPanel("formListPanel", new FormListDataProvider(formRepository)));

        org.apache.wicket.markup.html.form.Form<Void> newForm =
                new org.apache.wicket.markup.html.form.Form<Void>("newForm")
                {
                    @Override
                    protected void onSubmit()
                    {
                        Form form = formFactory.createForm(formName);
                        
                        formRepository.persist(form);
                    }
                };
        newForm.add(new TextField<String>("name", new PropertyModel<String>(this, "formName")));
        add(newForm);
    }

    public void setFormRepository(FormRepository formRepository)
    {
        this.formRepository = formRepository;
    }

    public void setFormFactory(FormFactory formFactory)
    {
        this.formFactory = formFactory;
    }
}
