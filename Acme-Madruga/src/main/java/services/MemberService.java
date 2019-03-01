
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.MemberRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Dropout;
import domain.Enrol;
import domain.Member;
import domain.MessageBox;
import domain.Request;
import forms.MemberForm;

@Service
@Transactional
public class MemberService {

	// Managed repository
	// -------------------------------------------------------------
	@Autowired
	private MemberRepository	memberRepository;

	// Supporting services
	// -------------------------------------------------------------

	@Autowired
	private MessageBoxService	messageBoxService;

	@Autowired
	private FinderService		finderService;


	// CRUD methods
	// ------------------------------------------------------------------

	public Member create() {
		Member result;

		result = new Member();

		final Authority authority = new Authority();
		authority.setAuthority(Authority.MEMBER);

		final Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		final UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(authorities);

		final Collection<MessageBox> boxes = this.messageBoxService.createSystemMessageBox();

		result.setFinder(this.finderService.create());
		result.setDropouts(new ArrayList<Dropout>());
		result.setEnrols(new ArrayList<Enrol>());
		result.setRequests(new ArrayList<Request>());
		result.setUserAccount(userAccount);
		result.setMessageBoxes(boxes);

		return result;

	}
	public Member findOne(final int memberId) {
		final Member result = this.memberRepository.findOne(memberId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Member> findAll() {
		final Collection<Member> result = this.memberRepository.findAll();
		Assert.notEmpty(result);
		Assert.notNull(result);

		return result;
	}

	public Member save(final Member member) {
		Assert.notNull(member);
		final Member result = this.memberRepository.save(member);

		return result;
	}

	public void delete(final Member member) {
		Assert.notNull(member);

		this.memberRepository.delete(member);

	}


	// Other methods
	// -----------------------------------------------------------------

	@Autowired
	private Validator	validator;


	public Member reconstruct(final MemberForm memberForm, final BindingResult binding) {
		final Member result = this.create();
		result.getUserAccount().setPassword(memberForm.getUserAccount().getPassword());
		result.getUserAccount().setUsername(memberForm.getUserAccount().getUsername());
		result.setUsername(memberForm.getUserAccount().getUsername());

		result.setAddress(memberForm.getAddress());

		result.setEmail(memberForm.getEmail());

		result.setMiddleName(memberForm.getMiddleName());
		result.setName(memberForm.getName());
		result.setPhoneNumber(memberForm.getPhoneNumber());
		result.setPhoto(memberForm.getPhoto());

		result.setSurname(memberForm.getSurname());

		this.validator.validate(result, binding);

		return result;
	}

	public Member findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Member result;

		result = this.memberRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Member findByPrincipal() {
		Member result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}
	public Member findOneByUsername(final String username) {
		Assert.notNull(username);

		return this.memberRepository.findByUserName(username);
	}
}
