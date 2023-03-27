package co.za.com.Assessment.controller;

import co.za.com.Assessment.exceptions.DataNotFoundException;
import co.za.com.Assessment.pojo.Account;
import co.za.com.Assessment.services.AccountCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping(value = "/Accounts")
public class AccountCreationController {

   @Autowired
    private AccountCreationService accountService;

   //Variables declarations
    int accountNo = 0;
    private Account account = new Account();

   //default values when accounts get created.
   double accountBalance = 0.0;
    @RequestMapping(value="/createAccount/{accountType}/{accountName}/{overDraft}", method = RequestMethod.POST)
    @ResponseBody
    public Account createccount(@PathVariable String accountType, @PathVariable String accountName, @PathVariable int overDraft){

        //differentiate cheque's and Savings accounts.. Savings will start with a 1 and Cheques start with 5
        if(accountType.equalsIgnoreCase("cheque") || accountType.equalsIgnoreCase("cheques")){
            accountNo = Integer.parseInt("5" +""+ getRandomNumberString());
        }else{
            accountNo = Integer.parseInt("1" + ""+ getRandomNumberString());
        }

        //Set account values and default Values
        if(accountType.equalsIgnoreCase("savings") || accountType.equalsIgnoreCase("saving")){
            account.setAccountStatus("Not Active");
            account.setAccountOverdraft(0.00);
        }else if(accountType.equalsIgnoreCase("cheques") || accountType.equalsIgnoreCase("cheque")){
            account.setAccountStatus("Active");

            //Set Overdraft for Cheque Account
            if(overDraft==01){
                account.setAccountOverdraft(100000.00);
            }else{
                account.setAccountOverdraft(0.00);
            }

        }else{
            throw new DataNotFoundException("Account Type does not exists...");
        }
        account.setAccountBalance(accountBalance);
        account.setAccountName(accountName);
        account.setAccountType(accountType);
        account.setAccountNo(accountNo);

        //create an account
        Account accounts = accountService.createAccount(account);

        //Valid account creation
        if(accounts != null)
        {
            //return account details.
            return accounts;
        }else{
            //Exception handling if account not created...
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
