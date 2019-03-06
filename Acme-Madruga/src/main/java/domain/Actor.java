
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	name;
	private String	username;
	private String	middleName;
	private String	surname;
	private String	photo;
	private String	email;
	private String	phoneNumber;
	private String	address;
	private Boolean	isSpammer;
	private Boolean	isBanned;
	private Double	score;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	@Email
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public Boolean getIsSpammer() {
		return this.isSpammer;
	}

	public void setIsSpammer(final Boolean isSpammer) {
		this.isSpammer = isSpammer;
	}

	public Boolean getIsBanned() {
		return this.isBanned;
	}

	public void setIsBanned(final Boolean isBanned) {
		this.isBanned = isBanned;
	}

	public Double getScore() {
		return this.score;
	}

	public void setScore(final Double score) {
		this.score = score;
	}


	// Relationships ----------------------------------------------------------
	private UserAccount				userAccount;
	private Collection<MessageBox>	messageBoxes;


	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@NotNull
	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<MessageBox> getMessageBoxes() {
		return this.messageBoxes;
	}

	public void setMessageBoxes(final Collection<MessageBox> messageBoxes) {
		this.messageBoxes = messageBoxes;
	}

	// Other Methods ------------------------------------------------------
	public MessageBox getMessageBox(final String name) {
		final MessageBox result = null;
		for (final MessageBox box : this.getMessageBoxes())
			if (box.getName().equals(name))
				return box;
		return result;
	}

	@Override
	public String toString() {
		return "Actor [name=" + this.name + ", middleName=" + this.middleName + ", surname=" + this.surname + "]";
	}

}
