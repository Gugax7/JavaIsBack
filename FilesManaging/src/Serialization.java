import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serialization {

    public static void main(String[] args) {
        Student student = new Student("Carlos Second", 103,19,"Springfield");
        Student student2 = new Student("Antonio Gyro", 102,23, "Itatiba");

        try(FileOutputStream fos = new FileOutputStream("src/main/resources/student.ser");
            ObjectOutputStream out = new ObjectOutputStream(fos)){
            out.writeObject(student);
            out.writeObject(student2);
            System.out.println("Student serialized successfully");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
