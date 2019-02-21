package controllers;

import java.util.Collection;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Brotherhood;
import domain.Procession;
import services.BrotherhoodService;
import services.ProcessionService;

@Controller
@RequestMapping("/procession")
public class ProcessionController extends AbstractController {

	@Autowired
	private ProcessionService processionService;

	@Autowired
	private BrotherhoodService brotherhoodService;
	
	
	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	
	// List ------------------------------------------------------------------------------------
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
	
	
	// Ancillary methods -----------------------------------------------------------------------
	
	
	
	
	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}
}
