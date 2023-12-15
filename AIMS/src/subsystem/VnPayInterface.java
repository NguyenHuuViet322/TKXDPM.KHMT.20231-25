package subsystem;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.PaymentTransaction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Map;

/**
 * @author ntvu
 */
public interface VnPayInterface {


    String generatePayUrl(int amount, String contents)
            throws  IOException;


    PaymentTransaction
    makePaymentTransaction(Map<String, String> response) throws ParseException;
}
