package personal.quote.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import personal.App;
import personal.quote.helper.DayQuote;
import personal.quote.helper.IQuote;
import personal.quote.helper.TaylorSwift;
import personal.quote.model.DayQuoteDTO;
import personal.quote.model.Quote;
import personal.quote.model.TaylorSwiftDTO;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuoteController implements Initializable {
    private final static String TAYLOR_API ="https://api.taylor.rest/";
    private final static String DAY_QUOTE_API ="https://api.quotable.io/random";
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
        iTaylor   = new TaylorSwift();
        taylor    = new TaylorSwiftDTO();
        iDayQuote = new DayQuote();
        dayQuote  = new DayQuoteDTO();

    }

    @FXML
    public void taylorClick(){
        isTrue = true;
        iTaylor.connect(TAYLOR_API);
        taylor = (Quote) iTaylor.transform(taylor);
        set(taylor,quoteLb,authorLb);

    }
    @FXML
    public void quoteClick(){
        isTrue   = false;
        iDayQuote.connect(DAY_QUOTE_API);
        dayQuote = (Quote) iDayQuote.transform(dayQuote);
        set(dayQuote,quoteLb,authorLb);
    }

    private void set(Quote quotes,Label quoteLb, Label authorLb){
        String author = quotes.getAuthor();
        String quote  = isTrue ?
                quotes.getQuote() :
                quotes.getContent();
        quoteLb.setText(quote);
        authorLb.setText(author);
    }
    @FXML
    private void backHome() throws IOException {
        App.switchBackHome();
    }
}
