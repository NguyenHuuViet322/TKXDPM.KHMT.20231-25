package entity.payment;

import entity.db.AIMSDB;

import java.sql.*;
import java.util.Date;

public class PaymentTransaction {
    private String errorCode;
    private String transactionId;
    private String transactionContent;
    private int amount;
    private Integer orderID;
    private Date createdAt;

    private String tmnCode;
    private String txnRef;

    public PaymentTransaction(String errorCode, String transactionId, String transactionContent,
                              int amount, Date createdAt, String tmnCode, String txnRef) {
        super();
        this.errorCode = errorCode;


        this.transactionId = transactionId;
        this.transactionContent = transactionContent;
        this.amount = amount;
        this.createdAt = createdAt;
        this.tmnCode = tmnCode;
        this.txnRef = txnRef;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Date getCreatedAt(){
        return createdAt;
    }

    public String GetTmnCode() {
        return tmnCode;
    }

    public String GetTxnRef(){
        return txnRef;
    }
    /**
     * @return String
     */
    public String getErrorCode() {
        return errorCode;
    }

    public String getTransactionContent() {
        return transactionContent;
    }

    public void save(int orderId) throws SQLException {
        this.orderID = orderId;
        Statement stm = AIMSDB.getConnection().createStatement();
        String query = "INSERT INTO PaymentTransaction ( orderID, createAt, content) " +
                "VALUES ( ?, ?, ?)";
        try (PreparedStatement preparedStatement = AIMSDB.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, orderID);
            preparedStatement.setDate(2, new java.sql.Date(createdAt.getTime()));
            preparedStatement.setString(3,transactionContent );

            preparedStatement.executeUpdate();
        }
    }

}
