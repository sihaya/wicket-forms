/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.grunt.domain.SubmissionProcessor;
import nl.desertspring.wicketforms.domain.Invitation;
import nl.desertspring.wicketforms.domain.InvitationRepository;
import nl.desertspring.wicketforms.domain.Submission;
import nl.desertspring.wicketforms.domain.SubmissionRepository;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author sihaya
 */
public class SubmissionFromInvitationPageTest
{

    @Test @Ignore
    public void givenAnInvitationCodeItRedirectsToSubmissionInputPage()
    {
        WicketTester wicketTester = new WicketTester();
        wicketTester.setFollowRedirects(true);

        PageParameters pageParameters = new PageParameters();
        pageParameters.add("invitationCode", "abcdef");

        Submission submission = mock(Submission.class);
        Invitation invitation = mock(Invitation.class);

        InvitationRepository invitationRepository = mock(InvitationRepository.class);
        when(invitationRepository.findBySecret("abcdef")).thenReturn(invitation);
        when(invitationRepository.merge(invitation)).thenReturn(invitation);

        SubmissionFromInvitationPage submissionFromInvitationPage = new SubmissionFromInvitationPage(pageParameters, invitationRepository, mock(SubmissionRepository.class), mock(SubmissionProcessor.class));

        wicketTester.startPage(submissionFromInvitationPage);

        wicketTester.assertRenderedPage(FormSubmissionInputPage.class);
    }
}
