package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Position extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String spanishName;
	private String englishName;
	
	@NotBlank
	public String getSpanishName() {
		return spanishName;
	}

	public void setSpanishName(String spanishName) {
		this.spanishName = spanishName;
	}

	@NotBlank
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}




	// Relationships ----------------------------------------------------------
	private Collection<Enrol> enrol;

	@ManyToMany
	public Collection<Enrol> getEnrol() {
		return enrol;
	}

	public void setEnrol(Collection<Enrol> enrol) {
		this.enrol = enrol;
	}	
}
