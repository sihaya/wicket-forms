/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sihaya
 */
@Entity
public class Form implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer formId;
    @Column(nullable = false)
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    @OrderBy("position")
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Page> pages;
    
    @OrderBy("sentAt")
    @OneToMany(mappedBy = "form")
    private List<Invitation> invitations;
    
    public Page getPage(int index) {
        return pages.get(index);
    }
    
    /**
     * @return the formId
     */
    public Integer getFormId()
    {
        return formId;
    }

    /**
     * @param formId the formId to set
     */
    public void setFormId(Integer formId)
    {
        this.formId = formId;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public List<Page> getPages()
    {
        return pages;
    }

    public void setPages(List<Page> pages)
    {
        this.pages = pages;
    }

    public Page addPageAfter(Page page)
    {
        Page newPage = new Page();
                
        pages.add(pages.indexOf(page) + 1, newPage);
        
        newPage.setTitle("New page " + pages.size());
        newPage.setPosition(page.getPosition() + 1);
        newPage.setForm(this);
        newPage.setQuestions(new ArrayList<Question>());
                
        int newIndex = pages.indexOf(newPage);
        if (newIndex != pages.size() - 1) {
            for(Page update : pages.subList(newIndex + 1, pages.size())) {
                update.setPosition(update.getPosition() + 1);
            }
        }
        
        return newPage;
    }

    public Submission createSubmission()
    {
        Submission submission = new Submission();
        submission.setForm(this);
        submission.setAnswers(new ArrayList<Answer>());
        submission.setSubmitted(false);
        
        return submission;
    }

    public Page getStartPage()
    {
        return pages.get(0);
    }

    @Override
    public String toString()
    {
        return name;
    }
    
    public Page getNextPage(Page page) {
        if (page.isLast()) {
            throw new IllegalStateException("Cannot get next page of last page");
        }
        
        return pages.get(pages.indexOf(page) + 1);
    }
    
    public Page getPreviousPage(Page page) {
        if (page.isFirst()) {
            throw new IllegalStateException("Cannot get previous page of first page");            
        }
        
        return pages.get(pages.indexOf(page) - 1);
    }

    public Invitation createInvitation()
    {
        Invitation invitation = new Invitation();
        invitation.setForm(this);
                
        return invitation;
    }

    public List<Invitation> getInvitations()
    {
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations)
    {
        this.invitations = invitations;
    }
    
    
}
