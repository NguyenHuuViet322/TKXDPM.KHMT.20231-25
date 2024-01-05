package views.screen.order;

import controller.OrderController;
import entity.order.Order;
import entity.order.entities.DetailResponse;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class OrderDetailHandler extends BaseScreenHandler {

    @FXML
    private TextField transactionId;

    @FXML
    private TextField bankCode;

    @FXML
    private TextField amount;

    @FXML
    private TextField payDate;

    @FXML
    private TextField transactionType;

    @FXML
    private TextField transactionStatus;

    @FXML
    private TextField orderInfo;

    @FXML
    private TextField promotionCode;

    @FXML
    private TextField promotionAmount;

    private Order order;

    public OrderDetailHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public OrderDetailHandler(Stage stage, String screenPath, Order order) throws IOException {
        super(stage, screenPath);
        super.setBController(new OrderController());
        this.order = order;
    }
    public OrderController getBController() {
        return (OrderController) super.getBController();
    }


    public void initScreen(DetailResponse response) {
        transactionId.setText(response.getTransactionId());
        bankCode.setText(response.getBankCode());
        amount.setText(response.getAmount());
        String text = response.getPayDate();
        payDate.setText(text);
        transactionType.setText(response.getTransactionType());
        transactionStatus.setText(response.getTransactionStatus());
        orderInfo.setText(response.getOrderInfo());
        promotionCode.setText(response.getPromotionCode());
        promotionAmount.setText(response.getPromotionAmount());
    }

    public void show(BaseScreenHandler prevScreen) throws SQLException, IOException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Order Detail Screen");

        initScreen(getBController().getDetailOrder(order));
        show();
    }

    public void showScreen() throws IOException {

        stage.setTitle("Order Detail Screen");

        initScreen(getBController().getDetailOrder(order));
        show();
    }
}
