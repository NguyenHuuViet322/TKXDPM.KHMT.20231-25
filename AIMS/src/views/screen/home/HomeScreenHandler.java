package com.example.views.screen.home;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import common.exception.ViewCartException;
import controller.HomeController;
import controller.ViewCartController;
import entity.cart.Cart;
import entity.media.Media;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.cart.CartScreenHandler;

public class HomeScreenHandler extends BaseScreenHandler implements Initializable {

    private static final Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());

    @FXML
    private Label numMediaInCart;

    @FXML
    private ImageView aimsImage;

    @FXML
    private ImageView cartImage;

    @FXML
    private VBox vboxMedia1;

    @FXML
    private VBox vboxMedia2;

    @FXML
    private VBox vboxMedia3;

    @FXML
    private HBox hboxMedia;

    @FXML
    private SplitMenuButton splitMenuBtnSearch;

    private List<MediaHandler> homeItems;

    public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public Label getNumMediaCartLabel() {
        return this.numMediaInCart;
    }

    public HomeController getBController() {
        return (HomeController) super.getBController();
    }

    @Override
    public void show() {
        numMediaInCart.setText(String.valueOf(Cart.getCart().getListMedia().size()) + " media");
        super.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setBController(new HomeController());

        try {
            initializeMedia();
            initializeEventHandlers();
            addMediaHome(homeItems);
            addMenuItems();
        } catch (SQLException | IOException e) {
            handleInitializationError(e);
        }
    }

    private void initializeMedia() throws SQLException, IOException {
        List<Media> mediaList = getBController().getAllMedia();
        homeItems = new ArrayList<>();
        
        for (Media media : mediaList) {
            MediaHandler mediaHandler = new MediaHandler(Configs.HOME_MEDIA_PATH, media, this);
            homeItems.add(mediaHandler);
        }
    }

    private void initializeEventHandlers() {
        aimsImage.setOnMouseClicked(e -> addMediaHome(homeItems));
        cartImage.setOnMouseClicked(e -> handleCartImageClick());
    }

    private void handleCartImageClick() {
        try {
            var cartScreen = new CartScreenHandler(stage, Configs.CART_SCREEN_PATH);
            cartScreen.setHomeScreenHandler(this);
            cartScreen.setBController(new ViewCartController());
            cartScreen.show(this);
        } catch (IOException | SQLException e) {
            throw new ViewCartException(Arrays.toString(e.getStackTrace()).replaceAll(", ", "\n"));
        }
    }

    private void addMenuItems() {
        addMenuItem(0, "Book", splitMenuBtnSearch);
        addMenuItem(1, "DVD", splitMenuBtnSearch);
        addMenuItem(2, "CD", splitMenuBtnSearch);
    }

    public void setImage() {
        setImageViewImage(aimsImage, "Logo.png");
        setImageViewImage(cartImage, "cart.png");
    }

    private void setImageViewImage(ImageView imageView, String imageName) {
        Path imagePath = Paths.get(Configs.IMAGE_PATH, imageName);
        File file = imagePath.toFile();
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    public void addMediaHome(List<MediaHandler> items) {
        hboxMedia.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();
        });

        for (VBox vBox : hboxMedia.getChildren()) {
            while (vBox.getChildren().size() < 3 && !items.isEmpty()) {
                MediaHandler mediaHandler = items.remove(0);
                vBox.getChildren().add(mediaHandler.getContent());
            }
        }
    }

    private void addMenuItem(int position, String text, MenuButton menuButton) {
        MenuItem menuItem = new MenuItem();
        Label label = new Label();
        label.prefWidthProperty().bind(menuButton.widthProperty().subtract(31));
        label.setText(text);
        label.setTextAlignment(TextAlignment.RIGHT);
        menuItem.setGraphic(label);
        menuItem.setOnAction(e -> handleMenuItemClick(text));
        menuButton.getItems().add(position, menuItem);
    }

    private void handleMenuItemClick(String category) {
        hboxMedia.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();
        });

        List<MediaHandler> filteredItems = new ArrayList<>();
        homeItems.forEach(mediaHandler -> {
            if (mediaHandler.getMedia().getTitle().toLowerCase().startsWith(category.toLowerCase())) {
                filteredItems.add(mediaHandler);
            }
        });

        addMediaHome(filteredItems);
    }

    private void handleInitializationError(Exception e) {
        LOGGER.info("Initialization error: " + e.getMessage());
        e.printStackTrace();
    }
}
