import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Putting on file
        try (FileOutputStream fos = new FileOutputStream("src/main/resources/output1.bin");
             DataOutputStream dos = new DataOutputStream(fos)) {

            dos.writeUTF("My first string argument");
            //dos.writeUTF("My second string argument");
            dos.writeDouble(1.52);
            //dos.writeDouble(3.1415);
            dos.writeChar('H');
            dos.writeInt(7);
            dos.flush();

        } catch (IOException e) {
            System.err.println("Error on writing on files: " + e.getMessage());
        }
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("output1.txt");
        try (BufferedInputStream bis = new BufferedInputStream(inputStream)) {
            int data;
            while ((data = bis.read()) != -1) {
                System.out.print((char) data);
            }

        } catch (IOException e) {
            System.out.println("Error on reading file: " + e.getMessage());
        }
    }
}
//        //Reading the file with DataInputStream
//
//        if (inputStream == null) {
//            System.out.println("InputStream doesn't exist");
//            return;
//        }
//
//        try (DataInputStream dis = new DataInputStream(inputStream)) {
//
//            // Reading the data in the same order it was written
//            String stringValue = dis.readUTF();
//            double doubleValue = dis.readDouble();
//            double sdoubleValue = dis.readDouble();
//            char charValue = dis.readChar();
//            int intValue = dis.readInt();
//
//            // Output the read values
//            System.out.println("Double: " + doubleValue + "," + sdoubleValue);
//            System.out.println("Integer: " + intValue);
//            System.out.println("String: " + stringValue);
//            System.out.println("Character: " + charValue);
//
//        } catch (IOException e) {
//            System.err.println("Error reading from the file: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }


//        String data = "Hello world, my name is gustavo and now i am practicing working with files in Java";
//
//        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("output1.bin");
//        if(inputStream == null){
//            System.out.println("InputStream doesn't exist");
//        }
//        try(//FileOutputStream fos = new FileOutputStream("src/main/resources/output1.bin");
//            //BufferedOutputStream bos = new BufferedOutputStream(fos);
//            //DataOutputStream dos = new DataOutputStream(fos);
//            DataInputStream dis = new DataInputStream(inputStream)
//        ){
////            byte[] bytesArray = data.getBytes();
////            bos.write(bytesArray);
////            bos.flush();
//
//            double doubleValue = dis.readDouble();
//            int intValue = dis.readInt();
//            String stringValue = dis.readUTF();
//            char charValue = dis.readChar();
//
//            System.out.println("Double: " + doubleValue);
//            System.out.println("Integer: " + intValue);
//            System.out.println("String: " + stringValue);
//            System.out.println("Character: " + charValue);
//
////            dos.writeInt(10);
////            dos.writeChar('G');
////            dos.writeDouble(3.1415);
////            dos.writeUTF("Ultimate tonto fight");
////            dos.flush();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
