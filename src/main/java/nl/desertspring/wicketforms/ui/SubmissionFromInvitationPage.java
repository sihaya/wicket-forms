/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import javax.persistence.NoResultException;
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

    public SubmissionFromInvitationPage(PageParameters pageParameters, InvitationRepository invitationRepository, SubmissionRepository submissionRepository)
    {
        this.invitationRepository = invitationRepository;
        this.submissionRepository = submissionRepository;

        init(pageParameters);
    }

    public SubmissionFromInvitationPage(PageParameters pageParameters)
    {
        init(pageParameters);
    }

    public void setInvitationRepository(InvitationRepository invitationRepository)
    {
        this.invitationRepository = invitationRepository;
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

        setResponsePage(new FormSubmissionInputPage(submissionRepository, Model.of(submissionRepository.merge(invitation.getSubmission()))));
    }
}
