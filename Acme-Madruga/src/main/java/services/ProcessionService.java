package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import domain.Actor;
import domain.Brotherhood;
import domain.Procession;
import repositories.ProcessionRepository;

@Service
@Transactional
public class ProcessionService {
	
	// Manage Repository
	@Autowired
	private ProcessionRepository	processionRepository;
	
	// Supporting services
	@Autowired
	private ActorService			actorService;
	
	
	// Validator
	@Autowired
	private Validator				validator;
	
	
	/************************************* CRUD methods ********************************/
	
	public Procession create() {
		Procession result;
		
		result = new Procession();
		result.setTicker(this.generateTicker());
		
		
		return result;
	}
	
	
	public Procession findOne(int id) {
		Procession result = this.processionRepository.findOne(id);
		
		Assert.notNull(result);
		
		return result;
	}
	
	@Transactional
	public Collection<Procession> findAll() {
		Collection<Procession> result = this.processionRepository.findAll();
		Assert.notNull(result);
		
		return result;
	}
	
	
	public Procession save(Procession procession) {
		Assert.notNull(procession);
		Actor principal;
		
		// Principal must be a Brotherhood
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Brotherhood.class, principal);
		
		Brotherhood brotherhood = (Brotherhood) principal;

		
		if(procession.getId() == 0) {
			procession.setBrotherhood(brotherhood);
		} else {
			Assert.isTrue(brotherhood.getProcessions().contains(procession));
		}
		
		return this.processionRepository.save(procession);
	}
	
	
	public void delete(Procession procession) {
		Assert.notNull(procession);
		Actor principal;
		
		// Principal must be a Brotherhood
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Brotherhood.class, principal);
		Assert.isTrue(procession.getId() != 0);
		
		Brotherhood brotherhood = (Brotherhood) principal;
		Assert.isTrue(brotherhood.getProcessions().contains(procession));
		
		
		this.processionRepository.delete(procession);
		
	}
	
	/************************************* Reconstruct ******************************************/
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Procession reconstruct(Procession procession, BindingResult binding) {
		Procession result;
		
		if(procession.getId() == 0) {
			validator.validate(procession, binding);
			result = procession;
		} else {
			result = this.findOne(procession.getId());
			result.setTitle(procession.getTitle());
			result.setDescription(procession.getDescription());
			result.setMoment(procession.getMoment());
			result.setDraftMode(procession.getDraftMode());
			
			validator.validate(result, binding);
		}
		
		return result;
	}
	
	
	/************************************* Other business methods********************************/
	public String generateTicker() {
		String ticker = "";
		final DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		final Date date = new Date();
		final String tickerDate = (dateFormat.format(date));
		final String tickerAlphanumeric = RandomStringUtils.randomAlphanumeric(5).toUpperCase();
		ticker = ticker.concat(tickerDate).concat("-").concat(tickerAlphanumeric);
		return ticker;
	}
	
	
	

}
