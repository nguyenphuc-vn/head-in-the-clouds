package personal.helper.quote;

import com.google.gson.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

/**
 * send request -> read body -> parse reader -> turn to DayQuoteDTO (Object)
 * using HttpURLConnection instead
 * the result is more code to write
 * but still work
 */
public class DayQuote implements IQuote {
    private final static Logger LOGGER = Logger.getLogger(DayQuote.class.getName());
    private HttpURLConnection httpconn;

    @Override
    public boolean connect(String api) {
        try{
            URL url  = new URL(api);
            httpconn = (HttpURLConnection) url.openConnection();
            httpconn.setRequestProperty("accept","application/json");
            httpconn.setRequestMethod("GET");
            httpconn.setConnectTimeout(5000);
            int statusCode = httpconn.getResponseCode();
            LOGGER.info("status code: "+statusCode);
            if(statusCode == HttpURLConnection.HTTP_OK){
                return true;
            }else LOGGER.warning("replied with code: "+statusCode);

        }catch (IOException ex){
            LOGGER.warning(ex.toString());
        }
        return false;
    }

    @Override
    public Object transform(Object object) {
        try{
            InputStreamReader body = new InputStreamReader((InputStream) httpconn.getContent());
            JsonElement root = JsonParser.parseReader(body);
            LOGGER.info(root+" ");
            Gson gson = new Gson();
            object = gson.fromJson(root,object.getClass());
        }catch (IOException ex){
            LOGGER.warning(ex.toString());
        }
        return object;
    }
}
