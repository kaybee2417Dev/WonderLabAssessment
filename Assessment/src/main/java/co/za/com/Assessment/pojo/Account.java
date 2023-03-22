package co.za.com.Assessment.pojo;

import javax.persistence.*;

@Entity
@Table(name = "tblAccount")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "accountid")
    private int accountId;
    @Column(name = "accountno")
    private int accountNo;
    @Column(name = "accounttype")
    private String accountType;
    @Column(name = "accountname")
    private String accountName;
    @Column(name = "accountbalance")
    private double accountBalance;

    @Column(name = "accountoverdraft")
    private double accountOverdraft;
    @Column(name = "accountstatus")
    private String accountStatus;

    public Account(int accountId, int accountNo, String accountType, String accountName, double accountBalance, String accountStatus, double accountOverdraft) {
        this.accountId = accountId;
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.accountName = accountName;
        this.accountBalance = accountBalance;
        this.accountStatus = accountStatus;
        this.accountOverdraft = accountOverdraft;
    }


    public Account() {

    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAccountNo() {
        return this.accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getAccountOverdraft() {
        return accountOverdraft;
    }

    public void setAccountOverdraft(double accountOverdraft) {
        this.accountOverdraft = accountOverdraft;
    }
}
