
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Brotherhood;

@Repository
public interface BrotherhoodRepository extends JpaRepository<Brotherhood, Integer> {

	@Query("select a from Brotherhood a where a.id = ?1")
	Brotherhood findById(int id);

	@Query("select a from Brotherhood a where a.userAccount.username = ?1")
	Brotherhood findByUserName(String username);

	@Query("select a from Brotherhood a where a.userAccount.id = ?1")
	Brotherhood findByUserAccountId(int id);
}
