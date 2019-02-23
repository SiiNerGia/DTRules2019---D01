package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrator;
import domain.Position;
import repositories.PositionRepository;

@Service
@Transactional
public class PositionService {

	// Manage Repository
	@Autowired
	private PositionRepository positionRepository;

	// Supporting services
	@Autowired
	private ActorService actorService;
	

	/**
	 * CRUD methods
	 */
	public Position create() {
		Position result = new Position();
//		Collection<Enrol> enrol = new ArrayList<Enrol>();
//		result.setEnrol(enrol);

		return result;
	}

	public Position findOne(int id) {
		Position result = this.positionRepository.findOne(id);
		Assert.notNull(result);

		return result;
	}

	public Collection<Position> findAll() {
		Collection<Position> result = this.positionRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public Position save(Position position) {
		Assert.notNull(position);
		Actor principal;
		
		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		
		return this.positionRepository.save(position);
	}
	
	
	public void delete(Position position) {
		Assert.notNull(position);
		Actor principal;
		
		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		Assert.isTrue(position.getId() != 0);
		
		// Posistion must not be in use to be deleted
		Assert.isTrue(position.getEnrol().isEmpty());
		
		this.positionRepository.delete(position);
	}
}
