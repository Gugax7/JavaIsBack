import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {

        Path path = Path.of("files/several/foulders/main_file.mp3");

        useFile("myfile.txt");
        usePath("myPathFile.txt");
        System.out.println("----------------------------");
        printPathInfo(path);
        logStatement(path);
        extraInfo(path);

    }

    private static void extraInfo(Path path){
        try{
            var atts = Files.readAttributes(path, "*");
            atts.entrySet().forEach(System.out::println);
            System.out.println(Files.probeContentType(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void logStatement(Path path){

        try{
            Path parent = path.getParent();
            if(!Files.exists(parent)){
                Files.createDirectories(parent);
            }
            Files.writeString(path, Instant.now() + ": hello world\n", StandardCharsets.UTF_8, StandardOpenOption.APPEND,StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printPathInfo(Path path){
        System.out.println("Path: " + path);
        System.out.println("File name:" + path.getFileName());
        System.out.println("Parent: " + path.getParent());
        Path absolutePath = path.toAbsolutePath();
        System.out.println("Absolute path:" + absolutePath);
        System.out.println("Absolute path root: " + absolutePath.getRoot());
        System.out.println("Root: " + path.getRoot());
        System.out.println("Is absolute?: " + path.isAbsolute());

        System.out.println(absolutePath.getRoot());
//        int i = 0;
//        var it = path.toAbsolutePath().iterator();
//        while(it.hasNext()){
//            System.out.println("-".repeat(i++) + " " + it.next());
//        }
        int pathParts = absolutePath.getNameCount();
        for (int i = 0; i < pathParts; i++) {
            System.out.println(".".repeat(i + 1) + " " + absolutePath.getName(i));

        }
    }
    private static void useFile(String fileName){
        File file = new File(fileName);
        boolean fileExists = file.exists();
        System.out.println("File: " + fileName + (fileExists ? "exists" : "does not exists"));

        if(fileExists){
            System.out.println("Deleting file: " + fileName);
            fileExists = !file.delete();
        }

        if(!fileExists){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Creating a new file: " + fileName);
            if(file.canWrite()){
                System.out.println("Would write to file here");
            }
        }

    }

    private static void usePath(String fileName){
        Path path = Path.of(fileName);
        boolean fileExists = Files.exists(path);
        System.out.println("File: " + fileName + (fileExists ? "exists" : "does not exists"));

        if(fileExists){
            System.out.println("Deleting file: " + fileName);
            try {
                Files.delete(path);
                fileExists = false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        if(!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Creating a new file: " + fileName);
            if(Files.isWritable(path)){
                try {
                    Files.writeString(path, """
                            Okay finally i have my first
                            string on my file lets see what happen
                            if i just write down here.
                            """);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Would write to file here");
            }
        }

    }
}