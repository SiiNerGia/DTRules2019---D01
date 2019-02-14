
package controllers;

import java.util.List;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.hibernate.criterion.Example;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import domain.Actor;

@Controller
@RequestMapping("/example")
public class ExampleController extends AbstractController {

	@Autowired
	private ExampleService	exampleService;

	@Autowired
	private ActorService	actorService;


	//Metodo que coge las excepciones cuando se introducen datos incorrectos por URL: example/edit.do?exampleId=gubtejfd
	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		//Aqui podemos poner lo que queramos, en un principio esta puesto que rediriga a la página principal del sistema
		return new ModelAndView("redirect:/");
	}

	// Delete -----------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int exampleID) {	//Metodo de ejemplo para delete, adaptarlo para cada clase
		ModelAndView result;
		Example example = null;
		try {
			example = this.exampleService.findOne(exampleID);
			final Actor a = this.ActorService.findByPrincipal();

			if (!a.getExamples().contains(example) || a == null)
				return this.forbiddenOpperation();	//Mirar este metodo al final del controller
		} catch (final Throwable oops) {
			return this.forbiddenOpperation();
		}

		try {
			a.getExamples().remove(example);
			this.exampleService.delete(example);
			result = new ModelAndView("redirect:/exaple/actor/list.do");
		} catch (final Throwable oops) {
			System.out.println(example);
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			oops.printStackTrace();
			result = this.createEditModelAndView(example, "example.commit.error");
		}

		return result;
	}
	// Create & Edit -----------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int workPlanId) {
		final Example example;
		try {
			final Actor a = this.ActorService.findByPrincipal();
			example = this.exampleService.create();
			example.setActor(a);

			return this.createEditModelAndView(example);
		} catch (final Throwable oops) {
			return this.forbiddenOpperation();
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Example example, final BindingResult binding) {
		ModelAndView result;
		final Actor a = this.ActorService.findByPrincipal();

		if (!a.getExamples().contains(example))
			return this.forbiddenOpperation();
		else if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			result = this.createEditModelAndView(example);

		} else
			try {
				saved = this.exampleService.save(example);
				a.getExamples().add(saved);
				result = new ModelAndView("redirect:/exaple/actor/list.do");
			} catch (final Throwable oops) {
				System.out.println(example);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.createEditModelAndView(example);
			}
		return result;
	}

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Example example) {
		ModelAndView result;

		result = this.createEditModelAndView(example, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Example example, final String message) {
		ModelAndView result;

		result = new ModelAndView("example/edit");
		result.addObject("example", example);
		result.addObject("message", message);

		return result;
	}

	private ModelAndView forbiddenOpperation() {	//Método para redigir de forma general cuanndo se haga algo prohibido o surja algun error
													// en el sistema, se debe poner un enlace distinto en cada controller o redirigir al index

		JOptionPane.showMessageDialog(null, "Forbidden operation"); //Muestra una ventana de alerta. PUEDE SALIR DETRÁS, HASTA QUE NO SE 
																	// CIERRE EL SISTEMA QUEDA PAUSADO
		return new ModelAndView("redirect:/example/list.do");
	}
}
