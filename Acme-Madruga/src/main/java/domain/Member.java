package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Member extends Actor {

	// Attributes
	// --------------------------------------------------------------------

	// Relationships
	// -----------------------------------------------------------------

//	private Finder finder;

	private Collection<Enrol> enrols;

	private Collection<Dropout> dropouts;

	private Collection<Request> requests;

//	@Valid
//	@OneToOne
//	public Finder getFinder() {
//		return finder;
//	}
//
//	public void setFinder(Finder finder) {
//		this.finder = finder;
//	}

	@Valid
	@OneToMany
	public Collection<Enrol> getEnrols() {
		return enrols;
	}

	public void setEnrols(Collection<Enrol> enrols) {
		this.enrols = enrols;
	}

	@Valid
	@OneToMany
	public Collection<Dropout> getDropouts() {
		return dropouts;
	}

	public void setDropouts(Collection<Dropout> dropouts) {
		this.dropouts = dropouts;
	}

	@Valid
	@OneToMany
	public Collection<Request> getRequests() {
		return requests;
	}

	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}

}
