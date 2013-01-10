/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import wicketdnd.DragSource;
import wicketdnd.Operation;

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
