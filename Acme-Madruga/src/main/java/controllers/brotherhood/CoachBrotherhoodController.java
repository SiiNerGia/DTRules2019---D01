
package controllers.brotherhood;

import controllers.AbstractController;
import domain.Coach;
import domain.Request;
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
import services.CoachService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/coach/brotherhood")
public class CoachBrotherhoodController extends AbstractController {

	@Autowired
	private CoachService coachService;

	@Autowired
	private BrotherhoodService	brotherhoodService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}


	// Create ------------------------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Coach coach;

		coach = this.coachService.create();

		result = this.createEditModelAndView(coach);

		return result;
	}

	// Save the coach ------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Coach coach, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = new ModelAndView("coach/brotherhood/create");
			result.addObject("coach", coach);
		}

		else
			try {
				this.coachService.save(coach);
				result = new ModelAndView("coach/list");
			} catch (final Throwable oops) {
				System.out.println(coach);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.createEditModelAndView(coach);

				if (oops instanceof DataIntegrityViolationException)
					result = this.createEditModelAndView(coach, "coach.commit.username");
				else
					result = this.createEditModelAndView(coach, "coach.commit.error");
			}
		return result;
	}

	// Edit ------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int coachId) {
		ModelAndView result;
		Coach coach;

		try {
			coach = this.coachService.findOne(coachId);
			Assert.notNull(coach);
		} catch (final Throwable oops) {
			result = this.forbiddenOpperation();
			return result;
		}

		result = this.createEditModelAndView(coach);

		return result;
	}

	// Delete --------------------------------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int coachId) {
		ModelAndView result;

		try {
			this.coachService.delete(coachId);
			result = new ModelAndView("coach/list");
		} catch (final Throwable oops) {
			result = this.forbiddenOpperation();
			return result;
		}

		return result;
	}


	// Ancillary methods -----------------------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Coach coach) {
		ModelAndView result;

		result = this.createEditModelAndView(coach, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Coach coach, final String message) {
		ModelAndView result;

		result = new ModelAndView("coach/brotherhood/edit");
		result.addObject("coach", coach);
		result.addObject("message", message);

		return result;
	}


	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}

	/************************************************************************************************/
}
