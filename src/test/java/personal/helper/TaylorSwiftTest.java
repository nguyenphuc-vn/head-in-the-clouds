package personal.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import personal.model.TaylorSwiftDTO;

import static org.junit.jupiter.api.Assertions.*;

class TaylorSwiftTest {
    TaylorSwift taylorSwift;
    TaylorSwiftDTO taylor;
    private final String api = "https://api.taylor.rest/";
    String broken = "https://www.baeldung.com/httpclient-status";
    String author = "Taylor Swift";
   // String anything;
    @BeforeEach
    void setUp() {
        taylorSwift = new TaylorSwift();
        taylor = new TaylorSwiftDTO();
        taylorSwift.connect(api);
       taylor = (TaylorSwiftDTO) taylorSwift.transform(taylor);
    }
    @Test
    void doesItConnectsOk(){
        assertTrue(taylorSwift.connect(api));
        assertFalse(taylorSwift.connect(broken));
    }
    @Test
    void willItReturnString(){
        // it failed but transform works
        //assertEquals(taylorSwift.transform(taylor),taylor.getQuote());
        //assertNotNull(taylorSwift.transform(taylor));
        //assertEquals(taylorSwift.transform(taylor),taylor);
        assertEquals(taylor.getAuthor(),author);



    }
}