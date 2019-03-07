
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.ProcessionService;
import domain.Procession;

@Controller
@RequestMapping("/procession")
public class ProcessionController extends AbstractController {

	@Autowired
	private ProcessionService	processionService;

	@Autowired
	private BrotherhoodService	brotherhoodService;

	// Validator
	@Autowired
	@Qualifier("validator")
	private Validator			validator;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// List
	// ------------------------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final Integer brotherhoodId) {

		ModelAndView result;
		Collection<Procession> processions = new ArrayList<Procession>();

		try {
			if (brotherhoodId != null && brotherhoodId != 0)
				processions = this.brotherhoodService.findOne(brotherhoodId).getProcessions();

			result = new ModelAndView("procession/list");
			result.addObject("processions", processions);
			result.addObject("uri", "procession/list");
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	// Create
	// ------------------------------------------------------------------------------------
	@RequestMapping(value = "brotherhood/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Procession procession;

		procession = this.processionService.create();

		result = this.createEditModelAndView(procession);

		return result;
	}

	// Edition -------------------------------------------------------------
	@RequestMapping(value = "brotherhood/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int processionId) {
		ModelAndView result;
		Procession procession = null;

		try {
			procession = this.processionService.findOne(processionId);
			Assert.notNull(procession);
		} catch (final Throwable oops) {
			result = this.forbiddenOpperation();
			return result;
		}

		result = this.createEditModelAndView(procession);

		return result;
	}

	// Save -------------------------------------------------------------
	@RequestMapping(value = "brotherhood/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Procession pruned, final BindingResult binding) {
		ModelAndView result;
		Procession constructed;

		constructed = this.processionService.reconstruct(pruned, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(pruned);
		else
			try {
				this.processionService.save(constructed);
				result = new ModelAndView("redirect:../list.do");
			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = this.createEditModelAndView(pruned, "procession.registration.error");
			}

		return result;
	}

	// Delete
	// --------------------------------------------------------------------------------------
	@RequestMapping(value = "brotherhood/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int processionId) {
		ModelAndView result = null;
		Procession procession;

		try {
			procession = this.processionService.findOne(processionId);
			this.processionService.delete(procession);
			result = new ModelAndView("redirect:../list.do");
		} catch (final Throwable oops) {
			result = this.forbiddenOpperation();
			return result;
		}

		return result;
	}

	// Ancillary Methods
	// -----------------------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Procession procession) {
		ModelAndView result;

		result = this.createEditModelAndView(procession, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Procession procession, final String message) {
		ModelAndView result;

		result = new ModelAndView("procession/brotherhood/edit");
		result.addObject("procession", procession);
		result.addObject("message", message);

		return result;
	}

	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}
}
