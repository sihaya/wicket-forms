/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 *
 * @author sihaya
 */
public class WicketFormsApplication extends WebApplication
{

    @Override
    public Class<? extends Page> getHomePage()
    {
        return HomePage.class;
    }

    @Override
    protected void init()
    {
        super.init();
        
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        
        mountPage("/invitation", SubmissionFromInvitationPage.class);
    }
}
