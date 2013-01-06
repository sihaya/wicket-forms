/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Answer;
import nl.desertspring.wicketforms.domain.Question;
import nl.desertspring.wicketforms.domain.Submission;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

/**
 *
 * @author sihaya
 */
public class AnswerModel extends AbstractReadOnlyModel<Answer>
{
    private IModel<Submission> submission;
    private Question question;

    public AnswerModel(IModel<Submission> submission, Question question)
    {
        this.submission = submission;
        this.question = question;
    }

    @Override
    public Answer getObject()
    {
        return submission.getObject().getAnswer(question);
    }
    
}
