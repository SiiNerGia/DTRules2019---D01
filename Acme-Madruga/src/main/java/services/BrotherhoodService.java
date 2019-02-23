
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

import repositories.BrotherhoodRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Brotherhood;
import domain.Coach;
import domain.Member;
import domain.Procession;
import domain.Url;
import forms.BrotherhoodForm;

@Service
@Transactional
public class BrotherhoodService {

	// Manage Repository
	@Autowired
	private BrotherhoodRepository	brotherhoodRepository;

	// Validator for form
	@Autowired
	@Qualifier("validator")
	private Validator				validator;


	// CRUD methods
	public Brotherhood create() {
		final Brotherhood result = new Brotherhood();

		final UserAccount userAccount = new UserAccount();
		final Collection<Authority> authorities = new ArrayList<Authority>();
		final Authority authority = new Authority();
		authority.setAuthority(Authority.BROTHERHOOD);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		result.setUserAccount(userAccount);
		result.setEstablishment(new Date());
		result.setPictures(new ArrayList<Url>());
		result.setCoaches(new ArrayList<Coach>());
		result.setProcessions(new ArrayList<Procession>());

		return result;
	}
	public Brotherhood findOne(final int brotherhoodId) {
		final Brotherhood result = this.brotherhoodRepository.findOne(brotherhoodId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Brotherhood> findAll() {
		final Collection<Brotherhood> result = this.brotherhoodRepository.findAll();
		Assert.notNull(result);
		Assert.notEmpty(result);

		return result;
	}

	public Brotherhood save(final Brotherhood brotherhood) {
		Assert.notNull(brotherhood);
		final Brotherhood result = this.brotherhoodRepository.save(brotherhood);

		return result;
	}

	public void delete(final Brotherhood brotherhood) {
		Assert.notNull(brotherhood);

		this.brotherhoodRepository.delete(brotherhood);
	}

	// Other business methods
	public Brotherhood findByPrincipal() {
		Brotherhood result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Brotherhood findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Brotherhood result;

		result = this.brotherhoodRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Brotherhood findOneByUsername(final String username) {
		Assert.notNull(username);

		return this.brotherhoodRepository.findByUserName(username);
	}

	//Reconstruct object, check validity and update binding
	public Brotherhood reconstruct(final BrotherhoodForm form, final BindingResult binding) {
		final Brotherhood bro = this.create();

		bro.getUserAccount().setPassword(form.getUserAccount().getPassword());
		bro.getUserAccount().setUsername(form.getUserAccount().getUsername());

		bro.setAddress(form.getAddress());
		bro.setEmail(form.getEmail());
		bro.setMiddleName(form.getMiddlename());
		bro.setName(form.getName());
		bro.setPhoneNumber(form.getPhone());
		bro.setPhoto(form.getPhoto());
		bro.setSurname(form.getSurname());
		bro.setTitle(form.getTitle());
		bro.getEstablishment().setTime(bro.getEstablishment().getTime() - 1000);

		this.validator.validate(bro, binding);

		return bro;
	}
	public Collection<Brotherhood> findAllMemberBelongs(final Member member) {
		final Collection<Brotherhood> bros = this.brotherhoodRepository
			.findBrotherhoodsMemberBelongs(member.getId());

		Assert.notNull(bros);

		return bros;
	}
	public Collection<Brotherhood> findAllMemberBelonged(final Member member) {
		final Collection<Brotherhood> bros = this.brotherhoodRepository
			.findBrotherhoodsMemberHashBelong(member.getId());

		Assert.notNull(bros);

		return bros;
	}
}
