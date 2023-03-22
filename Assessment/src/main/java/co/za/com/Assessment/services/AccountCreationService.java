package co.za.com.Assessment.services;

import co.za.com.Assessment.pojo.Account;
import co.za.com.Assessment.repositories.AccountCreationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCreationService {

    @Autowired
    private AccountCreationRepository accountCreationRepo;

    public Account createAccount(Account account){

     return accountCreationRepo.save(account);
    }

    public Account findAccountByAccountNo(int accountNo){
        return accountCreationRepo.findAccountByAccountNo(accountNo);
    }
}
