/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.domain;

import java.io.Serializable;
import java.util.Date;
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
    
    @OrderBy("pageId")
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Page> pages;

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

    public Set<Page> getPages()
    {
        return pages;
    }

    public void setPages(Set<Page> pages)
    {
        this.pages = pages;
    }

    public void addPageAfter(Page page)
    {
        Page newPage = new Page();
                
        pages.add(newPage);
        
        newPage.setTitle("New page " + pages.size());
    }
}
