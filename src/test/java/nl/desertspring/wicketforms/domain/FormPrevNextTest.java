/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import java.util.Arrays;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author sihaya
 */
public class FormPrevNextTest
{

    Page page1;
    Page page2;
    Page page3;
    Form form;

    @Before
    public void setUp()
    {
        page1 = mock(Page.class);
        page2 = mock(Page.class);
        page3 = mock(Page.class);

        form = new Form();
        form.setPages(Arrays.asList(page1, page2, page3));
    }

    @Test
    public void givenAFormWithSomePagesGetNextPageReturnsTheNextInTheList()
    {
        Page actual = form.getNextPage(page2);

        assertEquals(page3, actual);
    }
    
    @Test
    public void givenAFormWithSomePagesGetPreviousPageReturnsThePreviousInTheList() {
        Page actual = form.getPreviousPage(page2);

        assertEquals(page1, actual);
    }
}
