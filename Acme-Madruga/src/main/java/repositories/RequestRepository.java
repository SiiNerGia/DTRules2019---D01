
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {

}
