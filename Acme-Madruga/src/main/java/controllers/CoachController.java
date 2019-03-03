
package controllers;

import java.util.Collection;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CoachService;
import domain.Coach;

@Controller
@RequestMapping("/coach")
public class CoachController extends AbstractController {

	@Autowired
	private CoachService	coachService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// List ------------------------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Coach> coaches;

		try {
			coaches = this.coachService.findAll();
			result = new ModelAndView("coach/list");
			result.addObject("coaches", coaches);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}

	/************************************************************************************************/
}
