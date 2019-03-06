
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.MemberRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Brotherhood;
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

	@Autowired
	@Qualifier("validator")
	private Validator			validator;


	// CRUD methods
	// ------------------------------------------------------------------

	public Member create() {
		final Member result = new Member();

		final UserAccount userAccount = new UserAccount();
		final Collection<Authority> authorities = new ArrayList<Authority>();
		final Authority authority = new Authority();
		authority.setAuthority(Authority.MEMBER);
		authorities.add(authority);
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

	public Member reconstruct(final MemberForm memberForm, final BindingResult binding) {
		final Member result = this.create();

		result.getUserAccount().setPassword(memberForm.getUserAccount().getPassword());
		result.getUserAccount().setUsername(memberForm.getUserAccount().getUsername());

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

	public Member reconstruct(final Member member, final BindingResult binding) {
		final Member result = this.create();
		final Member temp = this.findOne(member.getId());

		Assert.isTrue(this.findByPrincipal().getId() == member.getId());

		result.setAddress(member.getAddress());
		result.setEmail(member.getEmail());
		result.setMiddleName(member.getMiddleName());
		result.setName(member.getName());
		result.setPhoneNumber(member.getPhoneNumber());
		result.setPhoto(member.getPhoto());
		result.setSurname(member.getSurname());
		//		result.setFinder(finder);
		result.setDropouts(member.getDropouts());
		result.setRequests(member.getRequests());
		result.setEnrols(temp.getEnrols());
		result.setId(temp.getId());
		result.setVersion(temp.getVersion());
		result.setUserAccount(temp.getUserAccount());

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

	public Collection<Member> findByBrotherhood(final Brotherhood brotherhood) {
		final Collection<Member> result = this.memberRepository.findByBrotherhood(brotherhood.getId());
		Assert.notNull(result);
		return result;
	}
}
