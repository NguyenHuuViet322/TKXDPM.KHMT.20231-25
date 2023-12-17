package views.screen.payment;

import controller.PaymentController;
import entity.invoice.Invoice;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import subsystem.vnPay.Config;
import utils.Configs;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class PaymentScreenHandler extends BaseScreenHandler {

    private Invoice invoice;

    @FXML
    private Label pageTitle;

    @FXML
    private VBox vBox;

    public PaymentScreenHandler(Stage stage, String screenPath, Invoice invoice) throws IOException {
        super(stage, screenPath);
        this.invoice = invoice;

        displayWebView();
    }

    private void displayWebView() {
        PaymentController paymentController = new PaymentController();
        String paymentUrl = paymentController.getUrlPay(invoice.getAmount(), "Thanh toan hoa don AIMS");
        WebView paymentView = new WebView();
        WebEngine webEngine = paymentView.getEngine();
        webEngine.load(paymentUrl);
        webEngine.locationProperty().addListener((observable, oldValue, newValue) -> handleUrlChanged(newValue));
        vBox.getChildren().clear();
        vBox.getChildren().add(paymentView);
    }

    private static Map<String, String> parseQueryString(String query) {
        Map<String, String> params = new HashMap<>();
        if (query != null && !query.isEmpty()) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    params.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return params;
    }

    private void handleUrlChanged(String newValue) {
        if (newValue.contains(Config.vnp_ReturnUrl)) {
            try {
                URI uri = new URI(newValue);
                String query = uri.getQuery();
                Map<String, String> params = parseQueryString(query);
                payOrder(params);
            } catch (URISyntaxException | IOException e) {
                handleException(e);
            }
        }
    }

    private void payOrder(Map<String, String> res) throws IOException {
        PaymentController paymentController = (PaymentController) super.getBController();
        Map<String, String> response = paymentController.makePayment(res, this.invoice.getOrder().getId());
        BaseScreenHandler resultScreen = new ResultScreenHandler(
                this.stage, Configs.RESULT_SCREEN_PATH, response.get("RESULT"), response.get("MESSAGE"));
        paymentController.emptyCart();
        resultScreen.setPreviousScreen(this);
        resultScreen.setHomeScreenHandler(homeScreenHandler);
        resultScreen.setScreenTitle("Result Screen");
        resultScreen.show();
    }

    private void handleException(Exception e) {
        // Handle exception appropriately (log, display message, etc.)
        e.printStackTrace();
    }
}
