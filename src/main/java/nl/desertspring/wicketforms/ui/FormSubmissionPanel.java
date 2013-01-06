/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.List;
import nl.desertspring.wicketforms.domain.Page;
import nl.desertspring.wicketforms.domain.Question;
import nl.desertspring.wicketforms.domain.Submission;
import nl.desertspring.wicketforms.domain.SubmissionRepository;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author sihaya
 */
public class FormSubmissionPanel extends Panel
{

    private IModel<Page> currentPage;
    private IModel<Submission> submission;

    public FormSubmissionPanel(String id, final SubmissionRepository submissionRepository, final IModel<Submission> submission)
    {
        super(id, submission);
        
        this.currentPage = new Model();
        this.submission = submission;

        final Form<Void> form = new Form<Void>("form");

        form.add(new Button("submit")
        {

            @Override
            protected void onConfigure()
            {
                super.onConfigure();

                setVisible(currentPage.getObject().isLast());
            }
        });

        form.add(new Button("next")
        {

            @Override
            public void onSubmit()
            {
                submission.setObject(submissionRepository.merge(submission.getObject()));
            }
        });
        
        form.add(new Button("previous") {
            
        });
        
        ListView<Question> questionList = new ListView<Question>("questionList", new PropertyModel<List<Question>>(currentPage, "questions")) {

            @Override
            protected void populateItem(ListItem<Question> item)
            {
                item.add(new ClosedYesNoQuestion("question", new AnswerModel(submission, item.getModelObject())));
            }
        };
        
        form.add(new Label("pageTitle", new PropertyModel<String>(currentPage, "title")));
        form.add(questionList);

        add(form);
    }

    @Override
    protected void onInitialize()
    {
        super.onInitialize();

        this.currentPage.setObject(submission.getObject().getForm().getStartPage());
    }
}
