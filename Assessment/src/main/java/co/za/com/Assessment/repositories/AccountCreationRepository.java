package co.za.com.Assessment.repositories;

import co.za.com.Assessment.pojo.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestController
public interface AccountCreationRepository extends CrudRepository<Account, Integer> {

    @Query(value = "SELECT * FROM tbl_account a WHERE a.accountno = :accountno", nativeQuery = true)
    public Account findAccountByAccountNo(@Param("accountno") int accountno);

    @Transactional
    @Modifying
    @Query(value = "Update tbl_account a SET a.accountbalance = :accountbalance WHERE a.accountno = :accountno", nativeQuery = true)
    public int updateAccountBalance(@Param("accountno") int accountno, @Param("accountbalance") double accountbalance );

}
