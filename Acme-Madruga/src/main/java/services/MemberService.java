
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MemberRepository;
import security.LoginService;
import security.UserAccount;
import domain.Member;

@Service
@Transactional
public class MemberService {

	// Managed repository
	// -------------------------------------------------------------
	@Autowired
	private MemberRepository	memberRepository;


	// Supporting services
	// -------------------------------------------------------------

	// CRUD methods
	// ------------------------------------------------------------------

	public Member create() {
		Member result;

		result = new Member();

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
