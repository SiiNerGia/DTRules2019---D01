
package controllers;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FinderService;
import services.MemberService;
import domain.Finder;
import domain.Member;
import domain.Procession;

@Controller
@RequestMapping("/finder/member")
public class FinderController extends AbstractController {

	@Autowired
	private FinderService	finderService;

	@Autowired
	private MemberService	memberService;


	//@Autowired
	//private AreaService		areaService;

	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// Edit -----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;

		System.out.println("Hola, entrando edit");

		final Member member = this.memberService.findByPrincipal();
		final Finder finder = member.getFinder();

		result = this.createEditModelAndView(finder);
		//result.addObject("categories", this.areaService.findAll());

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Finder finder, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			result = this.createEditModelAndView(finder);

		} else
			try {
				this.finderService.checkChanges(finder);
				System.out.println("Exito");
				result = new ModelAndView("redirect:/finder/member/result.do");
			} catch (final Throwable oops) {
				System.out.println(finder);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.createEditModelAndView(finder);
			}
		return result;
	}

	// Search -----------------------------------------------------------
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public ModelAndView result() {
		ModelAndView result;
		System.out.println("Controller result");
		try {
			final Member member = this.memberService.findByPrincipal();
			final Finder finder = member.getFinder();
			final Collection<Procession> processions = this.finderService.getResults(finder);

			result = new ModelAndView("procession/list");
			result.addObject("processions", processions);
			result.addObject("requestURI", "finder/member/result.do");
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = new ModelAndView("redirect:/");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Finder finder) {
		ModelAndView result;

		result = this.createEditModelAndView(finder, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Finder finder, final String message) {
		ModelAndView result;

		result = new ModelAndView("finder/member/edit");
		result.addObject("finder", finder);
		result.addObject("message", message);

		return result;
	}
}
