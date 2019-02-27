
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;
import domain.Procession;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	@Query("select p from Procession p where p.moment <= ?1")
	Collection<Procession> filterByMaxDate(Date maxDate);

	@Query("select p from Procession p where p.moment >= ?1")
	Collection<Procession> filterByMinDate(Date minDate);

	@Query("select p from Procession p where p.title LIKE ?1 or p.description LIKE ?1 or p.ticker like ?1")// or p.area.name like ?1
	Collection<Procession> filterByKeyword(String keyword);

//	@Query("select p from Procession p where p.area.id = ?1")
//	Collection<Procession> filterByArea(int areaId);
//TODO Esperar a que este area
}
