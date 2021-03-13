package personal.controller.quote;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import personal.helper.quote.DayQuote;
import personal.helper.quote.IQuote;
import personal.helper.quote.TaylorSwift;
import personal.model.quote.DayQuoteDTO;
import personal.model.quote.Quote;
import personal.model.quote.TaylorSwiftDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class QuoteController implements Initializable {
    private final static String taylorAPI="https://api.taylor.rest/";
    private final static String dayQuoteApi="https://api.quotable.io/random";
    private IQuote iDayQuote;
    private IQuote iTaylor;
    private Quote taylor;
    private Quote dayQuote;
    private boolean isTrue;
    @FXML
    private Label quoteLb;
    @FXML
    private Label authorLb;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iTaylor = new TaylorSwift();
        taylor = new TaylorSwiftDTO();
        iDayQuote = new DayQuote();
        dayQuote = new DayQuoteDTO();

    }

    @FXML
    public void taylorClick(){
        isTrue = true;
        iTaylor.connect(taylorAPI);
        taylor = (Quote) iTaylor.transform(taylor);
        set(taylor,quoteLb,authorLb);

    }
    @FXML
    public void quoteClick(){
        isTrue = false;
        iDayQuote.connect(dayQuoteApi);
        dayQuote = (Quote) iDayQuote.transform(dayQuote);
        set(dayQuote,quoteLb,authorLb);
    }

    private void set(Quote quotes,Label quoteLb, Label authorLb){

        String author = quotes.getAuthor();
        String quote = isTrue ?
                quotes.getQuote() :
                quotes.getContent();
        quoteLb.setText(quote);
        authorLb.setText(author);
    }
}
