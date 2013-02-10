/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketforms.ui;

import nl.desertspring.grunt.domain.SubmissionProcessor;
import nl.desertspring.wicketforms.domain.Form;
import nl.desertspring.wicketforms.domain.FormRepository;
import nl.desertspring.wicketforms.domain.Submission;
import nl.desertspring.wicketforms.domain.SubmissionRepository;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author sihaya
 */
public class FormSubmissionPage extends BasePage
{
    @SpringBean
    private FormRepository formRepository;
    
    @SpringBean
    private SubmissionProcessor submissionProcessor;
    
    @SpringBean
    private SubmissionRepository submissionRepository;

    public FormSubmissionPage(FormRepository formRepository, SubmissionRepository submissionRepository, SubmissionProcessor submissionProcessor)
    {
        init(formRepository, submissionRepository, submissionProcessor);
    }

    public FormSubmissionPage()
    {
        init(formRepository, submissionRepository, submissionProcessor);
    }
    
    private void init(FormRepository formRepository, final SubmissionRepository submissionRepository, final SubmissionProcessor submissionProcessor)
    {        
        final IModel<Form> selectedForm = new Model<Form>();
        DropDownChoice<Form> forms = new DropDownChoice<Form>("forms", selectedForm, new FormListModel(formRepository));
        forms.setRequired(true);
        
        org.apache.wicket.markup.html.form.Form<Void> newSubmissionForm = new org.apache.wicket.markup.html.form.Form<Void>("newSubmissionForm") {
            @Override
            protected void onSubmit()
            {
                Submission submission = selectedForm.getObject().createSubmission();
                
                submissionRepository.save(submission);
                
                setResponsePage(new FormSubmissionInputPage(submissionRepository, submissionProcessor, Model.of(submission)));
            }            
        };
        
        newSubmissionForm.add(forms);
        
        add(newSubmissionForm);
    }
    
}
