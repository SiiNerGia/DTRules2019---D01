
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Position extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String	spanishName;
	private String	englishName;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSpanishName() {
		return this.spanishName;
	}

	public void setSpanishName(final String spanishName) {
		this.spanishName = spanishName;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getEnglishName() {
		return this.englishName;
	}

	public void setEnglishName(final String englishName) {
		this.englishName = englishName;
	}


	// Relationships ----------------------------------------------------------
	private Collection<Enrol>	enrol;


	@ManyToMany
	public Collection<Enrol> getEnrol() {
		return this.enrol;
	}

	public void setEnrol(final Collection<Enrol> enrol) {
		this.enrol = enrol;
	}
}
