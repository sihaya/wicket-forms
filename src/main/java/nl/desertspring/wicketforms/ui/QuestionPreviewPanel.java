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
                item.add(new ClosedYesNoQuestion("component", item.getModel(), 0));
            }
        };
        
        list.setOutputMarkupId(true);

        DropTarget dropTarget = new DropTarget(Operation.values())
        {

            @Override
            public void onDrop(AjaxRequestTarget target, Transfer transfer, Location location) throws Reject
            {
                Question question = (Question)location.getModelObject();
                
                page.getObject().createQuestionAfter(question);
                
                list.modelChanged();
                
                target.add(questionListContainer);
                
                Form mergedForm = formRepository.merge(form.getObject());
                form.setObject(mergedForm);
            }
        };
        dropTarget.dropTopAndBottom("div.question");
        
        questionListContainer.add(dropTarget);
        
        questionListContainer.add(list);
        add(questionListContainer);
    }
    
}
