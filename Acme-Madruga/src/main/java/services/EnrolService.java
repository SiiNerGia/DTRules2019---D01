
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.EnrolRepository;
import domain.Enrol;
import domain.Position;

@Service
@Transactional
public class EnrolService {

	// Managed repository
	// -------------------------------------------------------------
	@Autowired
	private EnrolRepository	enrolRepository;

	@Autowired
	@Qualifier("validator")
	private Validator		validator;


	// Supporting services
	// -------------------------------------------------------------

	// CRUD methods
	// ------------------------------------------------------------------

	public Enrol create() {
		final Enrol result = new Enrol();
		final Date now = new Date();
		final Collection<Position> positions = new ArrayList<Position>();
		result.setPositions(positions);
		result.setMoment(now);

		return result;
	}

	public Enrol findOne(final int enrolId) {
		final Enrol result = this.enrolRepository.findOne(enrolId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Enrol> findAll() {
		final Collection<Enrol> result = this.enrolRepository.findAll();
		Assert.notEmpty(result);
		Assert.notNull(result);

		return result;
	}

	public Enrol save(final Enrol enrol) {
		Assert.notNull(enrol);
		//		final Enrol old = this.findOne(enrol.getId());
		//		final Position newPosition = new ArrayList<Position>(enrol.getPositions()).get(0);
		//		final Position oldPosition = new ArrayList<Position>(old.getPositions()).get(0);
		//
		//		if (newPosition != oldPosition)
		//			oldPosition.getEnrol().remove(enrol);
		final Enrol result = this.enrolRepository.save(enrol);
		//
		//		if (newPosition != oldPosition)
		//			newPosition.getEnrol().add(result);

		return result;
	}
	public void delete(final Enrol enrol) {
		Assert.notNull(enrol);
		//		Position position;
		//		final ArrayList<Position> positions = new ArrayList<Position>();
		//
		//		enrol.getBrotherhood().getEnrols().remove(enrol);
		//		enrol.getMember().getEnrols().remove(enrol);
		//
		//		positions.addAll(enrol.getPositions());
		//		position = positions.get(0);
		//		position.getEnrol().remove(enrol);

		this.enrolRepository.delete(enrol);

	}

	public Enrol findByBrothehoodAndMemberId(final int brotherhoodId, final int memberId) {
		Assert.notNull(brotherhoodId);
		Assert.notNull(memberId);
		Enrol result;

		result = this.enrolRepository.findEnrolByBrotherhoodAndMemberId(brotherhoodId, memberId);
		return result;
	}
	// Other methods
	// -----------------------------------------------------------------

	public Enrol reconstruct(final Enrol enrol, final BindingResult binding) {
		final Enrol result = new Enrol();
		final Enrol temp = this.findOne(enrol.getId());
		// Atributos del prune
		result.setPositions(enrol.getPositions());

		// Atributos del original
		result.setBrotherhood(temp.getBrotherhood());
		result.setMember(temp.getMember());
		result.setId(temp.getId());
		result.setVersion(temp.getVersion());

		this.validator.validate(result, binding);

		return result;
	}

}
