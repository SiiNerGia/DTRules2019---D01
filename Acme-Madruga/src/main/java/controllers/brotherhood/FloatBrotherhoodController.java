
package controllers.brotherhood;

import java.util.List;

import javax.validation.Valid;

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

import services.FloatService;
import controllers.AbstractController;
import domain.Float;
import domain.Url;

@Controller
@RequestMapping("/float/brotherhood")
public class FloatBrotherhoodController extends AbstractController {

	@Autowired
	private FloatService	floatService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// Create ------------------------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Float f;

		f = this.floatService.create();

		result = this.createEditModelAndView(f);

		return result;
	}

	// Save the float ------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Float f, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			//result = new ModelAndView("float/brotherhood/create");
			//result.addObject("float", float);
			result = this.createEditModelAndView(f);
		} else
			try {
				this.floatService.save(f);
				result = new ModelAndView("redirect:../list.do");
			} catch (final Throwable oops) {
				System.out.println(f);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.createEditModelAndView(f);

				if (oops instanceof DataIntegrityViolationException)
					result = this.createEditModelAndView(f, "float.commit.username");
				else
					result = this.createEditModelAndView(f, "float.commit.error");
			}
		return result;
	}

	// Edit ------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int floatId) {
		ModelAndView result;
		Float f;

		try {
			f = this.floatService.findOne(floatId);
			Assert.notNull(f);
		} catch (final Throwable oops) {
			result = this.forbiddenOpperation();
			return result;
		}

		result = this.createEditModelAndView(f);

		return result;
	}

	// Delete --------------------------------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int floatId) {
		ModelAndView result;

		try {
			this.floatService.delete(floatId);
			result = new ModelAndView("redirect:../list.do");
		} catch (final Throwable oops) {
			result = this.forbiddenOpperation();
			return result;
		}

		return result;
	}

	// Picture  ------------------------------------------------------------------------------------
	@RequestMapping(value = "/addPicture", method = RequestMethod.GET)
	public ModelAndView addPicture(@RequestParam final int floatId) {
		ModelAndView result;
		final Url url;

		try {
			url = new Url();
			result = new ModelAndView("float/brotherhood/addPicture");
			result.addObject("url", url);
			result.addObject("floatId", floatId);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value = "/deletePicture", method = RequestMethod.GET)
	public ModelAndView deletePicture(@RequestParam final String link, @RequestParam final int floatId) {
		ModelAndView result;
		try {
			final Float c = this.floatService.findOne(floatId);
			for (final Url picture : c.getPictures())
				if (picture.getLink().equals(link)) {
					c.getPictures().remove(picture);
					break;
				}
			result = this.createEditModelAndView(c);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	// SAVE ------------------------------------------------------------------------------------
	@RequestMapping(value = "/addPicture", method = RequestMethod.POST, params = "save")
	public ModelAndView savePicture(@RequestParam final int floatId, @Valid final Url url, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = new ModelAndView("float/brotherhood/addPicture");
			result.addObject("url", url);
			result.addObject("floatId", floatId);
		} else
			try {
				Float c = this.floatService.findOne(floatId);
				c.getPictures().add(url);
				c = this.floatService.save(c);
				result = this.createEditModelAndView(c);
			} catch (final Throwable oops) {
				System.out.println(url);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = new ModelAndView("float/brotherhood/addPicture");
				result.addObject("url", url);
				result.addObject("floatId", floatId);
			}
		return result;
	}

	// Ancillary methods -----------------------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Float f) {
		ModelAndView result;

		result = this.createEditModelAndView(f, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Float f, final String message) {
		ModelAndView result;

		result = new ModelAndView("float/brotherhood/edit");
		result.addObject("f", f);
		result.addObject("message", message);

		return result;
	}

	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}

	/************************************************************************************************/
}
