
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Brotherhood extends Actor {

	// Attributes
	private String				title;
	private Date				establishment;
	private Collection<Url>		pictures;

	// Relationships ----------------------------------------------------------
	//	private Collection<Enrol>		enrols;
	//	private Collection<Procession>	processions;
	//private Area					area;
	private Collection<Float>	floats;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getEstablishment() {
		return this.establishment;
	}

	public void setEstablishment(final Date establishment) {
		this.establishment = establishment;
	}

	@ElementCollection
	@Valid
	public Collection<Url> getPictures() {
		return this.pictures;
	}

	public void setPictures(final Collection<Url> pictures) {
		this.pictures = pictures;
	}

	// RELATIONSHIPS ---------------------------------------------------

	//	@Valid
	//	@OneToMany
	//	public Collection<Enrol> getEnrols() {
	//		return this.enrols;
	//	}
	//
	//	public void setEnrols(final Collection<Enrol> enrols) {
	//		this.enrols = enrols;
	//	}
	//
	//	@Valid
	//	@OneToMany
	//	public Collection<Procession> getProcessions() {
	//		return this.processions;
	//	}
	//
	//	public void setProcessions(final Collection<Procession> processions) {
	//		this.processions = processions;
	//	}

	@Valid
	@OneToMany
	public Collection<Float> getFloats() {
		return this.floats;
	}

	public void setFloats(final Collection<Float> floats) {
		this.floats = floats;
	}

	//	@Valid
	//	@ManyToOne(optional=true)
	//	public Area getArea() {
	//		return this.area;
	//	}
	//
	//	public void setArea(final Area area) {
	//		this.area = area;
	//	}

	@Override
	public String toString() {
		return "Brotherhood [Title = " + this.getTitle() + "]";
	}
}
