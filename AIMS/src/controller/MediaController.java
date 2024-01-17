package controller;

import entity.media.Media;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

public class MediaController extends BaseController {

    private Media media;

    public MediaController() {
        media = new Media();
    }

    public void create(Media media) {
        media.create();
    }

    public void update(Media media) {
        media.update();
    }

    // Phương thức để lưu hình ảnh vào thư mục đích
    public void saveImageToDestination(String desFolder, String fileName,Path sourcePath) {
        try {
            // Đường dẫn đầy đủ của tệp trong thư mục đích
            Path destinationPath = Paths.get(desFolder, fileName);

            // Sao chép tệp từ nguồn đến đích
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
