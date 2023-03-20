package be.ipl.pfe.repositories;

import be.ipl.pfe.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	@Query(value = "SELECT EXISTS (SELECT * FROM accounts WHERE username = ?1)", nativeQuery = true)
	boolean existsByUsername(String username);

	Account findByUsername(String username);

}
