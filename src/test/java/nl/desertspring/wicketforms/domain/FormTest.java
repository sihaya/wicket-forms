/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import java.util.ArrayList;
import java.util.Arrays;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import org.junit.Test;

/**
 *
 * @author sihaya
 */
public class FormTest
{
    @Test
    public void givenAFormWithPagesAddingAfterSomePageSetsPositionOfOtherPages() {
        Page afterThisPage = mock(Page.class);
        Page beforeThisPage = mock(Page.class);
        
        when(afterThisPage.getPosition()).thenReturn(0);
        when(beforeThisPage.getPosition()).thenReturn(1);
        
        Form form = new Form();
        form.setPages(new ArrayList<Page>(Arrays.asList(afterThisPage, beforeThisPage)));
        
        Page page = form.addPageAfter(afterThisPage);
        
        assertThat(form.getPages(), hasItem(page));
        verify(beforeThisPage).setPosition(2);
        assertEquals((Integer)1, page.getPosition());
        assertEquals(form, page.getForm());
    }
    
    @Test
    public void givenAFormCreateSubmissionReturnsSubmission() {
        Page page = new Page();
        Form form = new Form();
        
        form.setPages(Arrays.asList(page));
        
        Submission submission = form.createSubmission();
        
        assertEquals(form, submission.getForm());
    }        
    
    @Test
    public void givenAFormCreateInvitationReturnsInvitation() {
        Form form = new Form();
        form.setInvitations(new ArrayList<Invitation>());
        
        Invitation invitation = form.createInvitation();
        
        assertThat(form.getInvitations(), hasItem(invitation));
    }
}
