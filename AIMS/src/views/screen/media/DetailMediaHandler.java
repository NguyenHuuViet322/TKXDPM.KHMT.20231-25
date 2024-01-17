package views.screen.media;


import controller.MediaController;
import entity.media.Media;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Date;

public class DetailMediaHandler extends BaseScreenHandler {

    @FXML
    private TextField mediaId;

    @FXML
    private TextField mediaType;

    @FXML
    private TextField mediaCategory;

    @FXML
    private TextField mediaPrice;

    @FXML
    private TextField mediaQuantity;

    @FXML
    private TextField mediaTitle;

    @FXML
    private TextField mediaValue;

    @FXML
    private ImageView mediaImage;

    @FXML
    private Button chooseImgBtn;

    @FXML
    private Button saveBtn;

    private Media media;

    private String fileName;

    private Path pathImg;

    private static final String DESTINATION_FOLDER = "assets/images/";

    public DetailMediaHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        show();

        chooseImgBtn.setOnAction(event -> {
            chooseImg();
        });

        saveBtn.setOnMouseClicked( e -> handleSaveButton());
    }

    public DetailMediaHandler(Stage stage, String screenPath, Media media) throws IOException {
        super(stage, screenPath);
        super.setBController(new MediaController());
        this.media = media;
        setMediaInfo();
        chooseImgBtn.setDisable(true);
        show();

        saveBtn.setOnMouseClicked( e -> handleSaveButton());
    }

    public MediaController getBController() {
        return (MediaController) super.getBController();
    }

    public void setMediaInfo() {
//        System.out.println(media);
        mediaId.setText(Integer.toString(media.getId()));
        mediaType.setText(media.getType());
        mediaCategory.setText(media.getCategory());
        mediaPrice.setText(Integer.toString(media.getPrice()));
        mediaQuantity.setText(Integer.toString(media.getValueQuantity()));
        mediaTitle.setText(media.getTitle());
        mediaValue.setText(Integer.toString(media.getValue()));

        File file = new File(media.getImageURL());
        Image image = new Image(file.toURI().toString());
        mediaImage.setImage(image);
    }

    private void handleSaveButton() {
        String id = mediaId.getText();

        //Tạo media
        if(id == null || id.isEmpty()){
            // Kiểm tra tất cả các TextField
            if (!isInputValid() || !isImgNotEmpty(fileName)) {
                showAlert(Alert.AlertType.ERROR, "Error", "Vui lòng điền tất cả các trường.");
            }

            String type = mediaType.getText();
            String category = mediaCategory.getText();
            int price = Integer.parseInt(mediaPrice.getText());
            int quantity = Integer.parseInt(mediaQuantity.getText());
            String title = mediaTitle.getText();
            int value = Integer.parseInt(mediaValue.getText());

            //Lưu ảnh vào assets
            getBController().saveImageToDestination(DESTINATION_FOLDER, fileName, pathImg);

            //Lưu data vào db
            Media newMedia = new Media();
            newMedia.setType(type);
            newMedia.setCategory(category);
            newMedia.setPrice(price);
            newMedia.setQuantity(quantity);
            newMedia.setTitle(title);
            newMedia.setValue(value);
            newMedia.setMediaURL("assets/images/" + fileName);

            getBController().create(newMedia);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Thêm mới sản phẩm thành công!");
        }
        //Sửa media
        else {
            // Kiểm tra tất cả các TextField
            if (!isInputValid()){
                showAlert(Alert.AlertType.ERROR, "Error", "Vui lòng điền tất cả các trường.");
            }

            String type = mediaType.getText();
            String category = mediaCategory.getText();
            int price = Integer.parseInt(mediaPrice.getText());
            int quantity = Integer.parseInt(mediaQuantity.getText());
            String title = mediaTitle.getText();
            int value = Integer.parseInt(mediaValue.getText());

            media.setType(type);
            media.setCategory(category);
            media.setPrice(price);
            media.setQuantity(quantity);
            media.setTitle(title);
            media.setValue(value);;

            getBController().update(media);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Cập nhật sản phẩm thành công!");
        }

        closeWindow();
    }

    private void chooseImg(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            // Hiển thị hình ảnh trong ImageView
            Image image = new Image(selectedFile.toURI().toString());
            mediaImage.setImage(image);

            // Lưu lại originName và path của img
            fileName = selectedFile.getName();
            pathImg = selectedFile.toPath();
        }
    }

    // Phương thức kiểm tra xem tất cả các TextField đã được nhập liệu chưa
    private boolean isInputValid() {
        return isTextFieldNotEmpty(mediaType) &&
                isTextFieldNotEmpty(mediaCategory) &&
                isTextFieldNotEmpty(mediaPrice) &&
                isTextFieldNotEmpty(mediaQuantity) &&
                isTextFieldNotEmpty(mediaTitle) &&
                isTextFieldNotEmpty(mediaValue);
    }


    private boolean isTextFieldNotEmpty(TextField textField) {
        return textField.getText() != null && !textField.getText().trim().isEmpty();
    }

    private boolean isImgNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }


    // Phương thức hiển thị thông báo
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void closeWindow() {
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }
}
