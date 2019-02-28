
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrator;
import domain.Brotherhood;
import domain.MessageBox;
import domain.Procession;
import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AdministratorService {

	// Manage Repository
	@Autowired
	private AdministratorRepository adminRepository;

	// Supporting services
	@Autowired
	private ConfigurationsService configurationsService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private MessageBoxService messageBoxService;

	/*************************************
	 * CRUD methods
	 ********************************/
	public Administrator create() {
		// Initialice
		UserAccount userAccount = new UserAccount();
		Collection<Authority> authorities = new ArrayList<Authority>();
		Authority authority = new Authority();
		authority.setAuthority(Authority.ADMIN);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		Collection<MessageBox> boxes = this.messageBoxService.createSystemMessageBox();

		Administrator admin = new Administrator();
		admin.setUserAccount(userAccount);
		admin.setMessageBoxes(boxes);
		admin.setIsSpammer(false);

		return admin;
	}

	public Collection<Administrator> findAll() {
		final Collection<Administrator> result = this.adminRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Administrator findOne(final int adminID) {
		final Administrator result = this.adminRepository.findOne(adminID);
		Assert.notNull(result);
		return result;
	}

	public Administrator save(final Administrator admin) {
		Assert.notNull(admin);
		UserAccount userAccount;

		if (admin.getId() == 0) {
			// admin.setMessageBoxes(this.messageBoxService.createSystemMessageBox());
			if (!admin.getPhoneNumber().startsWith("+")) {
				final String countryCode = this.configurationsService.getConfiguration().getCountryCode();
				final String phoneNumer = admin.getPhoneNumber();
				admin.setPhoneNumber(countryCode.concat(phoneNumer));
			}
		} else {
			if (!admin.getPhoneNumber().startsWith("+")) {
				final String countryCode = this.configurationsService.getConfiguration().getCountryCode();
				final String phoneNumer = admin.getPhoneNumber();
				admin.setPhoneNumber(countryCode.concat(phoneNumer));
			}
			userAccount = LoginService.getPrincipal();
			Assert.isTrue(userAccount.equals(admin.getUserAccount()));
		}
		return this.adminRepository.save(admin);
	}

	public void delete(final Administrator admin) {
		Assert.notNull(admin);
		Assert.isTrue(admin.getId() != 0);
		this.adminRepository.delete(admin);
	}

	/*************************************
	 * Other business methods
	 ********************************/
	public Administrator findByPrincipal() {
		Administrator result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Administrator findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Administrator result;

		result = this.adminRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	// 12.1 Create user accounts for new
	// administrators---------------------------------------------------
	public Administrator registerNewAdmin(final Administrator admin) {
		Administrator principal;

		// Make sure that the principal is an Admin
		principal = this.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		// Check admin is not null
		Assert.notNull(admin);

		// Saves admin in the databese
		return this.adminRepository.save(admin);
	}

	// 12.2 Manage the catalogue of positions
	// ---------------------------------------------------

	// 12.3 Display a dashboard with the following
	// information-----------------------------------
	public Object[] query1() {
		return this.adminRepository.query1();
	}

	public Collection<Brotherhood> query2() {
		Collection<Brotherhood> result = this.adminRepository.query2();
		return result;
	}

	public Collection<Brotherhood> query3() {
		Collection<Brotherhood> result = this.adminRepository.query3();
		return result;
	}

	public Collection<Double> query4() {
		Collection<Double> result = this.adminRepository.query4();
		return result;
	}

	public Collection<Procession> query5() {
		Collection<Procession> result;
		Calendar c = new GregorianCalendar();
		c.add(Calendar.DATE, 30);
		Date date = c.getTime();
		result = this.adminRepository.query5(date);
		return result;
	}

	// 28.2 Spammers actors--------------------------------------------------------------------
	public Collection<Actor> getSuspiciousActors() {
		Administrator principal;

		// Make sure that the principal is an Admin
		principal = this.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.actorService.findSpammers();
	}

	// 38.3 Ban an actor ----------------------------------------------------------------
	public Actor banAnActor(Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getIsSpammer());

		// Make sure that the principal is an Admin
		final Object principal = this.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		actor.setUsername(actor.getUserAccount().getUsername());
		actor.getUserAccount().setUsername(null);
		actor.setIsBanned(true);

		return this.actorService.save(actor);

	}

	// 38.4 Unbans an actor, which means that his or her user account is re-activated
	public Actor unBanAnActor(Actor actor) {
		Assert.notNull(actor);
		// Assert.notNull(actor.getUsername());

		// Make sure that the principal is an Admin
		final Object principal = this.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		actor.getUserAccount().setUsername(actor.getUsername());
		actor.setIsBanned(false);

		return this.actorService.save(actor);

	}

}
