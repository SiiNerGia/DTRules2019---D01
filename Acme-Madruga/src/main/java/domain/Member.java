
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Member extends Actor {

	// Attributes
	// --------------------------------------------------------------------

	// Relationships
	// -----------------------------------------------------------------

	private Finder				finder;

	private Collection<Enrol>	enrols;

	private Collection<Dropout>	dropouts;

	private Collection<Request>	requests;


	@NotNull
	@Valid
	@OneToOne(optional = false)
	public Finder getFinder() {
		return this.finder;
	}

	public void setFinder(final Finder finder) {
		this.finder = finder;
	}

	@Valid
	@OneToMany(mappedBy = "member")
	public Collection<Enrol> getEnrols() {
		return this.enrols;
	}

	public void setEnrols(final Collection<Enrol> enrols) {
		this.enrols = enrols;
	}

	@Valid
	@OneToMany(mappedBy = "member")
	public Collection<Dropout> getDropouts() {
		return this.dropouts;
	}

	public void setDropouts(final Collection<Dropout> dropouts) {
		this.dropouts = dropouts;
	}

	@Valid
	@OneToMany
	public Collection<Request> getRequests() {
		return this.requests;
	}

	public void setRequests(final Collection<Request> requests) {
		this.requests = requests;
	}

}
