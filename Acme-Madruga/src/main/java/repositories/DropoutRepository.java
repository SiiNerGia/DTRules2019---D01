package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Dropout;

@Repository
public interface DropoutRepository extends JpaRepository<Dropout, Integer> {

}
