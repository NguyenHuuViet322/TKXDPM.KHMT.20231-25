//package subsystem;
//
//import entity.payment.PaymentTransaction;
//import subsystem.vnPay.VnPaySubsystemController;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.Map;
//
///***
// *
// * @author ntvu
// *
// */
//public class VnPaySubsystem implements VnPayInterface {
//
//    /**
//     * Represent the controller of the subsystem.
//     */
//    private VnPaySubsystemController ctrl;
//
//    /**
//     * Initializes a newly created {@code InterbankSubsystem} object so that it
//     * represents an Interbank subsystem.
//     */
//    public VnPaySubsystem() {
//        this.ctrl = new VnPaySubsystemController();
//    }
//
//    /**
//     * Data coupling
//     * Procedural Cohesion
//     */
//    public String generatePayUrl(int amount, String contents) {
//
//        try {
//            return ctrl.generatePayOrderUrl(amount, contents);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     *
//     * Data coupling
//     * Functional cohesion
//     * @param response
//     * @return
//     */
//    public PaymentTransaction makePaymentTransaction(Map<String, String> response){
//
//            return ctrl.makePaymentTransaction(response);
//
//    }
//}
