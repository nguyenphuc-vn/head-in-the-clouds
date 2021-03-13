package personal.model;

/**
 * only for Json to java object
 */
public class TaylorSwiftDTO {


    private String quote;
    private String author;

    public TaylorSwiftDTO() {
    }
    public String getQuote() {
        return quote;
    }
    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return  quote+"\n"  +
                 author ;
    }
}
