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

    private int updateTransaction;

    private int updateAccount;

    private String accountType;

    private Account accountDetails;
    private Transactions transation = new Transactions();
    @Autowired
    private AccountCreationService accountService;

    @RequestMapping(value="/FundAccounts/{accountNo}/{transactionType}/{transactionAmount}", method = RequestMethod.POST)
    @ResponseBody
    public String fundAccount(@PathVariable int accountNo, @PathVariable String transactionType,@PathVariable double transactionAmount){

         accountDetails = findAccountDetailsUsingAccountNo(accountNo);

         if(accountDetails != null){
             accountBalance = accountDetails.getAccountBalance();
             accountStatus = accountDetails.getAccountStatus();
             accountType = accountDetails.getAccountType();

           /*  if(accountStatus.equalsIgnoreCase("Not Active")){
                 throw new  DataNotFoundException("Account Not Active, Please Deposit R1000.00 to Activate your Savings Account");
             }else{*/

                 if(transactionType.equalsIgnoreCase("Deposit")){

                     if(accountType.equalsIgnoreCase("savings") && accountStatus.equalsIgnoreCase("Not Active")){

                         if(transactionAmount < 1000.00){
                             throw new DataNotFoundException("The Minimum amount to Activate a Savings Accounts is + R1000.00");
                         }
                     }

                     accountBalance = accountBalance + transactionAmount;
                     updateAccount = accountService.UpdateAccountBalance(accountNo, accountBalance);

                    // System.out.println("Account No: " + accountDetails.getAccountNo());
                     transation.setTransactionAccountNo(accountNo);
                     transation.setTransactionType(transactionType);
                     transation.setTransactionAmount(transactionAmount);

                     transation = transactionsService.saveTransaction(transation);

                 }else if(transactionType.equalsIgnoreCase("Withdrawal")){

                    if(accountType.equalsIgnoreCase("savings") && accountBalance >= 1000){

                        if(accountBalance >= transactionAmount  ){

                            accountBalance = accountBalance - transactionAmount;
                            updateAccount = accountService.UpdateAccountBalance(accountNo, accountBalance);

                            // System.out.println("Account No: " + accountDetails.getAccountNo());
                            transation.setTransactionAccountNo(accountNo);
                            transation.setTransactionType(transactionType);
                            transation.setTransactionAmount(transactionAmount);

                            transation = transactionsService.saveTransaction(transation);
                        }else{
                            throw new  DataNotFoundException("You have Insufficient fund in your Savings Accounts...");
                        }

                    }else{
                        throw new  DataNotFoundException("Transactions Probited...");
                    }

                 }else{
                     throw new  DataNotFoundException("Transactions Type does not exists...");
                 }
           //  }

         }else{
             throw new  DataNotFoundException("Account Does Not Exist...");
         }


        return "Account";
    }

    public Account findAccountDetailsUsingAccountNo(int accountNo){
         accountDetails = accountService.findAccountByAccountNo(accountNo);

        if(accountDetails != null){
            System.out.println("Account Number found.." + accountDetails.getAccountStatus());

            return accountDetails;
        }else{
            throw new DataNotFoundException("Account Number does not exists...");
        }

    }

}
