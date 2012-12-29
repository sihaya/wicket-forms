/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.Iterator;
import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.FormRepository;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author sihaya
 */
public class FormListDataProvider implements IDataProvider<Form>
{
    @SpringBean
    private FormRepository formRepository;

    public FormListDataProvider(FormRepository formRepository)
    {
        this.formRepository = formRepository;
    }
    
    @Override
    public Iterator<? extends Form> iterator(int first, int count)
    {
        return formRepository.findAll().iterator();
    }

    @Override
    public int size()
    {
        return formRepository.findAll().size();
    }

    @Override
    public IModel<Form> model(Form object)
    {
        return Model.of(object);
    }

    @Override
    public void detach()
    {
        
    }
    
}
