
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.id = ?1")
	Actor findById(int id);

	@Query("select a from Actor a where a.userAccount.username = ?1")
	Actor findByUserName(String username);

	@Query("select a from Actor a where a.userAccount.id = ?1")
	Actor findByUserAccountId(int id);
	
	@Query("select a from Actor a where a.isSpammer = true")
	Collection<Actor> findSpammers();
}
