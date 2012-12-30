/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import wicketdnd.DragSource;
import wicketdnd.Operation;

/**
 *
 * @author sihaya
 */
public class ToolboxPanel extends Panel
{

    public ToolboxPanel(String id)
    {
        super(id);
        
        WebMarkupContainer toolboxContainer = new WebMarkupContainer("toolboxContainer");
                
        WebMarkupContainer addPage = new WebMarkupContainer("addPage", Model.of("addPage"));
        addPage.setOutputMarkupId(true);
        
        DragSource dragSource = new DragSource(Operation.values());            
        dragSource.drag("div").initiate("div");
        
        toolboxContainer.add(dragSource);        
        
        toolboxContainer.add(addPage);
        
        add(toolboxContainer);
    }
}
