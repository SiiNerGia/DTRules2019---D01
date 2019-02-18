package services;

import domain.Actor;
import domain.Brotherhood;
import domain.Coach;
import domain.Procession;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CoachRepository;
import repositories.ProcessionRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class CoachService {
	
	// Manage Repository

	@Autowired
	private CoachRepository coachRepository;
	
	// Supporting services
	@Autowired
	private ActorService			actorService;
	
	
	/************************************* CRUD methods ********************************/
	public Coach create() {
		Coach result;
		Actor principal;
		
		result = new Coach();

		// Principal must be a Brotherhood
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Brotherhood.class, principal);
		
		return result;
	}
	
	public Coach findOne(int id) {
		Coach result = this.coachRepository.findOne(id);
		
		Assert.notNull(result);
		
		return result;
	}
	
	public Collection<Coach> findAll() {
		Collection<Coach> result = this.coachRepository.findAll();
		//Assert.notNull(result);
		
		return result;
	}
	
	
	public Coach save(Coach coach) {
		Assert.notNull(coach);
		Actor principal;
		
		// Principal must be a Brotherhood
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Brotherhood.class, principal);
		
		return this.coachRepository.save(coach);
	}
	
	
	public void delete(int coachId) {
		Assert.isTrue(coachId != 0);
		Coach coach = this.findOne(coachId);

		Assert.notNull(coach);
		Actor principal;
		
		// Principal must be a Brotherhood
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Brotherhood.class, principal);
		
		Brotherhood brotherhood = (Brotherhood) principal;
		Assert.isTrue(brotherhood.getCoaches().contains(coach));
		
		
		this.coachRepository.delete(coach);
		
	}
	
	/************************************* Other business methods********************************/

	
	

}
