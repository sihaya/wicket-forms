/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.FormRepository;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 *
 * @author sihaya
 */
public class FormEditorPanel extends Panel
{

    FormEditorPanel(String id, FormRepository formRepository, IModel<Form> form)
    {
        super(id, form);
        
        add(new ToolboxPanel("toolbox"));
        add(new FormPreviewPanel("preview", form, formRepository));
    }
    
}
