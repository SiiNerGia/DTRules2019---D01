
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Enrol;

@Repository
public interface EnrolRepository extends JpaRepository<Enrol, Integer> {

	@Query("select e from Enrol e where e.brotherhood.id = ?1 and e.member.id = ?2")
	Enrol findEnrolByBrotherhoodAndMemberId(int brotherhoodId, int memberId);

}
