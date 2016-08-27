package java;

import com.form.Contact;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by patrick on 1/4/15.
 */

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import com.form.Contact;

public class ContactController {

    @Test
    public ModelAndView showContracts(){
        addRequestParameter("","");
        System.out.println("showContracts!");
        return new ModelAndView("contact","command",new Contact());
    }
}
