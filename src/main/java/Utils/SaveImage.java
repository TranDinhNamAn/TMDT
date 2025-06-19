package Utils;

import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.UUID;

public class SaveImage {

    public String saveImage(Part filePart, String uploadPath, String imageName) {
        try {
            Path imageDir = Paths.get(uploadPath);
            if (!Files.exists(imageDir)) {
                Files.createDirectories(imageDir);
            }
            Path targetPath = imageDir.resolve(imageName);

            try (InputStream inputStream = filePart.getInputStream()) {
                Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }

            return imageName;
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu ảnh: " + e.getMessage());
            return null;
        }
    }
}
