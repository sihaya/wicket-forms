/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.Page;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import static org.mockito.Mockito.*;
import wicketdnd.DropTarget;

/**
 *
 * @author sihaya
 */
public class FormPreviewPanelTest
{

    @Test
    public void givenAFormWithPagesItRendersAPreview()
    {
        WicketTester wicketTester = new WicketTester();

        Page page1 = new Page();
        page1.setTitle("This is page 1");

        Page page2 = new Page();
        page2.setTitle("This being page 2");

        Form form = mock(Form.class);
        when(form.getPages()).thenReturn(new LinkedHashSet<Page>(Arrays.asList(page1, page2)));

        FormPreviewPanel previewPanel = new FormPreviewPanel("panel", Model.of(form));

        wicketTester.startComponentInPage(previewPanel);

        wicketTester.assertContains("This is page 1");
    }
    
    @Test
    public void afterAndBeforeAPageItRendersADropTarget() {
        WicketTester wicketTester = new WicketTester();

        Page page1 = new Page();
        page1.setTitle("This is page 1");
        
        Form form = mock(Form.class);
        when(form.getPages()).thenReturn(new LinkedHashSet<Page>(Arrays.asList(page1)));
        
        FormPreviewPanel previewPanel = new FormPreviewPanel("panel", Model.of(form));
        
        wicketTester.startComponentInPage(previewPanel);
        
        //wicketTester.executeBehavior(previewPanel.get("pageList").getBehaviors(DropTarget.class).get(0));
    }
}
