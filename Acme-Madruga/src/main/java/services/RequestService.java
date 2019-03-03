
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import domain.Actor;
import domain.Brotherhood;
import domain.Member;
import domain.Procession;
import domain.Request;

@Service
@Transactional
public class RequestService {

	// Manage Repository

	@Autowired
	private RequestRepository	requestRepository;

	// Supporting services
	@Autowired
	private ActorService		actorService;

	@Autowired
	private BrotherhoodService	brotherhoodService;


	/************************************* CRUD methods ********************************/
	public Request create() {
		Request result;
		Actor principal;

		result = new Request();

		// Principal must be a Member
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Member.class, principal);

		result.setStatus("PENDING");

		return result;
	}

	public Request findOne(final int id) {
		final Request result = this.requestRepository.findOne(id);

		Assert.notNull(result);

		return result;
	}

	public Collection<Request> findAll() {
		final Collection<Request> result = this.requestRepository.findAll();
		//Assert.notNull(result);

		return result;
	}

	public Request save(final Request request) {
		Assert.notNull(request);
		Actor principal;

		// Principal must be a Member or a Brotherhood
		principal = this.actorService.findByPrincipal();

		if (request.getId() != 0) {
			Assert.isInstanceOf(Brotherhood.class, principal);
		} else {
			Assert.isInstanceOf(Member.class, principal);
		}

		this.checkRequest(request);

		return this.requestRepository.save(request);
	}

	public void delete(final int requestId) {
		Assert.isTrue(requestId != 0);
		final Request request = this.findOne(requestId);

		Assert.notNull(request);
		Actor principal;

		Assert.isTrue(request.getStatus().equals("PENDING"));

		// Principal must be a Member
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Member.class, principal);

		final Member member = (Member) principal;
		Assert.isTrue(member.getRequests().contains(request));

		this.requestRepository.delete(request);

	}

	/************************************* Other business methods ********************************/

	public void checkRequest(final Request request) {
		boolean check = true;

		if (request.getStatus() == "ACCEPTED") {
			if ((request.getAssignedColumn() < 0) || (request.getAssignedRow() < 0)) {
				check = false;
			}
		} else if (request.getStatus() == "REJECTED") {
			if (request.getReason() == null) {
				check = false;
			}
		}

		Assert.isTrue(check);
	}

	public Collection<Request> findRequestByBrotherhood(final int brotherhoodId) {
		final Collection<Request> result = new ArrayList<Request>();

		final Brotherhood b = this.brotherhoodService.findOne(brotherhoodId);

		for (final Procession p : b.getProcessions()) {
			result.addAll(p.getRequests());
		}

		return result;
	}

}
