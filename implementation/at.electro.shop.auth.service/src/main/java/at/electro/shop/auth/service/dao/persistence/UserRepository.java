package at.electro.shop.auth.service.dao.persistence;

import at.electro.shop.auth.service.dao.persistence.models.AccountEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<AccountEntity, Long> {

  AccountEntity findByUsername(String username);

  @Query(value = "SELECT r.name FROM Role r, Account a, Account_Roles ar WHERE a.username = ?1 AND a.id = ar.accountId AND r.id = ar.roleId")
  List<String> getRoles(String username);
}
