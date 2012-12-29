/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;


/**
 *
 * @author sihaya
 */
public abstract class LinkWithLabel extends Panel
{
    public LinkWithLabel(String id, IModel<String> label) {
        super(id);
        
        add(new Link<Void>("link"){

            @Override
            public void onClick()
            {
                LinkWithLabel.this.onClick();
            }            
        }.add(new Label("label", label)));
    }
    
    public abstract void onClick();        
}
