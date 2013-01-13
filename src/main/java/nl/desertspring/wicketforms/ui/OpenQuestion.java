/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Answer;
import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.FormRepository;
import nl.desertspring.wicketforms.domain.Question;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableLabel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author sihaya
 */
public class OpenQuestion extends Panel
{

    public OpenQuestion(String id, IModel<Answer> answerModel)
    {
        super(id, answerModel);
        
        add(new Label("text", new PropertyModel<String>(answerModel, "question.text")));
        add(new RequiredTextField("answer", new PropertyModel<String>(answerModel, "value")));
    }

    public OpenQuestion(String id, final FormRepository formRepository, final IModel<Form> formModel, IModel<Question> questionModel)
    {
        super(id, questionModel);
        
        add(new AjaxEditableLabel<String>("text", new PropertyModel<String>(questionModel, "text")) {

            @Override
            protected void onSubmit(AjaxRequestTarget target)
            {
                super.onSubmit(target);
                
                formModel.setObject(formRepository.merge(formModel.getObject()));
            }            
        });
        add(new WebMarkupContainer("answer"));
    }
    
}
