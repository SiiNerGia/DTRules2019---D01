
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private Date				moment;
	private String				subject;
	private String				body;
	private String				priority;
	private Boolean				isNotification;
	private Collection<String>	tags;


	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@NotBlank
	@Pattern(regexp = "^(LOW)|(MEDIUM)|(HIGH)$")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPriority() {
		return this.priority;
	}

	public void setPriority(final String priority) {
		this.priority = priority;
	}

	public Boolean getIsNotification() {
		return this.isNotification;
	}

	public void setIsNotification(final Boolean isNotification) {
		this.isNotification = isNotification;
	}

	@ElementCollection(targetClass = String.class)
	public Collection<String> getTags() {
		return this.tags;
	}

	public void setTags(final Collection<String> tags) {
		this.tags = tags;
	}


	// Relationships ----------------------------------------------------------
	private Actor					sender;
	private Collection<Actor>		recipients;
	private Collection<MessageBox>	messageBoxes;


	@OneToOne(optional = true)
	public Actor getSender() {
		return this.sender;
	}

	public void setSender(final Actor sender) {
		this.sender = sender;
	}

	@NotNull
	@ManyToMany
	public Collection<Actor> getRecipients() {
		return this.recipients;
	}

	public void setRecipients(final Collection<Actor> recipients) {
		this.recipients = recipients;
	}

	@NotNull
	@Valid
	@ManyToMany
	public Collection<MessageBox> getMessageBoxes() {
		return this.messageBoxes;
	}

	public void setMessageBoxes(final Collection<MessageBox> messageBoxes) {
		this.messageBoxes = messageBoxes;
	}

	@Override
	public String toString() {
		return "Message [sender=" + this.sender + ", recipient=" + this.recipients + ", moment=" + this.moment + ", subject=" + this.subject + ", priority=" + this.priority + ", tags=" + this.tags + ", messageBoxes=" + this.messageBoxes + "]";
	}

}
