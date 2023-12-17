package subsystem;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.PaymentTransaction;
import entity.payment.RefundTransaction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Map;

/**
 * SOLID: Khó mở rộng khi cần thêm phương thức thanh toán ko cần lấy URL
 * @author ntvu
 */
public interface VnPayInterface {


    String generatePayUrl(int amount, String contents)
            throws  IOException;


    RefundTransaction refund(Map<String, String> req) throws PaymentException, IOException;
    PaymentTransaction
    makePaymentTransaction(Map<String, String> response) throws ParseException;
}
