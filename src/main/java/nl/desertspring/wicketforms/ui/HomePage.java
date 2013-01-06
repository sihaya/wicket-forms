/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

/**
 *
 * @author sihaya
 */
public class HomePage extends BasePage
{
    public HomePage() {
        add(new BookmarkablePageLink("take", FormSubmissionPage.class));
        add(new BookmarkablePageLink("edit", FormOverviewPage.class));
    }
}
