package personal.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaylorSwiftTest {
    TaylorSwift taylorSwift;
    private final String api = "https://api.taylor.rest/";
    String broken = "https://www.baeldung.com/httpclient-status";
    @BeforeEach
    void setUp() {
        taylorSwift = new TaylorSwift();
    }
    @Test
    void doesItConnectsOk(){
        assertTrue(taylorSwift.connect(api));
        assertFalse(taylorSwift.connect(broken));
    }
}