/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import java.io.IOException;
import java.io.InputStream;
import nl.desertspring.wicketforms.domain.Answer;
import nl.desertspring.wicketforms.domain.Question;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author sihaya
 */
public class AttachmentQuestionTest
{

    Answer answer;
    Question question;
    org.apache.wicket.markup.html.form.Form<Void> form;
    WicketTester wicketTester;
    AttachmentQuestion attachmentQuestion;

    @Before
    public void setUp()
    {
        wicketTester = new WicketTester()
        {

            @Override
            protected String createPageMarkup(String componentId)
            {
                return "<html><head></head><body><form wicket:id='form'><span wicket:id='component'></span></form></body></html>";
            }
        };

        form = new org.apache.wicket.markup.html.form.Form<Void>("form");
        form.setMultiPart(true);
        question = new Question();
        answer = mock(Answer.class);

        when(answer.getQuestion()).thenReturn(question);
        attachmentQuestion = new AttachmentQuestion("component", Model.of(answer));

        form.add(attachmentQuestion);
    }

    @Test
    public void givenAnAnswerItRendersAnUploadBox()
    {
        final String text = "Please upload something:";

        question.setText(text);

        wicketTester.startComponentInPage(form);

        wicketTester.assertContains(text);
        wicketTester.assertComponent("form:component:attachment", FileUploadField.class);
    }

    @Test
    @Ignore
    public void givenAFileUploadItCallsSetAttachmentOnAnswer() throws IOException
    {
        wicketTester.startComponentInPage(form);

        FormTester formTester = wicketTester.newFormTester("form");
        formTester.setFile("component:attachment", new File("/tmp/test"), "text/plain");
        formTester.submit();
        
        attachmentQuestion.onSubmit();

        wicketTester.assertNoErrorMessage();

        verify(answer).attachFile(eq("test"), any(byte[].class));
    }
}
