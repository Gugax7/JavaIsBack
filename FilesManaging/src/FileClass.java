import java.io.File;
import java.io.IOException;

public class FileClass {

    public static void main(String[] args) {
        String filePath = "src/main/resources/created_file.txt";

        File file = new File(filePath);
        File renamed = new File("src/main/resources/renamed_file.txt");
        if(renamed.exists()){
            if(renamed.delete()){
                System.out.println("Old renamed was deleted!");
            }
            else{
                System.out.println("I couldn't delete the old renamed file");
            }
        }

        if(file.exists()){
            System.out.println("File already exists, renaming it...");
                if(file.renameTo(renamed)){
                    System.out.println("File renamed!");
                }
                else {
                    System.out.println("I couldn't rename the file!");
                }
        }
        else{
            System.out.println("File doesn't exist, creating it...");
            try{
                if(file.createNewFile()){
                    System.out.println("File created successfully");
                }
                else{
                    System.out.println("I couldn't create it");
                }
            }catch(IOException e){
                System.err.println("Exception occurred creating the file");
            }
        }
    }
}
