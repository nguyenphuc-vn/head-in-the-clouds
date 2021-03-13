package personal.helper.quote;


public interface IQuote {
    boolean connect(String api);
    Object transform(Object object);
}
