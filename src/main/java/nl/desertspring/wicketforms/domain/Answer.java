/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author sihaya
 */
@Entity
public class Answer implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;
    private String value;
    @JoinColumn
    @ManyToOne
    private Question question;
    @JoinColumn
    @ManyToOne
    private Submission submission;

    /**
     * @return the answerId
     */
    public Integer getAnswerId()
    {
        return answerId;
    }

    /**
     * @param answerId the answerId to set
     */
    public void setAnswerId(Integer answerId)
    {
        this.answerId = answerId;
    }

    /**
     * @return the value
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value)
    {
        this.value = value;
    }

    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }

    public Submission getSubmission()
    {
        return submission;
    }

    public void setSubmission(Submission submission)
    {
        this.submission = submission;
    }
}
