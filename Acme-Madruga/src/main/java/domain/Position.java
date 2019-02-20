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
	private String name;

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
