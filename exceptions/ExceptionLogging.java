package exceptions;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class ExceptionLogging {
    private static final String LOG_DOSYA_ADI = "ExceptionLogging.txt";

    public static void logKaydet(String errorMessage) {
        try (FileWriter fileWriter = new FileWriter(LOG_DOSYA_ADI, true)) {
            LocalDate localDate = LocalDate.now();
            fileWriter.write(localDate + " - " + errorMessage + "\n");
            System.out.println("Hata loglandı: " + errorMessage);
            System.out.println("Hata mesajı: " + errorMessage);
        } catch (IOException e) {
            System.out.println("Loglama işlemi başarısız oldu: " + e.getMessage());
        }
    }
}
