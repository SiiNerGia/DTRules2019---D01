
package controllers.administrator;

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

import services.AdministratorService;
import services.AreaService;
import controllers.AbstractController;
import domain.Administrator;
import domain.Area;
import domain.Url;

@Controller
@RequestMapping("/area/administrator")
public class AreaAdministratorController extends AbstractController {

	@Autowired
	private AreaService				areaService;

	@Autowired
	private AdministratorService	administratorService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// Create ------------------------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Area area;

		area = this.areaService.create();

		result = this.createEditModelAndView(area);

		return result;
	}

	// Save the area ------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Area area, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = new ModelAndView("area/administrator/create");
			result.addObject("area", area);
		}

		else
			try {
				this.areaService.save(area);
				result = new ModelAndView("area/list");
			} catch (final Throwable oops) {
				System.out.println(area);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.createEditModelAndView(area);

				if (oops instanceof DataIntegrityViolationException)
					result = this.createEditModelAndView(area, "area.commit.username");
				else
					result = this.createEditModelAndView(area, "area.commit.error");
			}
		return result;
	}

	// Edit ------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int areaId) {
		ModelAndView result;
		Area area;

		try {
			area = this.areaService.findOne(areaId);
			Assert.notNull(area);
		} catch (final Throwable oops) {
			result = this.forbiddenOpperation();
			return result;
		}

		result = this.createEditModelAndView(area);

		return result;
	}

	// Delete --------------------------------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int areaId) {
		ModelAndView result;

		try {
			final Area area = this.areaService.findOne(areaId);
			this.areaService.delete(area);
			result = new ModelAndView("area/list");
		} catch (final Throwable oops) {
			result = this.forbiddenOpperation();
			return result;
		}

		return result;
	}

	// Picture  ------------------------------------------------------------------------------------
	@RequestMapping(value = "/addPicture", method = RequestMethod.GET)
	public ModelAndView addPicture() {
		ModelAndView result;
		final Url url;

		try {
			url = new Url();
			result = new ModelAndView("area/administrator/addPicture");
			result.addObject("url", url);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value = "/deletePicture", method = RequestMethod.GET)
	public ModelAndView deletePicture(@RequestParam final String link, @RequestParam final int areaId) {
		ModelAndView result;
		try {
			final Area a = this.areaService.findOne(areaId);
			for (final Url picture : a.getPictures())
				if (picture.getLink().equals(link)) {
					a.getPictures().remove(picture);
					break;
				}
			result = this.createEditModelAndView(a);
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
	public ModelAndView savePicture(@RequestParam final int areaId, @Valid final Url url, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = new ModelAndView("area/administrator/addPicture");
			result.addObject("url", url);
			result.addObject("areaId", areaId);
		}

		else
			try {
				Area c = this.areaService.findOne(areaId);
				c.getPictures().add(url);
				c = this.areaService.save(c);
				result = this.createEditModelAndView(c);
			} catch (final Throwable oops) {
				System.out.println(url);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = new ModelAndView("area/administrator/addPicture");
				result.addObject("url", url);
				result.addObject("areaId", areaId);
			}
		return result;
	}

	// Ancillary methods -----------------------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Area area) {
		ModelAndView result;

		result = this.createEditModelAndView(area, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Area area, final String message) {
		ModelAndView result;

		result = new ModelAndView("area/administrator/edit");
		result.addObject("area", area);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView editModelAndView(final Administrator administrator, final String message) {
		ModelAndView result;

		result = new ModelAndView("administrator/edit");
		result.addObject("administrator", administrator);
		result.addObject("message", message);

		return result;
	}

	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}

	/************************************************************************************************/
}
