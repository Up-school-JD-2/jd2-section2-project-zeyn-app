package backupAndRestore;

import application.Application;
import domain.Constant;

import java.io.*;

public class BackUpApplication {
    private String dosyaAdi = Constant.APPLICATION_FILE;
    public BackUpApplication() {
        /*File file = new File(dosyaAdi);
        try {
            if(file.length()>0){
                FileWriter writer = new FileWriter(dosyaAdi);
                writer.write("");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
    public void backUp(String phoneNumber, Application application) {
        try (OutputStream outputStream = new FileOutputStream(dosyaAdi, true)) {
            String line = phoneNumber + ":" + application + "\n";
            outputStream.write(line.getBytes());
            System.out.println("Uygulama yedeklendi.");
        } catch (IOException e) {
            System.out.println("Yedekleme işlemi başarısız oldu: " + e.getMessage());
        }
    }
}
