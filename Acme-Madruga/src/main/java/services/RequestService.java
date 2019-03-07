
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import domain.Actor;
import domain.Brotherhood;
import domain.Member;
import domain.Message;
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
	private MemberService		memberService;

	@Autowired
	private BrotherhoodService	brotherhoodService;

	@Autowired
	private MessageService		messageService;


	/************************************* CRUD methods ********************************/
	public Request create() {
		Request result;
		Actor principal;

		result = new Request();

		// Principal must be a Member
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Member.class, principal);

		result.setStatus("PENDING");

		result.setMember(this.memberService.findByPrincipal());

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
		Member member = null;

		if (request.getId() != 0)
			Assert.isInstanceOf(Brotherhood.class, principal);
		else {
			Assert.isInstanceOf(Member.class, principal);
			member = this.memberService.findByPrincipal();
		}

		this.checkRequest(request);

		final Request r = this.requestRepository.save(request);

		if (member != null && !member.getRequests().contains(r))
			member.getRequests().add(r);

		if (!r.getProcession().getRequests().contains(r))
			r.getProcession().getRequests().add(r);

		return r;

	}

	public void delete(final int requestId) {
		Assert.isTrue(requestId != 0);
		final Request request = this.findOne(requestId);

		Assert.notNull(request);

		Assert.isTrue(request.getStatus().equals("PENDING"));

		// Principal must be a Member

		final Member member = request.getMember();
		Assert.isTrue(member.getRequests().contains(request));

		member.getRequests().remove(request);

		this.requestRepository.delete(request);

	}

	/************************************* Other business methods ********************************/

	public void checkRequest(final Request request) {
		boolean check = true;

		if (request.getStatus().equals("APPROVED")) {
			if (request.getAssignedColumn() < 0 || request.getAssignedRow() < 0 || request.getAssignedColumn() == null || request.getAssignedRow() == null)
				check = false;
		} else if (request.getStatus().equals("REJECTED")) {
			if (request.getReason().isEmpty())
				check = false;
		} else if (request.getProcession() == null)
			check = false;

		Assert.isTrue(check);
	}

	public Collection<Request> findRequestByBrotherhood(final String status) {
		final Collection<Request> result = new ArrayList<Request>();

		final Brotherhood b = this.brotherhoodService.findByPrincipal();

		Assert.notNull(b);

		for (final Procession p : b.getProcessions())
			for (final Request r : p.getRequests())
				if (r.getStatus().equals(status))
					result.add(r);
		return result;
	}
	public Collection<Request> findRequestsByStatus(final String status) {
		final Collection<Request> result = new ArrayList<Request>();

		final Member m = this.memberService.findByPrincipal();

		Assert.notNull(m);

		for (final Request r : m.getRequests())
			if (r.getStatus().equals(status))
				result.add(r);

		return result;
	}

	public void automaticNotification(final Request request, final Request old) {
		if (!old.getStatus().toString().equals(request.getStatus().toString())) {
			final Message message = this.messageService.create();
			message.setBody("The brotherhood " + request.getProcession().getBrotherhood().getTitle() + " changed the status of your request to march in " + request.getProcession().getTitle() + " from "
				+ old.getStatus().toString().toLowerCase(Locale.ENGLISH) + " to " + request.getStatus().toString().toLowerCase(Locale.ENGLISH) + ".");

			message.setIsNotification(true);
			message.getMessageBoxes().add(request.getMember().getMessageBox("in"));
			message.setPriority("MEDIUM");
			message.getRecipients().add(request.getMember());

			final Message send = this.messageService.save(message);

			request.getMember().getMessageBox("in").addMessage(send);
		}

	}

}
