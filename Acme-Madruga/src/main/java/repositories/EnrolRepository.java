package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Enrol;

@Repository
public interface EnrolRepository extends JpaRepository<Enrol, Integer> {

}
