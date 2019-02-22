
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Configurations;

@Repository
public interface ConfigurationsRepository extends JpaRepository<Configurations, Integer> {

}
