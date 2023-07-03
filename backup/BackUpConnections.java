package backup;

import connection.Connection;
import java.io.FileWriter;
import java.io.IOException;

public class BackUpConnections {
    private String dosyaAdi = "Connection.txt";
    public BackUpConnections() {
    }
    public void backUp(String phoneNumber, Connection connection) {
        try (FileWriter fileWriter = new FileWriter(dosyaAdi, true)) {
            String line = phoneNumber + "=" + connection + "\n";
            fileWriter.write(line);
            System.out.println("Kişilerim yedeklendi.");
        } catch (IOException e) {
            System.out.println("Yedekleme işlemi başarısız oldu: " + e.getMessage());
        }
    }
}