
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "`float`")
public class Float extends DomainEntity {

	// Attributes

	private String			title;
	private String			description;
	private Collection<Url>	pictures;


	// Getters & setters

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@ElementCollection
	@Valid
	public Collection<Url> getPictures() {
		return this.pictures;
	}

	public void setPictures(final Collection<Url> pictures) {
		this.pictures = pictures;
	}

}
