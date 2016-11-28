package test.java;



import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import com.form.Contact;

/**
 * Created by patrick on 1/4/15.
 */

public class ContactTest {

    @Test
    public void test() {
        Contact contactMock = mock(Contact.class);
        when(contactMock.getFirstname()).thenReturn("test");
        assertEquals("test", contactMock.getFirstname());

    }

}
