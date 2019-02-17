package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EnrolRepository;
import domain.Enrol;

@Service
@Transactional
public class EnrolService {

	// Managed repository
	// -------------------------------------------------------------
	@Autowired
	private EnrolRepository enrolRepository;

	// Supporting services
	// -------------------------------------------------------------

	// CRUD methods
	// ------------------------------------------------------------------
	public Enrol findOne(int enrolId) {
		Enrol result = this.enrolRepository.findOne(enrolId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Enrol> findAll() {
		Collection<Enrol> result = this.enrolRepository.findAll();
		Assert.notEmpty(result);
		Assert.notNull(result);

		return result;
	}

	public Enrol save(Enrol enrol) {
		Assert.notNull(enrol);
		Enrol result = this.enrolRepository.save(enrol);

		return result;
	}

	public void delete(Enrol enrol) {
		Assert.notNull(enrol);

		this.enrolRepository.delete(enrol);

	}

	// Other methods
	// -----------------------------------------------------------------

}
