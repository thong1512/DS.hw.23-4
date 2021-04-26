import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Server {
    public static void main(String[] args) {
        int port = 8080;
        String path = "C:/Users/TERA/Desktop/java/distrubuted_system/23_04/advertising.csv";
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(port);
            while (true) {
                Socket client = socket.accept();
                DataOutputStream streamOut = new DataOutputStream(client.getOutputStream());
                DataInputStream streamIn = new DataInputStream(client.getInputStream());

                ArrayList<Advertising> datas = ReadCSVFile.readCsv(path);
                for (Advertising data : datas) {
                    streamOut.writeUTF(data.toString());
                }
                streamOut.writeUTF(".bye");

                String line = streamIn.readUTF();
                Advertising ads = new Advertising();
                try {
                    StringTokenizer tokens = new StringTokenizer(line, ",");
                    ads.setTv(Double.parseDouble(tokens.nextToken()));
                    ads.setRadio(Double.parseDouble(tokens.nextToken()));
                    ads.setNewspaper(Double.parseDouble(tokens.nextToken()));
                    ads.setSales(Double.parseDouble(tokens.nextToken()));
                } catch (Exception e) {
                    streamOut.writeUTF("Error occured\n");
                }
                System.out.println(ads);
                ReadCSVFile.writeCSV(path, ads);

                client.close(); // close connecttion with server
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
