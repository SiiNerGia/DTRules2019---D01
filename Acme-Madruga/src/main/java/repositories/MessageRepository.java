
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("select mb.messages from MessageBox mb where mb.id = ?1")
	Collection<Message> findByMessageBox(int messageBoxID);
	
	@Query("select m from Message m where m.sender.id = ?1")
	Collection<Message> findAllBySender(int senderID);

}
