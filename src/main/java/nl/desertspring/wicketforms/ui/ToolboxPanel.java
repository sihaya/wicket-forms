/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.wicketforms.domain.Question;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import wicketdnd.DragSource;
import wicketdnd.Operation;
import wicketdnd.Reject;
import wicketdnd.Transfer;

/**
 *
 * @author sihaya
 */
public class ToolboxPanel extends Panel
{

    public ToolboxPanel(String id)
    {
        super(id);

        addPage();
        addYesNoQuestion();
        addOpenQuestion();

    }
    
    private void addOpenQuestion()
    {
        WebMarkupContainer addOpenQuestionContainer = new WebMarkupContainer("addOpenQuestionContainer");
        WebMarkupContainer addOpenQuestion = new WebMarkupContainer("addOpenQuestion");
        addOpenQuestion.setOutputMarkupId(true);
        addOpenQuestionContainer.add(addOpenQuestion);

        DragSource addOpenQuestionDragSource = new DragSource(Operation.values())
        {

            @Override
            public String[] getTypes()
            {
                return new String[]{"question"};
            }
        };
        addOpenQuestionDragSource.drag("a.add-openquestion").initiate("a.add-openquestion");
        addOpenQuestionContainer.add(addOpenQuestionDragSource);
        addOpenQuestion.setDefaultModel(Model.of(Question.Type.OPEN));

        add(addOpenQuestionContainer);
    }
    
    private void addYesNoQuestion()
    {
        WebMarkupContainer addClosedYesNoQuestionContainer = new WebMarkupContainer("addClosedYesNoQuestionContainer");
        WebMarkupContainer addClosedYesNoQuestion = new WebMarkupContainer("addClosedYesNoQuestion");
        addClosedYesNoQuestion.setOutputMarkupId(true);
        addClosedYesNoQuestionContainer.add(addClosedYesNoQuestion);

        DragSource addClosedYesNoQuestionDragSource = new DragSource(Operation.values())
        {

            @Override
            public String[] getTypes()
            {
                return new String[]{"question"};
            }
        };
        addClosedYesNoQuestionDragSource.drag("a.add-closedyesnoquestion").initiate("a.add-closedyesnoquestion");
        addClosedYesNoQuestionContainer.add(addClosedYesNoQuestionDragSource);
        addClosedYesNoQuestion.setDefaultModel(Model.of(Question.Type.CLOSED_YES_NO));

        add(addClosedYesNoQuestionContainer);
    }

    private void addPage()
    {
        WebMarkupContainer addPageContainer = new WebMarkupContainer("addPageContainer");
        WebMarkupContainer addPage = new WebMarkupContainer("addPage");
        addPage.setOutputMarkupId(true);
        addPageContainer.add(addPage);

        DragSource addPageDragSource = new DragSource(Operation.values())
        {

            @Override
            public String[] getTypes()
            {
                return new String[]{"page"};
            }
        };
        addPageDragSource.drag("a.add-page").initiate("a.add-page");
        addPageContainer.add(addPageDragSource);
        
        add(addPageContainer);
    }
}
