/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.List;
import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.Page;
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
public class FormPreviewPanel extends Panel
{

    public FormPreviewPanel(String id, final IModel<Form> form)
    {
        super(id);

        final WebMarkupContainer pageListContainer = new WebMarkupContainer("pageListContainer");
        final ListView<Page> list = new ListView<Page>("pageList", new SortedSetListModel(new PropertyModel<List<Page>>(form, "pages")))
        {

            @Override
            protected ListItem<Page> newItem(int index, IModel<Page> itemModel)
            {
                ListItem<Page> item = super.newItem(index, itemModel);
                
                item.setOutputMarkupId(true);
                
                return item;
            }
            

            @Override
            protected void populateItem(ListItem item)
            {                
                item.add(new Label("title", new PropertyModel<String>(item.getModel(), "title")));
            }
        };
        
        list.setOutputMarkupId(true);

        DropTarget dropTarget = new DropTarget(Operation.values())
        {

            @Override
            public void onDrop(AjaxRequestTarget target, Transfer transfer, Location location) throws Reject
            {
                System.out.println("Gonna do drop");
                
                Page page = (Page)location.getModelObject();
                
                form.getObject().addPageAfter(page);
                
                list.modelChanged();
                
                target.add(pageListContainer);                
            }
        };
        dropTarget.dropTopAndBottom("div.page");
        
        pageListContainer.add(dropTarget);
        
        pageListContainer.add(list);
        add(pageListContainer);
    }
}
