/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.List;
import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.FormRepository;
import org.apache.wicket.model.AbstractReadOnlyModel;

/**
 *
 * @author sihaya
 */
public class FormListModel extends AbstractReadOnlyModel<List<Form>>
{
    private FormRepository formRepository;

    FormListModel(FormRepository formRepository)
    {
        this.formRepository = formRepository;
    }

    @Override
    public List<Form> getObject()
    {
        return formRepository.findAll();
    }
    
}
