import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserialization {

    public static void main(String[] args) {
        try(FileInputStream fis = new FileInputStream("src/main/resources/student.ser");
            ObjectInputStream in = new ObjectInputStream(fis)){

            Student student = (Student)in.readObject();
            Student student2 = (Student)in.readObject();
            System.out.println("Deserialization was successful");

            student.displayStudentInfo();
            student2.displayStudentInfo();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
