/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 *
 * @author sihaya
 */
public class FormPreviewPanel extends Panel
{

    public FormPreviewPanel(String id, IModel<Form> form)
    {
        super(id);
    }
    
}
