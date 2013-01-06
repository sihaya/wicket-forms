/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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
public class Page implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pageId;
    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    private Form form;
    private String title;
    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<Question>();
    private Integer position;

    public Integer getPageId()
    {
        return pageId;
    }

    public void setPageId(Integer pageId)
    {
        this.pageId = pageId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Form getForm()
    {
        return form;
    }

    public void setForm(Form form)
    {
        this.form = form;
    }

    public Integer getPosition()
    {
        return position;
    }

    public void setPosition(Integer position)
    {
        this.position = position;
    }

    /**
     * @return the questions
     */
    public List<Question> getQuestions()
    {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(List<Question> questions)
    {
        this.questions = questions;
    }

    public Question createQuestionAfter(Question question)
    {
        Question newQuestion = new Question();
        newQuestion.setPage(this);
        newQuestion.setPosition(questions.size() + 1);
        newQuestion.setText("new question");

        questions.add(newQuestion);

        return newQuestion;
    }

    public Question createQuestion()
    {
        Question newQuestion = new Question();
        newQuestion.setPage(this);
        newQuestion.setPosition(questions.size() + 1);
        newQuestion.setText("new question");

        questions.add(newQuestion);

        return newQuestion;
    }

    public boolean isLast()
    {
        return false;
    }
}
