package co.za.com.Assessment.controller;

import co.za.com.Assessment.exceptions.DataNotFoundException;
import co.za.com.Assessment.pojo.Account;
import co.za.com.Assessment.pojo.Transactions;
import co.za.com.Assessment.services.AccountCreationService;
import co.za.com.Assessment.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/Transactions")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    //variables
    private String accountStatus;
    private double accountBalance;

    private double overDraftAmount;

    private int updateAccount;

    private String accountType;

    private Account accountDetails;
    private Transactions transation = new Transactions();

    private Transactions transactions;
    @Autowired
    private AccountCreationService accountService;

    @RequestMapping(value="/FundAccounts/{accountNo}/{transactionType}/{transactionAmount}", method = RequestMethod.POST)
    @ResponseBody
    public String fundAccount(@PathVariable int accountNo, @PathVariable String transactionType,@PathVariable double transactionAmount){

        //get Account details
         accountDetails = findAccountDetailsUsingAccountNo(accountNo);

         if(accountDetails != null){
             accountBalance = accountDetails.getAccountBalance();
             accountStatus = accountDetails.getAccountStatus();
             accountType = accountDetails.getAccountType();
             overDraftAmount = accountDetails.getAccountOverdraft();

                //Cheque Transaction Type
                 if(transactionType.equalsIgnoreCase("Deposit")){

                     if(accountType.equalsIgnoreCase("savings") && accountStatus.equalsIgnoreCase("Not Active")){

                         if(transactionAmount < 1000.00){
                             throw new DataNotFoundException("The Minimum amount to Activate a Savings Accounts is + R1000.00");
                         }
                     }

                     accountBalance = accountBalance + transactionAmount;
                     updateAccount = accountService.UpdateAccountBalance(accountNo, accountBalance);

                 }else if(transactionType.equalsIgnoreCase("Withdrawal")){

                    if(accountType.equalsIgnoreCase("saving") || accountType.equalsIgnoreCase("savings")&& accountBalance >= 1000){

                        if(accountBalance >= transactionAmount  ){
                            accountBalance = accountBalance - transactionAmount;
                            updateAccount = accountService.UpdateAccountBalance(accountNo, accountBalance);
                        }else{
                            throw new  DataNotFoundException("You have Insufficient fund in your Savings Accounts...");
                        }

                    }else if(accountType.equalsIgnoreCase("cheque") || accountType.equalsIgnoreCase("cheques")){

                        if(accountBalance >= transactionAmount || overDraftAmount >= transactionAmount){

                            if(accountBalance >= transactionAmount){
                                accountBalance = accountBalance - transactionAmount;
                                updateAccount = accountService.UpdateAccountBalance(accountNo, accountBalance);
                            }else if(transactionAmount >= accountBalance && overDraftAmount >= transactionAmount ){

                                double amountShortage, splitAmount;

                                amountShortage = transactionAmount - accountBalance;

                                splitAmount = transactionAmount - amountShortage;

                                accountBalance = accountBalance - splitAmount;

                                overDraftAmount = overDraftAmount - amountShortage;

                                updateAccount = accountService.UpdateAccountBalanceAndOverDraft(accountNo, accountBalance, overDraftAmount);

                            }
                        }else{
                            throw new  DataNotFoundException("You have Insufficient fund in your Cheque Accounts...");
                        }

                    }else{
                        throw new  DataNotFoundException("Transactions Probited...");
                    }

                 }else{
                     throw new  DataNotFoundException("Transactions Type does not exists...");
                 }
         }else{
             throw new  DataNotFoundException("Account Does Not Exist...");
         }

        transation.setTransactionAccountNo(accountNo);
        transation.setTransactionType(transactionType);
        transation.setTransactionAmount(transactionAmount);

        transactions = transactionsService.saveTransaction(transation);

        return "Transaction Successful...";
    }


    public Account findAccountDetailsUsingAccountNo(int accountNo){
         accountDetails = accountService.findAccountByAccountNo(accountNo);

        if(accountDetails != null){
            return accountDetails;
        }else{
            throw new DataNotFoundException("Account Number does not exists...");
        }
    }

}
