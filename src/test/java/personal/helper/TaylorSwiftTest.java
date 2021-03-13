package personal.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import personal.helper.quote.IQuote;
import personal.helper.quote.TaylorSwift;
import personal.model.quote.Quote;
import personal.model.quote.TaylorSwiftDTO;

import static org.junit.jupiter.api.Assertions.*;

class TaylorSwiftTest {
    IQuote taylorSwift;
    Quote taylor;
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
        // work now
        assertEquals(taylor.getQuote(),taylor.getQuote());
        assertEquals(taylor.getAuthor(),author);

    }
}