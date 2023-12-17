package controller;

import common.exception.PaymentException;
import common.exception.PaymentExceptionHolder;
import common.exception.ProcessingException;
import entity.media.Media;
import entity.order.Order;
import org.mockito.internal.matchers.Or;
import subsystem.VnPayInterface;
import subsystem.vnPay.VnPaySubsystemController;
import utils.Utils;
import utils.enums.OrderStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderController extends BaseController {

    private Order order;
    private VnPayInterface vnPayService;
    public OrderController(){
        order = new Order();
        vnPayService = new VnPaySubsystemController();
    }





    /**
     * this method gets all Order in DB and return back to display
     *
     *
     *
     */
   public List<Order> getOrders() {
       return  order.getListOrders();
   }

   public Map<String, String> cancelOrder(Order orderCancel) {
       // handle cancel order


       // cal api refund
       var trans = orderCancel.getPaymentTransaction();
        var requestParams = new HashMap<String, String>();

        requestParams.put("trantype", "02");
        requestParams.put("amount", String.valueOf(trans.getAmount()));
        requestParams.put("order_id", trans.getTxnRef());
        requestParams.put("transactionNo", trans.getTransactionNo());
//       requestParams.put("trans_date", orderCancel.getPayDate());
       requestParams.put("trans_date", Utils.formatDateTime(trans.getCreatedAt(), "yyyyMMddHHmmss"));
       requestParams.put("user", orderCancel.getName());
    var result = new HashMap<String, String>();
       try {

           var refund =  vnPayService.refund(requestParams);
          result.put("RESULT", "REFUND SUCESS");
          result.put("MESSAGE", "REFUND SUCCESS, PLEASE CHECK YOUR BANK");
           orderCancel.setStatus(OrderStatus.Rejected);
           orderCancel.updateStatus(OrderStatus.Rejected, orderCancel.getId());
           return result;


       }
       catch (ProcessingException e){
           result.put("RESULT", "REFUND PROCESSING");
           result.put("MESSAGE", "REFUND PROCESSING BY VNPAYY, PLEASE CHECK YOUR BANK AFTER 3 DAYS");
           orderCancel.setStatus(OrderStatus.Rejected);
           orderCancel.updateStatus(OrderStatus.Rejected, orderCancel.getId());

       }
       catch (PaymentException e){
           result.put("RESULT", "REFUND FAILED");
           result.put("MESSAGE",e.getMessage());
       }
       catch (IOException e) {

           throw new RuntimeException(e);
       }

       return result;

   }

}