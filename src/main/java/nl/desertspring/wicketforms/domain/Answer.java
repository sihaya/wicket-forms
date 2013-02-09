/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import java.io.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.apache.wicket.util.io.IOUtils;
import org.hibernate.annotations.Type;

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
    @JoinColumn(nullable = false)
    @ManyToOne
    private Submission submission;
    private String filename;
    @Column(length = 65535)
    private byte[] fileContent;

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

    public void attachFile(String filename, byte[] fileContent) throws IOException
    {
        System.out.println("Filename: " + filename);
        
        this.fileContent = fileContent;
        this.filename = filename;
    }

    public byte[] getFileContent()
    {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent)
    {
        this.fileContent = fileContent;
    }
}
