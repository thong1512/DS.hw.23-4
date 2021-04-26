import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CopyFile {
    public static void main(String[] args) {
        Scanner reader = null;
        FileWriter writer = null;
        
        try {
            reader = new Scanner(new File("C:/Users/TERA/Desktop/java/distrubuted_system/23_04/name.txt"));
            writer = new FileWriter(new File("C:/Users/TERA/Desktop/java/distrubuted_system/23_04/name_cp.txt"), false);
            String data = null;
            while (reader.hasNextLine()) {
                data = reader.nextLine();
                System.out.println(data);
                writer.write(data + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.close();
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
