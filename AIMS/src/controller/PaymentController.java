package controller;

import common.exception.PaymentException;
import common.exception.TransactionNotDoneException;
import common.exception.UnrecognizedException;
import entity.cart.Cart;
import subsystem.VnPayInterface;
import subsystem.vnPay.VnPaySubsystemController;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.Map;

/**
 * This {@code PaymentController} class control the flow of the payment process
 * in our AIMS Software.
 *
 * @author hieud
 */
public class PaymentController extends BaseController {


    /**
     * Represent the Interbank subsystem
     */
    private VnPayInterface vnPayService;

    //Control Coupling
    public Map<String, String> makePayment(Map<String, String> res, int orderId) {
        Map<String, String> result = new Hashtable<String, String>();

        try {

            var trans = this.vnPayService.makePaymentTransaction(res);
            trans.save(orderId);

            result.put("RESULT", "PAYMENT SUCCESSFUL!");
            result.put("MESSAGE", "You have succesffully paid the order!");
        } catch (PaymentException | UnrecognizedException ex) {
            result.put("MESSAGE", ex.getMessage());
            result.put("RESULT", "PAYMENT FAILED!");

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * Gen url thanh toán vnPay
     * @param amount
     * @param content
     * @return
     */

    //Functional Cohesion
    //Data Coupling
    public String getUrlPay(int amount, String content){

        String url = null;
        try {
            url = this.vnPayService.generatePayUrl(amount, content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

    //Functional Cohesion
    //Control Coupling
    public void emptyCart() {
        Cart.getCart().emptyCart();
    }
}