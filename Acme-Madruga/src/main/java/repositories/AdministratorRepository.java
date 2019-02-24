
package repositories;



import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Brotherhood;


@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
	
	@Query("select admin from Administrator admin where admin.userAccount.id = ?1")
	Administrator findByUserAccountId(int id);
	
	@Query("select avg(b.enrols.size), min(b.enrols.size), max(b.enrols.size), stddev(b.enrols.size) from Brotherhood b")
	Object[] query1();
	
	@Query("select b1 from Brotherhood b1 where b1.enrols.size = (Select max(b2.enrols.size) from Brotherhood b2)")
	Collection<Brotherhood> query2();
	
	@Query("select b1 from Brotherhood b1 where b1.enrols.size = (Select min(b2.enrols.size) from Brotherhood b2)")
	Collection<Brotherhood> query3();
}
