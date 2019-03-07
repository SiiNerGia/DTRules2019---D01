
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {

	@Query("select r from Request r where r.assignedRow=?1 and r.assignedColumn=?2")
	Collection<Request> findRequestByRowAndColumn(int assignedRow, int assignedColumn);

}
