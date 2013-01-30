/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import com.floreysoft.jmte.Engine;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.apache.wicket.util.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

/**
 *
 * @author sihaya
 */
@Service
public class InvitationService
{

    private EmailService emailService;
    private InvitationRepository invitationRepository;
    //@Value("${serverUrl}")
    private String serverUrl = "http://wicket-forms.sihaya.cloudbees.net/invitation?invitationCode=";

    @Autowired
    public void setEmailService(EmailService emailService)
    {
        this.emailService = emailService;
    }

    @Autowired
    public void setInvitationRepository(InvitationRepository invitationRepository)
    {
        this.invitationRepository = invitationRepository;
    }

    @Transactional
    public void persistAndSend(Invitation invitation)
    {                
        invitation.setMessage("");
        invitation.prepareForSend();
        String message = generateMessage(invitation);

        emailService.send(invitation.getEmailAddress(), "Invitation for new submission in Wicket Forms", message);

        invitationRepository.persist(invitation);
    }

    protected String generateMessage(Invitation invitation)
    {
        Map<String, Object> model = new HashMap<String, Object>();

        String url = serverUrl + invitation.getSecret();

        model.put("filloutURL", url);

        Engine engine = Engine.createDefaultEngine();
        String template;
        try {
            template = IOUtils.toString(getClass().getResourceAsStream("invitation-template.html"));
        }
        catch (IOException ex) {
            throw new IllegalStateException("Cannot create template", ex);
        }

        return engine.transform(template, model);
    }
}
