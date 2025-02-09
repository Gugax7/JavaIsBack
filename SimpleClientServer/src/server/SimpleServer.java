package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(5000)){
           try(Socket socket = serverSocket.accept()) {
               System.out.println("Server accepts client connection");
               BufferedReader input = new BufferedReader(
                       new InputStreamReader(socket.getInputStream())
               );
               PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

               while(true){
                   String echoString = input.readLine();
                   System.out.println("Server got request data:" + echoString);

                   if(echoString.equals("exit")){
                       break;
                   }
                   output.println("Echo from server = " + echoString);
               }
           }

        }catch (IOException e){
            System.err.println("Server exception: " + e.getMessage());
        }

    }


}