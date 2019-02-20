package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Procession extends DomainEntity {
	
	
	// Attributes -------------------------------------------------------------
	private String ticker;
	private String title;
	private String description;
	private Date moment;
	private Boolean draftMode;
	
	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^([0-9]{2})(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])(-)([A-Z0-9]{5})$")
	public String getTicker() {
		return ticker;
	}
	
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@NotNull
	@Future
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getMoment() {
		return moment;
	}
	
	
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	

	public Boolean getDraftMode() {
		return draftMode;
	}


	public void setDraftMode(Boolean finalMode) {
		this.draftMode = finalMode;
	}






	// Relationships ----------------------------------------------------------
	private Brotherhood brotherhood;
	private Collection<Request> requests;


	@ManyToOne(optional = false)
	public Brotherhood getBrotherhood() {
		return brotherhood;
	}


	public void setBrotherhood(Brotherhood brotherhood) {
		this.brotherhood = brotherhood;
	}


	@OneToMany
	public Collection<Request> getRequests() {
		return requests;
	}


	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}


	@Override
	public String toString() {
		return "Procession [ticker=" + ticker + ", title=" + title + ", description=" + description + ", moment="
				+ moment + ", draftMode=" + draftMode + ", brotherhood=" + brotherhood + ", requests=" + requests + "]";
	}
}
