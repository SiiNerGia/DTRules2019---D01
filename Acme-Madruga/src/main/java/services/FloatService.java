
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FloatRepository;
import domain.Actor;
import domain.Brotherhood;
import domain.Float;
import domain.Url;

@Service
@Transactional
public class FloatService {

	// Manage Repository

	@Autowired
	private FloatRepository		floatRepository;

	// Supporting services
	@Autowired
	private ActorService		actorService;

	@Autowired
	private BrotherhoodService	brotherhoodService;


	/************************************* CRUD methods ********************************/
	public Float create() {
		Float result;
		Actor principal;

		result = new Float();

		// Principal must be a Brotherhood
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Brotherhood.class, principal);

		result.setPictures(new ArrayList<Url>());

		return result;
	}

	public Float findOne(final int id) {
		final Float result = this.floatRepository.findOne(id);

		Assert.notNull(result);

		return result;
	}

	public Collection<Float> findAll() {
		final Collection<Float> result = this.floatRepository.findAll();
		//Assert.notNull(result);

		return result;
	}

	public Float save(final Float f) {
		Assert.notNull(f);
		Actor principal;

		// Principal must be a Brotherhood
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Brotherhood.class, principal);

		final Float c = this.floatRepository.save(f);

		if (!this.brotherhoodService.findByPrincipal().getFloats().contains(c))
			this.brotherhoodService.findByPrincipal().getFloats().add(c);

		return c;
	}
	public void delete(final int floatId) {
		Assert.isTrue(floatId != 0);
		final Float f = this.findOne(floatId);

		Assert.notNull(f);
		Actor principal;

		// Principal must be a Brotherhood
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Brotherhood.class, principal);

		final Brotherhood brotherhood = (Brotherhood) principal;
		Assert.isTrue(brotherhood.getFloats().contains(f));

		brotherhood.getFloats().remove(f);

		this.floatRepository.delete(f);

	}

	/************************************* Other business methods ********************************/

}
