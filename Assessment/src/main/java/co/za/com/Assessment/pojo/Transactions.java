package co.za.com.Assessment.pojo;

import javax.persistence.*;

@Entity
@Table(name = "tblTransaction")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "transactionid")
    private int transactionID;
    @Column(name = "transactionaccountno")
    private int transactionAccountNo;
    @Column(name = "transactiontype")
    private String transactionType;
    @Column(name = "transactionamount")
    private double transactionAmount;

    public Transactions(int transactionID, int transactionAccountNo, String transactionType, double transactionAmount) {
        this.transactionID = transactionID;
        this.transactionAccountNo = transactionAccountNo;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
    }

    public Transactions() {

    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getTransactionAccountNo() {
        return transactionAccountNo;
    }

    public void setTransactionAccountNo(int transactionAccountNo) {
        this.transactionAccountNo = transactionAccountNo;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
