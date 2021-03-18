package personal.quote.helper;


public interface IQuote {
    boolean connect(String api);
    Object transform(Object object);
}
