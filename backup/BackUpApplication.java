package backup;

import application.Application;

import java.io.FileWriter;
import java.io.IOException;

public class BackUpApplication {
    private String dosyaAdi = "Application.txt";
    public BackUpApplication() {
    }
    public void backUp(String phoneNumber, Application application) {
        try (FileWriter fileWriter = new FileWriter(dosyaAdi, true)) {
            String line = phoneNumber + "=" + application + "\n";
            fileWriter.write(line);
            System.out.println("Uygulama yedeklendi.");
        } catch (IOException e) {
            System.out.println("Yedekleme işlemi başarısız oldu: " + e.getMessage());
        }
    }
}
