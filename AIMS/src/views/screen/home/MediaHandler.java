package views.screen.home;

import common.exception.MediaNotAvailableException;
import controller.AccountController;
import controller.MediaController;
import controller.ViewCartController;
import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.media.Media;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Configs;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.FXMLScreenHandler;
import views.screen.cart.CartScreenHandler;
import views.screen.media.DetailMediaHandler;
import views.screen.popup.PopupScreen;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MediaHandler extends FXMLScreenHandler {

    private static Logger LOGGER = Utils.getLogger(MediaHandler.class.getName());
    @FXML
    protected ImageView mediaImage;
    @FXML
    protected Label mediaTitle;
    @FXML
    protected Label mediaPrice;
    @FXML
    protected Label mediaAvail;
    @FXML
    protected Spinner<Integer> spinnerChangeNumber;
    @FXML
    protected Button addToCartBtn;
    @FXML
    protected Button editBtn;
    @FXML
    protected Button deleteBtn;

    private Media media;
    private AccountController accountController;
    private HomeScreenHandler home;

    public MediaHandler(String screenPath, Media media, HomeScreenHandler home, AccountController acc) throws SQLException, IOException {
        super(screenPath);
        this.media = media;
        this.home = home;
        this.accountController = acc;
//        if (accountController.getAccountController().getLoggedInAccount() == null){
//            editBtn.setVisible(false);
//            deleteBtn.setVisible(false);
//        }
//        else if (accountController.getAccountController().getLoggedInAccount() != null && accountController.getAccountController().getLoggedInAccount().getRole() != 2) {
//            editBtn.setVisible(false);
//            deleteBtn.setVisible(false);
//        }
//        else {
//            editBtn.setVisible(true);
//            deleteBtn.setVisible(true);
//            spinnerChangeNumber.setVisible(false);
//            addToCartBtn.setVisible(false);
//        }

        editBtn.setOnMouseClicked(e -> {
            showDetailMedia();
        });
        deleteBtn.setOnMouseClicked(e -> {
            try {
                deleteProduct();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        addToCartBtn.setOnMouseClicked(event -> {
            try {
                if (spinnerChangeNumber.getValue() > media.getQuantity()) throw new MediaNotAvailableException();
                Cart cart = Cart.getCart();
                // if media already in cart then we will increase the quantity by 1 instead of create the new cartMedia
                CartMedia mediaInCart = home.getBController().checkMediaInCart(media);
                if (mediaInCart != null) {
                    mediaInCart.setQuantity(mediaInCart.getQuantity() + 1);
                } else {
                    CartMedia cartMedia = new CartMedia(media, cart, spinnerChangeNumber.getValue(), media.getPrice());
                    cart.getListMedia().add(cartMedia);
                }

                // subtract the quantity and redisplay
                media.setQuantity(media.getQuantity() - spinnerChangeNumber.getValue());
                mediaAvail.setText(String.valueOf(media.getQuantity()));
                home.getNumMediaCartLabel().setText(String.valueOf(cart.getTotalMedia() + " media"));
                PopupScreen.success("The media " + media.getTitle() + " added to Cart");
            } catch (MediaNotAvailableException exp) {
                try {
                    String message = "Not enough media:\nRequired: " + spinnerChangeNumber.getValue() + "\nAvail: " + media.getQuantity();
                    LOGGER.severe(message);
                    PopupScreen.error(message);
                } catch (Exception e) {
                    LOGGER.severe("Cannot add media to cart: ");
                }

            } catch (Exception exp) {
                LOGGER.severe("Cannot add media to cart: ");
                exp.printStackTrace();
            }
        });
        setMediaInfo();
    }

    private void deleteProduct() throws SQLException {
        home.removeMedia(this.media);
    }

    /**
     * @return Media
     */
    public Media getMedia() {
        return media;
    }


    /**
     * @throws SQLException
     */
    private void setMediaInfo() throws SQLException {
        // set the cover image of media
        File file = new File(media.getImageURL());
        Image image = new Image(file.toURI().toString());
        mediaImage.setFitHeight(160);
        mediaImage.setFitWidth(152);
        mediaImage.setImage(image);

        mediaTitle.setText(media.getTitle());
        mediaPrice.setText(Utils.getCurrencyFormat(media.getPrice()));
        mediaAvail.setText(Integer.toString(media.getQuantity()));
        spinnerChangeNumber.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1)
        );

        setImage(mediaImage, media.getImageURL());
    }

    private void showDetailMedia()
        {
            Stage newWindow = new Stage();
            DetailMediaHandler detailmediaScreen = null;
            try {
                detailmediaScreen = new DetailMediaHandler(newWindow, Configs.DETAIL_MEDIA_PATH, this.media);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            detailmediaScreen.setBController(new MediaController());
        }
}
