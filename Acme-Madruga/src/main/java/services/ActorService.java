
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	// Manage Repository
	@Autowired
	private ActorRepository	actorRepository;


	// CRUD methods

	public Actor findOne(final int actorId) {
		final Actor result = this.actorRepository.findOne(actorId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Actor> findAll() {
		final Collection<Actor> result = this.actorRepository.findAll();
		Assert.notNull(result);
		Assert.notEmpty(result);

		return result;
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);
		final Actor result = this.actorRepository.save(actor);

		return result;
	}

	public void delete(final Actor actor) {
		Assert.notNull(actor);

		this.actorRepository.delete(actor);
	}

	// Other business methods
	public Actor findByPrincipal() {
		Actor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Actor findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Actor result;

		result = this.actorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Actor findOneByUsername(final String username) {
		Assert.notNull(username);

		return this.actorRepository.findByUserName(username);
	}
}
