package co.za.com.Assessment.pojo;

public class Account {
    private int accountId;
    private int accountNo;
    private String accountType;
    private String accountName;
    private double accountBalance;

    public Account(int accountId, int accountNo, String accountType, String accountName, double accountBalance) {
        this.accountId = accountId;
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.accountName = accountName;
        this.accountBalance = accountBalance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAccountNo() {
        return accountNo;
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
}
