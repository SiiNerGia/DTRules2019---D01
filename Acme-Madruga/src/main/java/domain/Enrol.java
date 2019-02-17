package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@Access(AccessType.PROPERTY)
public class Enrol {

	// Atributtes ----------------------------------------------------
	private Date moment;
	// Relashionships -----------------------------------------------
	private Member member;

	private Brotherhood brotherhood;

	private Position position;

	// Methods -------------------------------------------------------

	@NotNull
	@Past
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@Valid
	@ManyToOne
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Valid
	@ManyToOne
	public Brotherhood getBrotherhood() {
		return brotherhood;
	}

	public void setBrotherhood(Brotherhood brotherhood) {
		this.brotherhood = brotherhood;
	}

	@ManyToMany
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
