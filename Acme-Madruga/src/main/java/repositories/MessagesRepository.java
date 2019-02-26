
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Message;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Integer> {

}
