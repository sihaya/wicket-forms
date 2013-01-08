/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.Arrays;
import nl.desertspring.wicketforms.domain.Form;
import org.apache.wicket.extensions.markup.html.repeater.data.table.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

/**
 *
 * @author sihaya
 */
public class FormListPanel extends Panel
{

    public FormListPanel(String id, IDataProvider<Form> model)
    {
        super(id);

        IColumn nameColumn = new PropertyColumn(Model.of(getLocalizer().getString("label.name", this)), "name")
        {

            @Override
            public void populateItem(Item item, String componentId, final IModel rowModel)
            {
                item.add(new LinkWithLabel(componentId, createLabelModel(rowModel))
                {

                    @Override
                    public void onClick()
                    {
                        setResponsePage(new FormEditPage(rowModel));
                    }
                });
            }
        };
        IColumn creationDateColumn = new PropertyColumn(Model.of(getLocalizer().getString("label.creationDate", this)), "creationDate");

        DataTable dataTable = new DataTable("formList", Arrays.asList(nameColumn, creationDateColumn), model, 999999);
        dataTable.addTopToolbar(new HeadersToolbar(dataTable, null));
        dataTable.addBottomToolbar(new NoRecordsToolbar(dataTable));
        
        add(dataTable);
    }
}
