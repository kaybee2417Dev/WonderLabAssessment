package co.za.com.Assessment.controller;

import co.za.com.Assessment.exceptions.DataNotFoundException;
import co.za.com.Assessment.pojo.Account;
import co.za.com.Assessment.services.AccountCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/Accounts")
public class AccountCreationController {

   @Autowired
    private AccountCreationService accountService;

   //Variables declarations
    int accountNo = 0;
    private Account account = new Account();

    //Randow number to generate account No.
    int int_random = ThreadLocalRandom.current().nextInt();

   //default values when accounts get created.
   String accountStatus = "Not Active";
   double accountBalance = 0.0;
    @RequestMapping(value="/createAccount/{accountType}/{accountName}", method = RequestMethod.POST)
    @ResponseBody
    public Account createccount(@PathVariable String accountType, @PathVariable String accountName){

        //differentiate cheque's and Savings accounts.. Savings will start with a 01 and Cheques start with 05
        if(accountType.equalsIgnoreCase("cheque") || accountType.equalsIgnoreCase("cheques")){
            accountNo = 05 + getRandomNumberString();
        }else{
            accountNo = 01 +getRandomNumberString();
        }

        //Set account values
        account.setAccountNo(accountNo);
        account.setAccountStatus(accountStatus);
        account.setAccountBalance(accountBalance);
        account.setAccountName(accountName);
        account.setAccountType(accountType);

        //Store an account
        Account accounts = accountService.createAccount(account);

        //Valid account creation
        if(accounts != null)
        {
            //return account details.
            return accounts;
        }else{
            //Exception handling if account not created...
            System.out.println("Not saved..");
            throw new DataNotFoundException("Account not created...");
        }
    }


    //Methods to generate Random Numbers for an account Number
    public static int getRandomNumberString() {
        // It will generate 2 digit random Number.
        // from 0 to 99
        Random rnd = new Random();
        int number = rnd.nextInt(99);

        return number;
    }
}
