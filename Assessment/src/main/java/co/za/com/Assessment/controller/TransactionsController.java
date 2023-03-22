package co.za.com.Assessment.controller;

import co.za.com.Assessment.exceptions.DataNotFoundException;
import co.za.com.Assessment.pojo.Account;
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

    @Autowired
    private AccountCreationService accountService;

    @RequestMapping(value="/FundAccounts/{accountNo}/{transactionType}/{transactionAmount}", method = RequestMethod.GET)
    @ResponseBody
    public String fundAccount(@PathVariable int accountNo, @PathVariable String transactionType,@PathVariable double transactionAmount){

        Account accountDetails = accountService.findAccountByAccountNo(accountNo);

        if(accountDetails != null){
            System.out.println("Account Number found..");

        }else{
            throw new DataNotFoundException("Account Number does not exists...");
        }

        return "Account";
    }
}
