package practice.java.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by patrick on 6/25/2015.
 */

public class Test{

   @org.junit.Test
    public void test(){
       MockitoSample mockitoSample = mock(MockitoSample.class);
       when(mockitoSample.testMethod()).thenReturn(true);
       when(mockitoSample.testInt()).thenReturn(2);
       assertEquals(2,mockitoSample.testInt());
    }
}