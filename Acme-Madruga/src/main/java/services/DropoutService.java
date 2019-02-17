package services;

import java.util.Collection;

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
	private DropoutRepository dropoutRespository;

	// Supporting services
	// -------------------------------------------------------------

	// CRUD methods
	// ------------------------------------------------------------------
	public Dropout findOne(int dropoutId) {
		Dropout result = this.dropoutRespository.findOne(dropoutId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Dropout> findAll() {
		Collection<Dropout> result = this.dropoutRespository.findAll();
		Assert.notEmpty(result);
		Assert.notNull(result);

		return result;
	}

	public Dropout save(Dropout dropout) {
		Assert.notNull(dropout);
		Dropout result = this.dropoutRespository.save(dropout);

		return result;
	}

	public void delete(Dropout dropout) {
		Assert.notNull(dropout);

		this.dropoutRespository.delete(dropout);

	}

	// Other methods
	// -----------------------------------------------------------------

}
