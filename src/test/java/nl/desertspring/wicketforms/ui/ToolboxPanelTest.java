/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

/**
 *
 * @author sihaya
 */
public class ToolboxPanelTest
{

    @Test
    public void itRendersAListOfItems()
    {
        WicketTester wicketTester = new WicketTester();
        
        ToolboxPanel panel = new ToolboxPanel("panel");
        
        wicketTester.startComponentInPage(panel);
        
        wicketTester.assertContains("add-page");
    }
}
