
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;
import domain.Member;
import domain.Procession;

@Service
@Transactional
public class FinderService {

	// Manage Repository
	@Autowired
	private FinderRepository		finderRepository;

	// Other services
	@Autowired
	private MemberService			memberService;

	@Autowired
	private ProcessionService		processionService;

	@Autowired
	private ConfigurationsService	configurationsService;


	// CRUD methods
	public Finder create() {
		final Finder result = new Finder();

		final Calendar lastUpdate = Calendar.getInstance();
		lastUpdate.add(Calendar.YEAR, -20);

		result.setLastUpdate(lastUpdate.getTime());
		return this.finderRepository.save(result);
	}

	public Finder findOne(final int finderID) {
		final Finder result = this.finderRepository.findOne(finderID);
		Assert.notNull(result);

		return result;
	}

	public Collection<Finder> findAll() {
		final Collection<Finder> result = this.finderRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Finder save(final Finder finder) {
		Assert.notNull(finder);
		final Member principal = this.memberService.findByPrincipal();
		Assert.isTrue(principal.getFinder().getId() == finder.getId());

		final Finder result = this.finderRepository.save(finder);

		return result;
	}

	public void delete(final Finder finder) {
		Assert.notNull(finder);
		final Member principal = this.memberService.findByPrincipal();
		Assert.isTrue(principal.getFinder().getId() == finder.getId());

		this.finderRepository.delete(finder);
	}

	// Check if something has changed, if so the results are updated
	// If not, the results are updated if it has not been updated
	// in the specified time
	public Finder checkChanges(final Finder finder) {
		final Finder old = this.findOne(finder.getId());
		if (/* finder.getArea() != old.getArea() || */finder.getMinDate() != old.getMinDate() || finder.getKeyword() != old.getKeyword() || finder.getMaxDate() != old.getMaxDate()) {

			System.out.println("Hay cambios");
			return this.updateResults(finder);

		} else {
			System.out.println("Sin cambios");
			finder.setProcessions(this.getResults(finder));
			return finder;
		}
	}

	// Check if it has passed enough time to update and return the results
	public Collection<Procession> getResults(final Finder finder) {
		final Calendar siguienteActualizacion = Calendar.getInstance();
		siguienteActualizacion.setTime(finder.getLastUpdate());
		final Calendar actual = Calendar.getInstance();
		System.out.println("Mirando si ha pasado el tiempo cache");
		siguienteActualizacion.add(Calendar.HOUR, this.configurationsService.getConfiguration().getCacheTime());

		if (actual.after(siguienteActualizacion))
			System.out.println("Ha pasado el tiempo");
		this.updateResults(finder);
		return finder.getProcessions();
	}

	public Finder updateResults(final Finder finder) {
		System.out.println("Actualizando resultados");
		Assert.notNull(finder);
		final ArrayList<Procession> result = new ArrayList<Procession>(this.processionService.findAll());

		if (finder.getMinDate() != null)
			result.retainAll(this.finderRepository.filterByMinDate(finder.getMinDate()));

		if (finder.getMaxDate() != null)
			result.retainAll(this.finderRepository.filterByMaxDate(finder.getMaxDate()));

		if (finder.getKeyword() != null)
			result.retainAll(this.finderRepository.filterByKeyword("%" + finder.getKeyword() + "%"));

		//		if (finder.getArea() != null)
		//			result.retainAll(this.finderRepository.filterByArea(finder.getArea().getId()));

		if (result.size() > this.configurationsService.getConfiguration().getFinderMaxResult())
			finder.setProcessions(result.subList(0, this.configurationsService.getConfiguration().getFinderMaxResult() - 1));
		else
			finder.setProcessions(result);
		finder.setLastUpdate(new Date());
		System.out.println("Nuevos datos cargados, guardando y volviendo");
		return this.save(finder);
	}
}
