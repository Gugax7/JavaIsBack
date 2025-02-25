package client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentRequests {

    private static final Path orderTracking = Path.of("orderTracking.json");
    private static final Lock lock = new ReentrantLock();
    public static void main(String[] args) {

        Map<String, Integer> orderMap =
                Map.of("apples", 400,
                       "bananas", 500,
                       "carrots", 120,
                       "mango",2000,
                       "tomatoes", 10000);

        String urlParams = "product=%s&amount=%d";
        String urlBase = "http://localhost:8080";

        List<URI> sites = new ArrayList<>();
        orderMap.forEach((k,v) -> sites.add(URI.create(
                urlBase + "?" + urlParams.formatted(k,v))));

        HttpClient client = HttpClient.newHttpClient();

        if(!Files.exists(orderTracking)){
            try {
                Files.createFile(orderTracking);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        sendPostsGetJSON(client,urlBase,urlParams,orderMap);

    }
    private static void writeToFile(String content){
        lock.lock();
        try{
            Files.writeString(orderTracking,content+"\r",
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }

    private static void sendGets(HttpClient client, List<URI> uris){
        var futures = uris.stream()
                .map(HttpRequest::newBuilder)
                .map(HttpRequest.Builder::build)
                .map(request -> client.sendAsync(
                        request, HttpResponse.BodyHandlers.ofString()))
                .toList();
        var allFutureRequests = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture<?>[0])
        );
        allFutureRequests.join();

        futures.forEach(f -> {
            System.out.println(f.join().body());
        });
    }

    private static void sendPosts(HttpClient client, List<URI> uris){
        var futures = uris.stream()
                .map(uri -> HttpRequest.newBuilder()
                        .POST(HttpRequest.BodyPublishers.ofString(
                                uri.toString().substring(uri.toString().indexOf("?") + 1))))
                .map(HttpRequest.Builder::build)
                .map(request -> client.sendAsync(request,HttpResponse.BodyHandlers.ofString()))
                .toList();

        var allFutureRequests = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture<?>[0])
        );
        allFutureRequests.join();
        futures.forEach(f -> {
            System.out.println(f.join().body());
        });

    }



    private static void teacherSendPosts(HttpClient client, String baseURI,
                                         String paramString, Map<String, Integer> orders){

        var futures = orders.entrySet().stream()
                .map(e -> paramString.formatted(e.getKey(),e.getValue()))
                .map(uri -> HttpRequest.newBuilder(URI.create(baseURI))
                        .POST(HttpRequest.BodyPublishers.ofString(uri)))
                .map(HttpRequest.Builder::build)
                .map(request -> client.sendAsync(
                        request, HttpResponse.BodyHandlers.ofString()))
                .toList();
        var allFutureRequests = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture<?>[0])
        );
        allFutureRequests.join();
        List<String> lines = new ArrayList<>();


        futures.forEach(f -> {
            lines.add(f.join().body());
        });

        try {
            Files.write(orderTracking,lines, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void sendPostsGetJSON(HttpClient client, String baseURI,
                                         String paramString, Map<String,Integer> orders) {

        ObjectMapper objectMapper = new ObjectMapper();
        var handler = JsonBodyHandler.create(objectMapper);
        var futures = orders.entrySet().stream()
                .map(e -> paramString.formatted(
                        e.getKey(), e.getValue()))
                .map(s -> HttpRequest.newBuilder(URI.create(baseURI))
                        .POST(HttpRequest.BodyPublishers.ofString(s)))
                .map(HttpRequest.Builder::build)
                .map(request -> client.sendAsync(
                        request, handler))
                .toList();

        var allFutureRequests = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture<?>[0])
        );

        allFutureRequests.join();

        futures.forEach(f -> {
            JsonNode node = f.join().body().get("order");
            System.out.printf("Order Id:%s Expected Delivery: %s %n",
                    node.get("orderId"),
                    node.get("orderDeliveryDate").asText());
        });
    }

    private static void sendPostsSafeFileWrite(HttpClient client, String baseURI,
                                         String paramString, Map<String, Integer> orders){

        var futures = orders.entrySet().stream()
                .map(e -> paramString.formatted(e.getKey(),e.getValue()))
                .map(uri -> HttpRequest.newBuilder(URI.create(baseURI))
                        .POST(HttpRequest.BodyPublishers.ofString(uri)))
                .map(HttpRequest.Builder::build)
                .map(request -> client.sendAsync(
                        request, HttpResponse.BodyHandlers.ofString())
                        .thenAccept(r -> writeToFile(r.body())))
                .toList();
        var allFutureRequests = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture<?>[0])
        );
        allFutureRequests.join();
        List<String> lines = new ArrayList<>();


        try {
            Files.write(orderTracking,lines, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
