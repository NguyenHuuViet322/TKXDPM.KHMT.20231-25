package com.example.views.screen.shipping;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import com.example.common.exception.InvalidDeliveryInfoException;
import com.example.controller.PlaceOrderController;
import com.example.entity.order.Order;
import com.example.utils.Configs;
import com.example.views.screen.BaseScreenHandler;
import com.example.views.screen.delivery.DeliveryMethodsScreenHandler;
import com.example.views.screen.invoice.InvoiceScreenHandler;
import com.example.views.screen.popup.PopupScreen;

public class ShippingScreenHandler extends BaseScreenHandler implements Initializable {

    @FXML
    private Label screenTitle;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextField address;

    @FXML
    private TextField instructions;

    @FXML
    private ComboBox<String> province;

    private Order order;

    public ShippingScreenHandler(Stage stage, String screenPath, Order order) throws IOException {
        super(stage, screenPath);
        this.order = order;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        final BooleanProperty firstTime = new SimpleBooleanProperty(true);
        name.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                content.requestFocus();
                firstTime.setValue(false);
            }
        });
        this.province.getItems().addAll(Configs.PROVINCES);
    }

    @FXML
    void handleSubmitDeliveryInfo(MouseEvent event) throws IOException, InterruptedException, SQLException {

        HashMap<String, String> messages = new HashMap<>();
        messages.put("name", name.getText());
        messages.put("phone", phone.getText());
        messages.put("address", address.getText());
        messages.put("instructions", instructions.getText());
        messages.put("province", province.getValue());
        
        PlaceOrderController placeOrderCtrl = getBController();

        if (!placeOrderCtrl.validateContainLetterAndNoEmpty(name.getText())) {
            PopupScreen.error("Name is not valid!");
            return;
        }

        if (!placeOrderCtrl.validatePhoneNumber(phone.getText())) {
            PopupScreen.error("Phone is not valid!");
            return;
        }

        if (!placeOrderCtrl.validateContainLetterAndNoEmpty(address.getText())) {
            PopupScreen.error("Address is not valid!");
            return;
        }

        if (province.getValue() == null) {
            PopupScreen.error("Province is empty!");
            return;
        }

        try {
            getBController().processDeliveryInfo(messages);
        } catch (InvalidDeliveryInfoException e) {
            handleException(e);
        }

        int shippingFees = getBController().calculateShippingFee(order.getAmount());
        order.setShippingFees(shippingFees);
        order.setName(name.getText());
        order.setPhone(phone.getText());
        order.setProvince(province.getValue());
        order.setAddress(address.getText());
        order.setInstruction(instructions.getText());

        navigateToDeliveryMethodsScreen();
    }

    public PlaceOrderController getBController() {
        return (PlaceOrderController) super.getBController();
    }

    private void navigateToDeliveryMethodsScreen() throws IOException {
        BaseScreenHandler deliveryMethodsScreenHandler = new DeliveryMethodsScreenHandler(
                this.stage, Configs.DELIVERY_METHODS_PATH, this.order);
        deliveryMethodsScreenHandler.setPreviousScreen(this);
        deliveryMethodsScreenHandler.setHome
