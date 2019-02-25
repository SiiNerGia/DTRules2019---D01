
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String	keyword;
	private Date	maxDate;
	private Date	minDate;
	private Date	lastUpdate;


	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(final String keyword) {
		this.keyword = keyword;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getMaxDate() {
		return this.maxDate;
	}

	public void setMaxDate(final Date maxDate) {
		this.maxDate = maxDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getMinDate() {
		return this.minDate;
	}

	public void setMinDate(final Date minDate) {
		this.minDate = minDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getLastUpdate() {
		return this.lastUpdate;
	}
	public void setLastUpdate(final Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	// Relationships -------------------------------------------------------------
	private Collection<Procession>	processions;


	//private Area					area;

	//	@Valid
	//	@OneToOne(optional = true)
	//	public Area getArea() {
	//		return this.area;
	//	}
	//
	//	public void setArea(final Area area) {
	//		this.area = area;
	//	}

	@Valid
	@OneToMany
	public Collection<Procession> getProcessions() {
		return this.processions;
	}

	public void setFixUpTasks(final Collection<Procession> processions) {
		this.processions = processions;
	}

	@Override
	public String toString() {
		return "Finder [Number of results: " + this.getProcessions().size() + "]";
	}
}
