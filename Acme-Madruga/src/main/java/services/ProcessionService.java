
package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ProcessionRepository;
import domain.Actor;
import domain.Brotherhood;
import domain.Member;
import domain.Message;
import domain.Procession;
import domain.Request;

@Service
@Transactional
public class ProcessionService {

	// Manage Repository
	@Autowired
	private ProcessionRepository	processionRepository;

	// Supporting services
	@Autowired
	private ActorService			actorService;

	@Autowired
	private MessageService			messageService;

	@Autowired
	private MemberService			memberService;

	@Autowired
	private RequestService			requestService;

	// Validator
	@Autowired
	private Validator				validator;


	/************************************* CRUD methods ********************************/

	public Procession create() {
		Procession result;

		result = new Procession();

		result.setTicker(this.generateTicker());
		while (this.isValidTicker(result.getTicker()) == false)
			result.setTicker(this.generateTicker());

		return result;
	}

	public Procession findOne(final int id) {
		final Procession result = this.processionRepository.findOne(id);

		Assert.notNull(result);

		return result;
	}

	@Transactional
	public Collection<Procession> findAll() {
		final Collection<Procession> result = this.processionRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Procession save(final Procession procession) {
		Assert.notNull(procession);
		Actor principal;

		// Principal must be a Brotherhood
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Brotherhood.class, principal);

		final Brotherhood brotherhood = (Brotherhood) principal;

		if (procession.getId() == 0) {
			procession.setBrotherhood(brotherhood);
			this.automaticNotification(procession);
		} else
			Assert.isTrue(brotherhood.getProcessions().contains(procession));

		return this.processionRepository.save(procession);
	}

	public void delete(final Procession procession) {
		Assert.notNull(procession);
		Actor principal;

		// Principal must be a Brotherhood
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Brotherhood.class, principal);
		Assert.isTrue(procession.getId() != 0);

		final Brotherhood brotherhood = (Brotherhood) principal;

		for (final Request r : procession.getRequests())
			this.requestService.delete(r.getId());

		brotherhood.getProcessions().remove(procession);

		this.processionRepository.delete(procession);

	}
	/************************************* Reconstruct ******************************************/
	public Procession reconstruct(final Procession pruned, final BindingResult binding) {
		Procession result = this.create();
		Procession temp;

		if (pruned.getId() == 0) {
			this.validator.validate(pruned, binding);
			result = pruned;
		} else {
			temp = this.findOne(pruned.getId());
			result.setId(temp.getId());
			result.setVersion(temp.getVersion());
			result.setBrotherhood(temp.getBrotherhood());
			result.setTicker(temp.getTicker());

			result.setTitle(pruned.getTitle());
			result.setDescription(pruned.getDescription());
			result.setMoment(pruned.getMoment());
			result.setDraftMode(pruned.getDraftMode());

			this.validator.validate(result, binding);
		}

		return result;
	}

	/************************************* Other business methods ********************************/
	public String generateTicker() {
		String ticker = "";
		final DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		final Date date = new Date();
		final String tickerDate = (dateFormat.format(date));
		final String tickerAlphanumeric = RandomStringUtils.randomAlphanumeric(5).toUpperCase();
		ticker = ticker.concat(tickerDate).concat("-").concat(tickerAlphanumeric);
		return ticker;
	}

	private void automaticNotification(final Procession procession) {
		final Message message = this.messageService.create();
		message.setBody("The brotherhood " + procession.getBrotherhood().getTitle() + " has published a procession called " + procession.getTitle() + ".");

		message.setIsNotification(true);
		for (final Member m : this.memberService.findByBrotherhood(procession.getBrotherhood())) {
			message.getMessageBoxes().add(m.getMessageBox("in"));
			message.getRecipients().add(m);
		}
		message.setPriority("MEDIUM");
		message.setSubject("New procession by " + procession.getBrotherhood().getTitle());

		final Message send = this.messageService.save(message);

		for (final Member m : this.memberService.findByBrotherhood(procession.getBrotherhood()))
			m.getMessageBox("in").addMessage(send);
	}

	public Boolean isValidTicker(final String ticker) {
		Boolean res = true;
		final Collection<String> tickers = new ArrayList<String>();
		for (final Procession p : this.processionRepository.findAll())
			tickers.add(p.getTicker());

		if (tickers.contains(ticker))
			res = false;
		return res;
	}
}
