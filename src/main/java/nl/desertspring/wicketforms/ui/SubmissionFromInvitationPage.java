/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import javax.persistence.NoResultException;
import nl.desertspring.grunt.domain.SubmissionProcessor;
import nl.desertspring.wicketforms.domain.Invitation;
import nl.desertspring.wicketforms.domain.InvitationRepository;
import nl.desertspring.wicketforms.domain.Submission;
import nl.desertspring.wicketforms.domain.SubmissionRepository;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author sihaya
 */
public class SubmissionFromInvitationPage extends BasePage
{

    @SpringBean
    private InvitationRepository invitationRepository;
    @SpringBean
    private SubmissionRepository submissionRepository;
    @SpringBean
    private SubmissionProcessor submissionProcessor;

    public SubmissionFromInvitationPage(PageParameters pageParameters, InvitationRepository invitationRepository, SubmissionRepository submissionRepository, SubmissionProcessor submissionProcessor)
    {
        this.invitationRepository = invitationRepository;
        this.submissionRepository = submissionRepository;
        this.submissionProcessor = submissionProcessor;

        init(pageParameters);
    }

    public SubmissionFromInvitationPage(PageParameters pageParameters)
    {
        init(pageParameters);
    }

    private void init(PageParameters pageParameters)
    {
        String secret = pageParameters.get("invitationCode").toString();

        Invitation invitation;
        try {
            invitation = invitationRepository.findBySecret(secret);
        }
        catch (NoResultException ex) {
            throw new IllegalStateException("Unknown invitation code", ex);
        }

        invitation.createSubmission();
        invitation = invitationRepository.merge(invitation);

        setResponsePage(new FormSubmissionInputPage(submissionRepository, submissionProcessor, Model.of(submissionRepository.merge(invitation.getSubmission()))));
    }
}
