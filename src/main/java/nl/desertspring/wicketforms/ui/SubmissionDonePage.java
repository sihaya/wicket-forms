/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Submission;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author sihaya
 */
public class SubmissionDonePage extends BasePage
{

    public SubmissionDonePage(IModel<Submission> submission)
    {
        add(new Label("submissionId", new PropertyModel<String>(submission, "submissionId")));
    }
    
}
