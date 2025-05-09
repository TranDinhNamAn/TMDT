package Utils;

import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.UUID;

public class SaveImage {

    public String saveImage(Part filePart, String uploadPath) {
        try {
            // Lấy tên file gốc và phần mở rộng
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

            // Tạo tên file ngẫu nhiên
            String imageName = UUID.randomUUID().toString() + extension;

            // Tạo thư mục nếu chưa tồn tại
            Path imageDir = Paths.get(uploadPath);
            if (!Files.exists(imageDir)) {
                Files.createDirectories(imageDir);
            }

            // Tạo đường dẫn đến file đích
            Path targetPath = imageDir.resolve(imageName);

            // Lưu file vào thư mục
            try (InputStream inputStream = filePart.getInputStream()) {
                Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }

            return imageName;
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu ảnh: " + e.getMessage());
            return null;
        }
    }
    public boolean uploadFile(InputStream is, String path){
        boolean test = false;
        try{
            byte[] byt = new byte[is.available()];
            is.read();

            FileOutputStream fops = new FileOutputStream(path);
            fops.write(byt);
            fops.flush();
            fops.close();

            test = true;

        }catch(Exception e){
            e.printStackTrace();
        }

        return test;
    }
}
