
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

	@Query("select a from Member a where a.userAccount.id = ?1")
	Member findByUserAccountId(int id);

	@Query("select a from Member a where a.id = ?1")
	Member findById(int id);

	@Query("select a from Member a where a.userAccount.username = ?1")
	Member findByUserName(String username);

	@Query("select e.member from Enrol e join e.brotherhood b where b.id = ?1")
	Collection<Member> findByBrotherhood(int id);

}
