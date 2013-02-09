/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.List;
import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.FormRepository;
import nl.desertspring.wicketforms.domain.Page;
import nl.desertspring.wicketforms.domain.Question;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import wicketdnd.*;

/**
 *
 * @author sihaya
 */
public class QuestionPreviewPanel extends Panel
{

    public QuestionPreviewPanel(String id, final FormRepository formRepository, final IModel<Form> form, final IModel<Page> page)
    {
        super(id, page);

        final WebMarkupContainer questionListContainer = new WebMarkupContainer("questionListContainer");
        final ListView<Question> list = new ListView<Question>("questionList", new SortedSetListModel(new PropertyModel<List<Page>>(page, "questions")))
        {

            @Override
            protected ListItem<Question> newItem(int index, IModel<Question> itemModel)
            {
                ListItem<Question> item = super.newItem(index, itemModel);

                item.setOutputMarkupId(true);

                return item;
            }

            @Override
            protected void populateItem(ListItem<Question> item)
            {
                Panel component;

                switch (item.getModelObject().getType()) {
                    case CLOSED_YES_NO:
                        component = new ClosedYesNoQuestion("component", formRepository, form, item.getModel());
                        break;
                    case OPEN:
                        component = new OpenQuestion("component", formRepository, form, item.getModel());
                        break;
                    case ATTACHMENT:
                        component = new AttachmentQuestion("component", formRepository, form, item.getModel());
                        break;
                    default:
                        throw new IllegalStateException("Unknown component");
                }

                item.add(component);
            }
        };

        list.setOutputMarkupId(true);

        WebMarkupContainer questionFirst = new WebMarkupContainer("questionFirst");
        DropTarget dropTargetFirst = new DropTarget(Operation.values())
        {

            @Override
            public void onDrop(AjaxRequestTarget target, Transfer transfer, Location location) throws Reject
            {
                System.out.println("the data: " + transfer.getData());
                if (transfer.getData() == null) {
                    return;
                }

                page.getObject().createQuestion(transfer.<Question.Type>getData());

                list.modelChanged();

                target.add(questionListContainer);

                Form mergedForm = formRepository.merge(form.getObject());
                form.setObject(mergedForm);
            }

            @Override
            public String[] getTypes()
            {
                return new String[]{"question"};
            }
        };
        dropTargetFirst.dropCenter("div.drop-first-question");

        DropTarget dropTargetOthers = new DropTarget(Operation.values())
        {

            @Override
            public void onDrop(AjaxRequestTarget target, Transfer transfer, Location location) throws Reject
            {
                Question question = (Question) location.getModelObject();

                page.getObject().createQuestionAfter(question, transfer.<Question.Type>getData());

                list.modelChanged();

                target.add(questionListContainer);

                Form mergedForm = formRepository.merge(form.getObject());
                form.setObject(mergedForm);
            }

            @Override
            public String[] getTypes()
            {
                return new String[]{"question"};
            }
        };
        dropTargetOthers.dropBottom("div.drop-question");

        questionFirst.add(dropTargetFirst);
        questionListContainer.add(dropTargetOthers);

        questionListContainer.add(list);

        questionListContainer.setOutputMarkupId(true);

        add(questionFirst);
        add(questionListContainer);
    }
}
