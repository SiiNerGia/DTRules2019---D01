
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import domain.Float;

@Controller
@RequestMapping("/float")
public class FloatController extends AbstractController {

	//	@Autowired
	//	private FloatService		floatService;

	@Autowired
	private BrotherhoodService	brotherhoodService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// List ------------------------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Float> floats = new ArrayList<Float>();

		try {
			if (this.brotherhoodService.findByPrincipal() != null)
				floats = this.brotherhoodService.findByPrincipal().getFloats();

			result = new ModelAndView("float/list");
			result.addObject("floats", floats);
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
