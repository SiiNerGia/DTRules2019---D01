
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Brotherhood;
import domain.Procession;

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

	@Query("select count(r1)*1.0 / (select count(r2)*1.0 from Request r2) from Request r1 group by r1.status")
	Collection<Double> query4();

	@Query("select p from Procession p where p.moment <= ?1")
	Collection<Procession> query5(Date date);

	// Chart queries
	@Query("select count(a) from Actor a where a.isSpammer = true")
	Integer getAllSpammers();

	@Query("select count(a) from Actor a where a.isSpammer = false")
	Integer getAllNotSpammers();

//	@Query("select m from Member m join m.requests r where 0.1 <= (((count(r) where r.status='APPROVED')*1.0) / (count(r)*1.0))")
//	Collection<Member> query7();

	@Query("select p.enrol.size from Position p where p.id = ?1")
	Integer query8(int id);

}
