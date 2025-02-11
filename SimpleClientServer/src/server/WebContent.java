package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;

public class WebContent {
    public static void main(String[] args) {

        try {
            URL url = new URL("https://example.com/extra");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Chrome");
            connection.setRequestProperty("Accept","application/json, text/html");
            connection.setConnectTimeout(15_000);

            int responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);
            if(responseCode != HTTP_OK){
                System.err.println("Error on connecting to webpage");
                return;
            }
            printContent(connection.getInputStream());
            //printContent(url.openStream());
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printContent(InputStream is){

        try(BufferedReader inputStream = new BufferedReader(
                new InputStreamReader(is))
        ){
            String line;
            while((line = inputStream.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
