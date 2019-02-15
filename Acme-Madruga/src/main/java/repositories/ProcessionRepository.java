package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Procession;

public interface ProcessionRepository extends JpaRepository<Procession, Integer> {

}
