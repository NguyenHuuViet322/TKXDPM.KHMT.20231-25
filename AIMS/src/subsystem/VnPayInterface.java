package subsystem;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.PaymentTransaction;

import java.text.ParseException;
import java.util.Map;

/**
 * @author ntvu
 */
public interface VnPayInterface {


    public abstract String generatePayUrl(int amount, String contents)
            throws PaymentException, UnrecognizedException;


    public PaymentTransaction
    makePaymentTransaction(Map<String, String> response) throws ParseException;
}
