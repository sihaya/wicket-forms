/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.List;
import nl.desertspring.grunt.domain.SubmissionProcessor;
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
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

/**
 *
 * @author sihaya
 */
public class FormSubmissionPanel extends Panel
{

    private IModel<Page> currentPage;
    private IModel<Submission> submission;

    public FormSubmissionPanel(String id, final SubmissionRepository submissionRepository, final SubmissionProcessor submissionProcessor, final IModel<Submission> submission)
    {
        super(id, submission);

        this.currentPage = new Model();
        this.submission = submission;

        final Form<Void> form = new Form<Void>("form");

        form.add(new Button("submit")
        {

            @Override
            public void onSubmit()
            {
                form.visitChildren(AttachmentQuestion.class, new IVisitor<AttachmentQuestion, Void>()
                {

                    @Override
                    public void component(AttachmentQuestion t, IVisit<Void> ivisit)
                    {
                        t.onSubmit();
                    }
                });

                submission.getObject().submit();
                submission.setObject(submissionRepository.merge(submission.getObject()));
                
                submissionProcessor.process(submission.getObject());

                setResponsePage(new SubmissionDonePage(submission));
            }

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
                form.visitChildren(AttachmentQuestion.class, new IVisitor<AttachmentQuestion, Void>()
                {

                    @Override
                    public void component(AttachmentQuestion t, IVisit<Void> ivisit)
                    {
                        t.onSubmit();
                    }
                });

                submission.setObject(submissionRepository.merge(submission.getObject()));

                currentPage.setObject(submission.getObject().getForm().getNextPage(currentPage.getObject()));
            }

            @Override
            protected void onConfigure()
            {
                super.onConfigure();

                setVisible(!currentPage.getObject().isLast());
            }
        });

        form.add(new Button("previous")
        {

            @Override
            public void onSubmit()
            {
                currentPage.setObject(submission.getObject().getForm().getPreviousPage(currentPage.getObject()));
            }

            @Override
            protected void onConfigure()
            {
                super.onConfigure();

                setVisible(!currentPage.getObject().isFirst());
            }
        }.setDefaultFormProcessing(true));

        ListView<Question> questionList = new ListView<Question>("questionList", new PropertyModel<List<Question>>(currentPage, "questions"))
        {

            @Override
            protected void populateItem(ListItem<Question> item)
            {
                Panel component;

                AnswerModel model = new AnswerModel(submission, item.getModelObject());

                switch (item.getModelObject().getType()) {
                    case CLOSED_YES_NO:
                        component = new ClosedYesNoQuestion("question", model);
                        break;
                    case OPEN:
                        component = new OpenQuestion("question", model);
                        break;
                    case ATTACHMENT:
                        component = new AttachmentQuestion("question", model);
                        break;
                    default:
                        throw new IllegalStateException("Unknown component");
                }

                item.add(component);
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
