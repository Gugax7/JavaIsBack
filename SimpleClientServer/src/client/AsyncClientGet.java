package client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.net.HttpURLConnection.HTTP_OK;

public class AsyncClientGet {


    public static void main(String[] args) {

        try {
            URL url = new URL("http://localhost:8080");
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(url.toURI())
                    .header("User-Agent", "Chrome")
                    .headers("Accept", "application/json", "Accept", "text/html")
                    .timeout(Duration.ofSeconds(15))
                    .build();


            HttpResponse<Stream<String>> response;

            var responseFuture = client.sendAsync(request,HttpResponse.BodyHandlers.ofLines());


            int result = -1;

            while(!responseFuture.isDone()){
                System.out.print(". ");
                TimeUnit.SECONDS.sleep(1);
            }

            System.out.println();
            try {
                response = responseFuture.get();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            handleResponse(response);

        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static void handleResponse(HttpResponse<Stream<String>> response){
        if(response.statusCode() == HTTP_OK){
            response.body()
                    .filter(s -> s.contains("<h1>"))
                    .map(s -> s.replaceAll("<[^>]*>","").strip())
                    .forEach(System.out::println);
        }else{
            System.out.println("Error reading response = " + response.uri());
        }

    }
}