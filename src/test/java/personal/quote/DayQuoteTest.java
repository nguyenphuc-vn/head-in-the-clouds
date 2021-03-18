package personal.quote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import personal.quote.helper.DayQuote;
import personal.quote.helper.IQuote;
import personal.quote.model.DayQuoteDTO;
import personal.quote.model.Quote;

import static org.junit.jupiter.api.Assertions.*;
class DayQuoteTest {
    Quote dayQuote;
    IQuote iDayQuote;
    String api = "https://api.quotable.io/random";
    String broken= "https://api.quotable.io/ran";
    @BeforeEach
    void setUp(){
        iDayQuote= new DayQuote();
        dayQuote = new DayQuoteDTO();
        iDayQuote.connect(api);
       // dayQuote = (Quote) iDayQuote.transform(dayQuote);

    }
    @Test
    void checkConnection(){
        assertTrue(iDayQuote.connect(api));
        assertFalse(iDayQuote.connect(broken));
    }
    @Test
    void returnString(){
       // assertNotNull(dayQuote.getContent());
       // assertNotNull(dayQuote.getAuthor());
        assertNotNull(iDayQuote.transform(dayQuote));
    }



}