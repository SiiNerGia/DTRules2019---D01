
package controllers;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.MemberService;
import utilities.Md5;
import domain.Brotherhood;
import domain.Member;
import domain.Url;
import forms.BrotherhoodForm;

@Controller
@RequestMapping("/brotherhood")
public class BrotherhoodController extends AbstractController {

	@Autowired
	private BrotherhoodService	brotherhoodService;

	@Autowired
	private MemberService		memberService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// List ------------------------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Brotherhood> bros;

		try {
			bros = this.brotherhoodService.findAll();
			result = new ModelAndView("brotherhood/list");
			result.addObject("brotherhoods", bros);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	// List ------------------------------------------------------------------------------------
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public ModelAndView memberList() {
		ModelAndView result;
		final Collection<Brotherhood> belonging;
		final Collection<Brotherhood> belonged;

		try {
			final Member member = this.memberService.findByPrincipal();

			belonging = this.brotherhoodService.findAllMemberBelongs(member);
			belonged = this.brotherhoodService.findAllMemberBelonged(member);

			result = new ModelAndView("brotherhood/member/list");
			result.addObject("belonging", belonging);
			result.addObject("belonged", belonged);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	// Register ------------------------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		BrotherhoodForm bro;

		try {
			bro = new BrotherhoodForm();
			result = new ModelAndView("brotherhood/create");
			result.addObject("brotherhoodForm", bro);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	// Save the new brotherhood ------------------------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final BrotherhoodForm brotherhoodForm, final BindingResult binding) {
		ModelAndView result;
		Brotherhood brotherhood;
		String password;

		brotherhood = this.brotherhoodService.reconstruct(brotherhoodForm, binding);
		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = new ModelAndView("brotherhood/create");
			result.addObject("brotherhoodForm", brotherhoodForm);
		}

		else
			try {
				password = Md5.encodeMd5(brotherhood.getUserAccount().getPassword());
				brotherhood.getUserAccount().setPassword(password);
				this.brotherhoodService.save(brotherhood);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (final Throwable oops) {
				System.out.println(brotherhood);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.createEditModelAndView(brotherhoodForm);

				if (oops instanceof DataIntegrityViolationException)
					result = this.createEditModelAndView(brotherhoodForm, "brotherhood.duplicated.username");
				else
					result = this.createEditModelAndView(brotherhoodForm, "brotherhood.registration.error");
			}
		return result;
	}

	// Edit ------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Brotherhood bro;

		try {
			bro = this.brotherhoodService.findByPrincipal();

			// Set relations to null to use as a prune object
			bro.setCoaches(null);
			bro.setEnrols(null);
			bro.setProcessions(null);
			bro.setUserAccount(null);

			result = new ModelAndView("brotherhood/edit");
			result.addObject("brotherhood", bro);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	// SAVE ------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(final Brotherhood prune, final BindingResult binding) {
		ModelAndView result;
		final Brotherhood brotherhood;

		brotherhood = this.brotherhoodService.reconstruct(prune, binding);

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = new ModelAndView("brotherhood/edit");
			result.addObject("brotherhood", prune);
		}

		else
			try {
				this.brotherhoodService.save(brotherhood);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				System.out.println(brotherhood);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.editModelAndView(brotherhood, "brotherhood.registration.error");
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
			result = new ModelAndView("brotherhood/addPicture");
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
	public ModelAndView deletePicture(@RequestParam final String link) {
		ModelAndView result;
		try {
			final Brotherhood bro = this.brotherhoodService.findByPrincipal();
			for (final Url picture : bro.getPictures())
				if (picture.getLink().equals(link)) {
					bro.getPictures().remove(picture);
					break;
				}
			result = this.editModelAndView(bro);
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
	public ModelAndView savePicture(@Valid final Url url, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = new ModelAndView("brotherhood/addPicture");
			result.addObject("url", url);
		}

		else
			try {
				Brotherhood brotherhood = this.brotherhoodService.findByPrincipal();
				brotherhood.getPictures().add(url);
				brotherhood = this.brotherhoodService.save(brotherhood);
				result = this.editModelAndView(brotherhood);
			} catch (final Throwable oops) {
				System.out.println(url);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = new ModelAndView("brotherhood/addPicture");
				result.addObject("url", url);
			}
		return result;
	}

	// Ancillary methods -----------------------------------------------------------------------
	protected ModelAndView createEditModelAndView(final BrotherhoodForm brotherhoodForm) {
		ModelAndView result;

		result = this.createEditModelAndView(brotherhoodForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final BrotherhoodForm brotherhoodForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("brotherhood/create");
		result.addObject("brotherhoodForm", brotherhoodForm);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView editModelAndView(final Brotherhood brotherhood) {
		ModelAndView result;

		result = this.editModelAndView(brotherhood, null);

		return result;
	}

	protected ModelAndView editModelAndView(final Brotherhood brotherhood, final String message) {
		ModelAndView result;

		result = new ModelAndView("brotherhood/edit");
		result.addObject("brotherhood", brotherhood);
		result.addObject("message", message);

		return result;
	}

	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}
}
