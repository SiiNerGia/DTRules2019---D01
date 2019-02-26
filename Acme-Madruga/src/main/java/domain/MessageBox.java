
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class MessageBox extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String	name;
	private Boolean	isSystemBox;


	// Getters and Setters -----------------------------------------------------------
	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Boolean getIsSystemBox() {
		return this.isSystemBox;
	}

	public void setIsSystemBox(final Boolean isSystemBox) {
		this.isSystemBox = isSystemBox;
	}


	// Attributes -------------------------------------------------------------
	private Collection<Message>	messages;


	@ManyToMany
	public Collection<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(final Collection<Message> messages) {
		this.messages = messages;
	}

	// Methods -----------------------------------------------------------
	public void addMessage(final Message message) {
		this.messages.add(message);
	}

	public void deleteMessage(final Message message) {
		if (!this.messages.contains(message))
			throw new RuntimeException("Error: The message does not exist");
		this.messages.remove(message);
	}

	@Override
	public String toString() {
		return "MessageBox [name=" + this.name + "]";
	}

}
