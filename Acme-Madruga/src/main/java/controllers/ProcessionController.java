package controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Brotherhood;
import domain.Procession;
import forms.ProcessionForm;
import services.BrotherhoodService;
import services.ProcessionService;

@Controller
@RequestMapping("/procession")
public class ProcessionController extends AbstractController {

	@Autowired
	private ProcessionService processionService;

	@Autowired
	private BrotherhoodService brotherhoodService;

	// Validator
	@Autowired
	private Validator validator;

	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// List
	// ------------------------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Brotherhood principal;
		Collection<Procession> processions;

		principal = this.brotherhoodService.findByPrincipal();

		try {
			processions = principal.getProcessions();
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

	// Save the new Procession
	// ------------------------------------------------------------------------------------
	// @RequestMapping(value = "brotherhood/create", method =
	// RequestMethod.POST, params = "save")
	// public ModelAndView save(ProcessionForm processionForm, final
	// BindingResult binding) {
	// ModelAndView result;
	// Procession procession;
	//
	// procession = this.processionService.reconstruct(processionForm, binding);
	// if (binding.hasErrors()) {
	// final List<ObjectError> errors = binding.getAllErrors();
	// for (final ObjectError e : errors)
	// System.out.println(e.toString());
	//
	// result = new ModelAndView("procession/brotherhood/create");
	// result.addObject("processionForm", processionForm);
	// }
	// else
	// try {
	// this.processionService.save(procession);
	// result = new ModelAndView("redirect:../procession/list.do");
	// } catch (final Throwable oops) {
	// System.out.println(procession);
	// System.out.println(oops.getMessage());
	// System.out.println(oops.getClass());
	// System.out.println(oops.getCause());
	// result = this.createEditModelAndView(processionForm);
	// }
	// return result;
	// }

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
	public ModelAndView save(Procession procession, BindingResult binding) {
		ModelAndView result;

		procession = this.reconstruct(procession, binding);

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			result = this.createEditModelAndView(procession);
		} else
			try {
				this.processionService.save(procession);
				result = new ModelAndView("redirect:../list.do");
			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = this.createEditModelAndView(procession, "procession.registration.error");
			}

		return result;
	}

	// Delete
	// --------------------------------------------------------------------------------------
	@RequestMapping(value = "brotherhood/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int processionId) {
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
	public Procession reconstruct(Procession procession, BindingResult binding) {
		Procession result;

		if (procession.getId() == 0) {
			return procession;
		} else {
			result = this.processionService.findOne(procession.getId());
			result.setTitle(procession.getTitle());
			result.setDescription(procession.getDescription());
			result.setMoment(procession.getMoment());
			result.setDraftMode(procession.getDraftMode());

			validator.validate(result, binding);
			if (binding.hasErrors())
				return procession;
		}

		return result;
	}

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

	protected ModelAndView createEditModelAndView(ProcessionForm processionForm) {
		ModelAndView result;

		result = this.createEditModelAndView(processionForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(ProcessionForm processionForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("procession/brotherhood/create");
		result.addObject("processionForm", processionForm);
		result.addObject("message", message);

		return result;
	}

	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}
}
