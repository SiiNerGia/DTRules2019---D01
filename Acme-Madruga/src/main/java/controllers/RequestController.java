
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.MemberService;
import services.ProcessionService;
import services.RequestService;
import domain.Procession;
import domain.Request;

@Controller
@RequestMapping("/request")
public class RequestController extends AbstractController {

	@Autowired
	private RequestService		requestService;

	@Autowired
	private MemberService		memberService;

	@Autowired
	private BrotherhoodService	brotherhoodService;

	@Autowired
	private ProcessionService	processionService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// List ------------------------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Request> requests = new ArrayList<Request>();

		try {

			if (this.memberService.findByPrincipal() != null)
				requests = this.memberService.findByPrincipal().getRequests();
			else if (this.brotherhoodService.findByPrincipal() != null)
				requests = this.requestService.findRequestByBrotherhood(this.brotherhoodService.findByPrincipal().getId());
			result = new ModelAndView("request/list");
			result.addObject("requests", requests);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	// Create ------------------------------------------------------------------------------------
	@RequestMapping(value = "/member/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Request request;

		request = this.requestService.create();

		result = this.createEditModelAndView(request);

		return result;
	}

	// Save the new request ------------------------------------------------------------------------------------
	@RequestMapping(value = "/member/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Request request, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = new ModelAndView("request/member/create");
			result.addObject("request", request);
		} else
			try {
				this.requestService.save(request);
				result = new ModelAndView("request/list");
			} catch (final Throwable oops) {
				System.out.println(request);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.createEditModelAndView(request);

				if (oops instanceof DataIntegrityViolationException)
					result = this.createEditModelAndView(request, "request.commit.username");
				else
					result = this.createEditModelAndView(request, "request.commit.error");
			}
		return result;
	}

	// Edit ------------------------------------------------------------------------------------
	@RequestMapping(value = "/brotherhood/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int requestId) {
		ModelAndView result;
		Request request;

		try {
			request = this.requestService.findOne(requestId);
			Assert.notNull(request);
		} catch (final Throwable oops) {
			result = this.forbiddenOpperation();
			return result;
		}

		result = this.editModelAndView(request);

		return result;
	}

	// Save the edited request ------------------------------------------------------------------------------------
	@RequestMapping(value = "/brotherhood/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(final Request request, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = new ModelAndView("request/member/create");
			result.addObject("request", request);
		} else
			try {
				/*** Descomentar cuando el metodo funcione, comprobar que se manda la notificacion ***/
				//final Request old = this.requestService.findOne(request.getId());
				this.requestService.save(request);
				//this.requestService.automaticNotification(request, old);
				result = new ModelAndView("request/list");
			} catch (final Throwable oops) {
				System.out.println(request);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.createEditModelAndView(request);

				if (oops instanceof DataIntegrityViolationException)
					result = this.createEditModelAndView(request, "request.commit.username");
				else
					result = this.createEditModelAndView(request, "request.commit.error");
			}
		return result;
	}

	// Delete --------------------------------------------------------------------------------------
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int requestId) {
		ModelAndView result;

		try {
			this.requestService.delete(requestId);
			result = new ModelAndView("redirect:../list.do");
		} catch (final Throwable oops) {
			result = this.forbiddenOpperation();
			return result;
		}

		return result;
	}

	// Ancillary methods -----------------------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Request request) {
		ModelAndView result;

		result = this.createEditModelAndView(request, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Request request, final String message) {
		ModelAndView result;

		final Collection<Procession> processions = this.processionService.findAll();

		result = new ModelAndView("request/member/create");
		result.addObject("request", request);
		result.addObject("message", message);
		result.addObject("processions", processions);

		return result;
	}

	protected ModelAndView editModelAndView(final Request request) {
		ModelAndView result;

		result = this.editModelAndView(request, null);

		return result;
	}

	protected ModelAndView editModelAndView(final Request request, final String message) {
		ModelAndView result;

		final Collection<Procession> processions = this.processionService.findAll();

		result = new ModelAndView("request/brotherhood/edit");
		result.addObject("request", request);
		result.addObject("message", message);
		result.addObject("processions", processions);

		return result;
	}

	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}

	/************************************************************************************************/
}
