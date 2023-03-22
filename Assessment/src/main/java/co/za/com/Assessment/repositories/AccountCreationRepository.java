package co.za.com.Assessment.repositories;

import co.za.com.Assessment.pojo.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface AccountCreationRepository extends CrudRepository<Account, Integer> {

    @Query(value = "SELECT * FROM tbl_account a WHERE a.accountno = :accountno", nativeQuery = true)
    public Account findAccountByAccountNo(@Param("accountno") int accountno);
}
