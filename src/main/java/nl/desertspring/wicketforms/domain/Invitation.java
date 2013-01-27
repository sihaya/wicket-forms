/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.*;

/**
 *
 * @author sihaya
 */
@Entity
public class Invitation implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invitationId;
    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    private Form form;
    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private Submission submission;
    @Column(nullable = false)
    private String emailAddress;
    @Column(nullable = false, length = 10000)
    private String message;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentAt;
    @Column(nullable = false)
    private String secret;

    /**
     * @return the invitationId
     */
    public Integer getInvitationId()
    {
        return invitationId;
    }

    /**
     * @param invitationId the invitationId to set
     */
    public void setInvitationId(Integer invitationId)
    {
        this.invitationId = invitationId;
    }

    /**
     * @return the form
     */
    public Form getForm()
    {
        return form;
    }

    /**
     * @param form the form to set
     */
    public void setForm(Form form)
    {
        this.form = form;
    }

    /**
     * @return the submission
     */
    public Submission getSubmission()
    {
        return submission;
    }

    /**
     * @param submission the submission to set
     */
    public void setSubmission(Submission submission)
    {
        this.submission = submission;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress()
    {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

    /**
     * @return the sentAt
     */
    public Date getSentAt()
    {
        return sentAt;
    }

    /**
     * @param sentAt the sentAt to set
     */
    public void setSentAt(Date sentAt)
    {
        this.sentAt = sentAt;
    }

    public void prepareForSend()
    {
        secret = UUID.randomUUID().toString();

        sentAt = new Date();
    }

    public String getSecret()
    {
        return secret;
    }

    public void setSecret(String secret)
    {
        this.secret = secret;
    }
    
    public void createSubmission() {
        if (submission == null) {
            submission = form.createSubmission();
        }
    }
}
