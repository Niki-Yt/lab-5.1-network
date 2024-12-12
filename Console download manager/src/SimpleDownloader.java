import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class SimpleDownloader {
    public static void main(String[] args) {
        String fileURL = "https://www.learningcontainer.com/wp-content/uploads/2020/04/sample-text-file.txt";
        String savePath = "downloaded_file.txt";

        try {
            URL url = new URL(fileURL);

            URLConnection connection = url.openConnection();

            int fileSize = connection.getContentLength();
            System.out.println("Розмір файлу: " + fileSize + " bytes");

            try (InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                int totalBytesRead = 0;

                System.out.println("Початок завантаження...");

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;
                    System.out.printf("\rЗавантажено: %d/%d bytes", totalBytesRead, fileSize);
                }

                System.out.println("\nУспішно завантажено!");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}