import java.io.File;

public class FoldersAndDirectories {

    public static void main(String[] args) {
        String dirPath = "src/main/resources/folder";

        File dir = new File(dirPath);
        if(!dir.exists()){
            if(dir.mkdir()){
                System.out.println("Directory created successfully");
            }
            else{
                System.out.println("Error occurred when creating directory");
            }
        }else{
            System.out.println("Directory already exists");
        }

        String nestedDirPath = "src/main/resources/folder/subdir1/subdir2/subdir3";
        File nestedDir = new File(nestedDirPath);

        if(nestedDir.exists()){
            System.out.println("This directories already exists");
        }
        else{
            System.out.println("Directories dont exists, creating it...");
            if(nestedDir.mkdirs()){
                System.out.println("Directories created successfully");
            }
            else{
                System.out.println("Error on creating directories");
            }
        }

        String resourcePath = "src/main/resources";
        File resourceDir = new File(resourcePath);

        File[] files = resourceDir.listFiles();

        if (files != null) {
            for(File file : files){
                System.out.println(file.getName());
            }
        }

    }

}
