
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
import org.hibernate.validator.constraints.URL;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	name;
	private String	middleName;
	private String	surname;
	private String	photo;
	private String	email;
	private String	phoneNumber;
	private String	address;
	private Boolean	isPammer;
	private Boolean	isBanned;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	@NotBlank
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@URL
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	@Email
	@NotBlank
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}
	
	
	public Boolean getIsPammer() {
		return isPammer;
	}

	public void setIsPammer(Boolean isPammer) {
		this.isPammer = isPammer;
	}

	public Boolean getIsBanned() {
		return isBanned;
	}

	public void setIsBanned(Boolean isBanned) {
		this.isBanned = isBanned;
	}






	// Relationships ----------------------------------------------------------
	private UserAccount					userAccount;
	private Collection<MessageBox>		messageBoxes;


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
		return messageBoxes;
	}

	public void setMessageBoxes(Collection<MessageBox> messageBoxes) {
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
