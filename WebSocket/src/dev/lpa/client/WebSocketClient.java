package dev.lpa.client;


import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.Scanner;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class WebSocketClient {

    public static void main(String[] args) throws URISyntaxException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name to join chat: ");
        String name = sc.nextLine();

        HttpClient client = HttpClient.newHttpClient();
        WebSocket socket = client.newWebSocketBuilder()
                .buildAsync(new URI("ws://localhost:8080?name=%s".formatted(name)),
                        new WebSocket.Listener() {
                            @Override
                            public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {

                                System.out.println(data);
                                return WebSocket.Listener.super.onText(webSocket, data, last);
                            }
                        }).join();

        while (true) {
            String input = sc.nextLine();
            if ("bye".equals(input)) {
                try {
                    socket.sendClose(WebSocket.NORMAL_CLOSURE, "User left normally").get();
                    break;
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            } else {
                socket.sendText(input, true);
            }
        }
    }
}
