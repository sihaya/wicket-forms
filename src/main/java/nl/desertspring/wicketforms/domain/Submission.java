/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author sihaya
 */
@Entity
public class Submission implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer submissionId;
    @JoinColumn
    @ManyToOne
    private Form form;
    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL)
    private List<Answer> answers;
    @Column(nullable = false)
    private boolean submitted;

    public Integer getSubmissionId()
    {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId)
    {
        this.submissionId = submissionId;
    }

    public Form getForm()
    {
        return form;
    }

    public void setForm(Form form)
    {
        this.form = form;
    }

    public Answer getAnswer(Question question)
    {
        System.out.println(question);

        for (Answer answer : answers) {
            System.out.println(answer.getQuestion());

            if (answer.getQuestion().getQuestionId() == question.getQuestionId()) {
                return answer;
            }
        }

        Answer answer = new Answer();
        answer.setQuestion(question);
        answers.add(answer);
        answer.setSubmission(this);

        return answer;
    }

    public List<Answer> getAnswers()
    {
        return answers;
    }

    public void setAnswers(List<Answer> answers)
    {
        this.answers = answers;
    }

    public void submit()
    {
        submitted = true;
    }

    public boolean isSubmitted()
    {
        return submitted;
    }

    public void setSubmitted(boolean submitted)
    {
        this.submitted = submitted;
    }
}
