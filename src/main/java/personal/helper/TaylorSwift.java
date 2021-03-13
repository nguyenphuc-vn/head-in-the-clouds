package personal.helper;

import com.google.gson.Gson;
import personal.IApp;
import personal.model.TaylorSwiftDTO;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.logging.*;

/**
 * using HttpClient , send get request to server
 * return resposebody
 * turn that into TaylorSwiftDTO
 */
public class TaylorSwift implements IApp {
           private final static Logger LOGGER = Logger.getLogger(TaylorSwift.class.getName());
           private String body;


    @Override
    public boolean connect(String api) {
       try{
           HttpClient httpClient = HttpClient.newHttpClient();
           HttpRequest request =
                 HttpRequest.newBuilder()
                .uri(URI.create(api))
                .timeout(Duration.ofSeconds(5))
                .build();
           // Convert response body bytes into String
           HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
           //get status codes
           int statusCode = response.statusCode();
           LOGGER.info("Status code: "+statusCode);
           if (statusCode == HttpURLConnection.HTTP_OK){
               body = response.body();
               LOGGER.info(body);
               return true;
           }
       }catch (IOException | InterruptedException ex){
           LOGGER.severe(ex.toString());

       }
       return false;
    }

    @Override
    public Object transform(Object object) {
        Gson gson = new Gson();
        object  = gson.fromJson(body,TaylorSwiftDTO.class);
        return object;
    }


}
