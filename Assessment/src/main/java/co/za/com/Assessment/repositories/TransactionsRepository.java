package co.za.com.Assessment.repositories;

import co.za.com.Assessment.pojo.Transactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface TransactionsRepository extends CrudRepository<Transactions,Integer> {


}
