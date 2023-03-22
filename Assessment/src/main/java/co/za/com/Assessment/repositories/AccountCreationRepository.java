package co.za.com.Assessment.repositories;

import co.za.com.Assessment.pojo.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface AccountCreationRepository extends CrudRepository<Account, Integer> {
}
