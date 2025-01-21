import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.stream.Stream;

public class DirListing {
    public static void main(String[] args) {

        Path path = Path.of("");
        System.out.println(path.toAbsolutePath());

        try(Stream<Path> paths = Files.list(path)){
            paths
                    .map(DirListing::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("------------------------------");

        try(Stream<Path> paths = Files.walk(path,1)){
            paths
                    .map(DirListing::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("------------------------------");

        try(Stream<Path> paths = Files.find(path,Integer.MAX_VALUE,
                (p,attr) -> attr.isRegularFile() && attr.size() > 2000)){
            paths
                    .sorted(Comparator.comparing(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }))
                    .map(DirListing::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("================ Directory Stream =================");
        try(var dirs = Files.newDirectoryStream(path, "*.txt")){
            dirs.forEach(d -> System.out.println(DirListing.listDir(d)));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private static String listDir(Path path){
        try {
            boolean isDir = Files.isDirectory(path);
            FileTime dateField = Files.getLastModifiedTime(path);
            LocalDateTime modDt = LocalDateTime.ofInstant(
                    dateField.toInstant(), ZoneId.systemDefault()
            );
            return "%tD %tT %-5s %12s %s".formatted(modDt,modDt,(isDir ? "<DIR>" : ""),(isDir ? "" : Files.size(path)), path);
        }catch (IOException e){
            e.printStackTrace();
            return path.toString();
        }
    }

}
