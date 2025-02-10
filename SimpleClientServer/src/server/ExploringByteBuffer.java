package server;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class ExploringByteBuffer {
    public static void main(String[] args) {

        Consumer<ByteBuffer> printBuffer = buffer ->{
            byte[] data = new byte[buffer.limit()];
            buffer.get(data);
            System.out.printf("%s", new String(data, StandardCharsets.UTF_8));
        };

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        doOperation("Write: ", buffer, b-> b.put("This is a cool test".getBytes()));
        doOperation("Flip(from write to read): ", buffer, ByteBuffer::flip);
        doOperation("Read: ", buffer, printBuffer);

        doOperation("Flip(Read to write this time): ", buffer, ByteBuffer::flip);
        doOperation("Compact: ", buffer, ByteBuffer::compact);
        doOperation("Append: ", buffer, b-> b.put(", This is a new test mf ".getBytes()));
       // doOperation("Flip: ", buffer, ByteBuffer::flip);
        doOperation("Read: ", buffer.slice(0,buffer.position()), printBuffer);
        doOperation("Clear: ", buffer, ByteBuffer::clear);

    }
    private static void doOperation(String op, ByteBuffer buffer, Consumer<ByteBuffer> cons){
        System.out.printf("%-30s",op);
        cons.accept(buffer);
        System.out.printf("capacity = %d, limit = %d, position = %d, remaining = %d%n",
                buffer.capacity(),buffer.limit(), buffer.position(), buffer.remaining());
    }
}

