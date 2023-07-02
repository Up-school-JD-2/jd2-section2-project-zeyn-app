package logging;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class CallRecords {
    // Buraya arama yapıldığında, yapılan arama saati, aranan numarayı kaydet
    private static final String LOG_DOSYA_ADI = "CallRecords.txt";

    public static void logKaydet(String phoneNumber) {
        try (FileWriter fileWriter = new FileWriter(LOG_DOSYA_ADI, true)) {
            LocalDate localDate = LocalDate.now();
            fileWriter.write(localDate + " - " + phoneNumber + "\n");
            // System.out.println("Arama loglandı: " + phoneNumber);
            /*System.out.println("Hata mesajı: " + phoneNumber);*/
        } catch (IOException e) {
            System.out.println("Arama loglama işlemi başarısız oldu: " + e.getMessage());
        }
    }
}
