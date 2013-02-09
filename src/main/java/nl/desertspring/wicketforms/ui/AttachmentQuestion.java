/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.desertspring.wicketforms.domain.Answer;
import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.FormRepository;
import nl.desertspring.wicketforms.domain.Question;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableLabel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author sihaya
 */
public class AttachmentQuestion extends Panel
{

    FileUploadField fileUploadField;
    IModel<Answer> answerModel;

    public AttachmentQuestion(String id, final IModel<Answer> answerModel)
    {
        super(id, answerModel);

        add(new Label("text", new PropertyModel<String>(answerModel, "question.text")));
        add(fileUploadField = new FileUploadField("attachment"));
        
        this.answerModel = answerModel;
    }

    public AttachmentQuestion(String id, final FormRepository formRepository, final IModel<Form> formModel, IModel<Question> questionModel)
    {
        super(id, questionModel);

        add(new AjaxEditableLabel<String>("text", new PropertyModel<String>(questionModel, "text"))
        {

            @Override
            protected void onSubmit(AjaxRequestTarget target)
            {
                super.onSubmit(target);

                formModel.setObject(formRepository.merge(formModel.getObject()));
            }
        });
        add(new WebMarkupContainer("attachment"));
    }

    public void onSubmit()
    {
        try {
            answerModel.getObject().attachFile(fileUploadField.getFileUpload().getClientFileName(), fileUploadField.getFileUpload().getBytes());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
