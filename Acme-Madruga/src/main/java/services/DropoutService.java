
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.DropoutRepository;
import domain.Dropout;

@Service
@Transactional
public class DropoutService {

	// Managed repository
	// -------------------------------------------------------------
	@Autowired
	private DropoutRepository	dropoutRespository;


	// Supporting services
	// -------------------------------------------------------------

	// CRUD methods
	// ------------------------------------------------------------------
	public Dropout create() {
		final Dropout result = new Dropout();
		final Date now = new Date();
		result.setDate(now);

		return result;
	}

	public Dropout findOne(final int dropoutId) {
		final Dropout result = this.dropoutRespository.findOne(dropoutId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Dropout> findAll() {
		final Collection<Dropout> result = this.dropoutRespository.findAll();
		Assert.notEmpty(result);
		Assert.notNull(result);

		return result;
	}

	public Dropout save(final Dropout dropout) {
		Assert.notNull(dropout);
		final Dropout result = this.dropoutRespository.save(dropout);

		return result;
	}

	public void delete(final Dropout dropout) {
		Assert.notNull(dropout);

		this.dropoutRespository.delete(dropout);

	}

	// Other methods
	// -----------------------------------------------------------------

}
