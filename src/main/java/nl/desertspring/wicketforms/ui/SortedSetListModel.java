/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import org.apache.wicket.model.IModel;

/**
 *
 * @author sihaya
 */
public class SortedSetListModel<T> implements IModel<List<T>>
{

    private IModel<SortedSet<T>> model;

    public SortedSetListModel(IModel<SortedSet<T>> model)
    {
        this.model = model;
    }

    @Override
    public List<T> getObject()
    {
        return model.getObject() == null ? null : new ArrayList<T>(model.getObject());
    }

    @Override
    public void setObject(List<T> object)
    {
    }

    @Override
    public void detach()
    {
        model.detach();
    }
}
