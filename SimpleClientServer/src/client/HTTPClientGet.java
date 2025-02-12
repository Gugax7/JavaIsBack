package client;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.stream.Stream;

import static java.net.HttpURLConnection.HTTP_OK;

public class HTTPClientGet {


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


            HttpResponse<Stream<String>> response = client.send(request,
                    HttpResponse.BodyHandlers.ofLines());
            if (response.statusCode() != HTTP_OK) {
                System.err.println("Error reading web page!");
                return;
            }
            response.body()
                    .filter(s -> s.contains("<h1>"))
                    .map(s -> s.replaceAll("<[^>]*>","").strip())
                    .forEach(System.out::println);
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}