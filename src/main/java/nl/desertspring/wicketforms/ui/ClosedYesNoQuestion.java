/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Answer;
import nl.desertspring.wicketforms.domain.Question;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author sihaya
 */
public class ClosedYesNoQuestion extends Panel
{
    private String value;

    public ClosedYesNoQuestion(String id, IModel<Answer> model)
    {
        super(id, model);
        
        add(new Label("text", new PropertyModel<String>(model, "question.text")));
        RadioGroup<String> answer = new RadioGroup<String>("answer", new PropertyModel<String>(model, "value"));
        
        answer.add(new Radio<String>("no", Model.of("no")));
        answer.add(new Radio<String>("yes", Model.of("yes")));
        
        add(answer);
    }

    public ClosedYesNoQuestion(String id, IModel<Question> model, int unused)
    {
        super(id, model);
        
        add(new Label("text", new PropertyModel<String>(model, "text")));
        RadioGroup<String> answer = new RadioGroup<String>("answer", new PropertyModel<String>(this, "value"));
        
        answer.add(new Radio<String>("no", Model.of("no")));
        answer.add(new Radio<String>("yes", Model.of("yes")));
        
        add(answer);
    }
    
}
