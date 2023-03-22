package co.za.com.Assessment.services;

import co.za.com.Assessment.pojo.Transactions;
import co.za.com.Assessment.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepo;

    public Transactions saveTransaction(Transactions transaction){
     return   transactionsRepo.save(transaction);
    }

}
