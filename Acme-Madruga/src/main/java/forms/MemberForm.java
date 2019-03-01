
package forms;

import java.util.Collection;

import security.UserAccount;
import domain.Dropout;
import domain.Enrol;
import domain.Finder;
import domain.MessageBox;
import domain.Request;

public class MemberForm {

	private int						id;
	private String					address;
	private Collection<Dropout>		dropouts;
	private String					email;
	private Collection<Enrol>		enrols;
	private Finder					finder;
	private Collection<MessageBox>	messageBoxes;
	private String					middleName;
	private String					name;
	private String					phoneNumber;
	private String					photo;
	private Collection<Request>		requests;
	private String					surname;
	private Boolean					isBanned;
	private Boolean					isSpammer;
	private UserAccount				userAccount;
	private String					username;


	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public Collection<Dropout> getDropouts() {
		return this.dropouts;
	}

	public void setDropouts(final Collection<Dropout> dropouts) {
		this.dropouts = dropouts;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Collection<Enrol> getEnrols() {
		return this.enrols;
	}

	public void setEnrols(final Collection<Enrol> enrols) {
		this.enrols = enrols;
	}

	public Finder getFinder() {
		return this.finder;
	}

	public void setFinder(final Finder finder) {
		this.finder = finder;
	}

	public Collection<MessageBox> getMessageBoxes() {
		return this.messageBoxes;
	}

	public void setMessageBoxes(final Collection<MessageBox> messageBoxes) {
		this.messageBoxes = messageBoxes;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	public Collection<Request> getRequests() {
		return this.requests;
	}

	public void setRequests(final Collection<Request> requests) {
		this.requests = requests;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public Boolean getIsBanned() {
		return this.isBanned;
	}

	public void setIsBanned(final Boolean isBanned) {
		this.isBanned = isBanned;
	}

	public Boolean getIsSpammer() {
		return this.isSpammer;
	}

	public void setIsSpammer(final Boolean isSpammer) {
		this.isSpammer = isSpammer;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "MemberForm [id=" + this.id + ", address=" + this.address + ", dropouts=" + this.dropouts + ", email=" + this.email + ", enrols=" + this.enrols + ", finder=" + this.finder + ", messageBoxes=" + this.messageBoxes + ", middleName="
			+ this.middleName + ", name=" + this.name + ", phoneNumber=" + this.phoneNumber + ", photo=" + this.photo + ", requests=" + this.requests + ", surname=" + this.surname + ", isBanned=" + this.isBanned + ", isSpammer=" + this.isSpammer
			+ ", userAccount=" + this.userAccount + ", username=" + this.username + "]";
	}
}
