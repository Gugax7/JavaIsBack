package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class DatagramChannelServer {

    private static final int PORT = 5000;
    private static final int PACKET_SIZE = 1024;

    public static void main(String[] args) {

        try(DatagramChannel channel = DatagramChannel.open()){

            channel.bind(new InetSocketAddress(PORT));
            System.out.println("Server listening on port: " + PORT);

            Selector selector = Selector.open();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
            ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);

            while(true) {
                selector.select();

                var selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while(keyIterator.hasNext()){
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();

                    if(key.isReadable()){
                        var registeredChannel = (DatagramChannel) key.channel();
                        buffer.clear();
                        var client = registeredChannel.receive(buffer);
                        buffer.flip();
                        byte[] data = new byte[buffer.remaining()];
                        buffer.get(data);
                        String audioPathFile = new String(data);
                        System.out.println("Client requested to listen to : " + audioPathFile);

                        new Thread(() -> sendDataToClient(audioPathFile,client,channel)).start();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error on server has occurred: " + e.getMessage());

        }
    }
    private static void sendDataToClient(String fileName, SocketAddress adress,
                                         DatagramChannel channel){

        ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);
        try(
                FileChannel fileChannel = FileChannel.open(Paths.get(fileName), StandardOpenOption.READ)
                ) {
            while(true){
                buffer.clear();
                int bytesRead = fileChannel.read(buffer);
                if(bytesRead == -1) break;
                buffer.flip();
                while(buffer.hasRemaining()){
                    channel.send(buffer,adress);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(22);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
