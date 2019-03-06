
package controllers;

import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.MemberService;
import utilities.Md5;
import domain.Member;
import forms.MemberForm;

@Controller
@RequestMapping("/member")
public class MemberController extends AbstractController {

	@Autowired
	private MemberService	memberService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		MemberForm memberForm;

		try {
			//Se crea un memberform vacio
			memberForm = new MemberForm();
			result = new ModelAndView("member/create");
			result.addObject("memberForm", memberForm);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final MemberForm memberForm, final BindingResult binding) {
		ModelAndView result;
		Member member;
		String password;

		member = this.memberService.reconstruct(memberForm, binding);
		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = new ModelAndView("member/create");
			result.addObject("memberForm", memberForm);
		}

		else
			try {
				password = Md5.encodeMd5(member.getUserAccount().getPassword());
				member.getUserAccount().setPassword(password);
				this.memberService.save(member);
				result = new ModelAndView("redirect:../security/login.do");
			} catch (final Throwable oops) {
				System.out.println(member);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.createEditModelAndView(memberForm);

				if (oops instanceof DataIntegrityViolationException)
					result = this.createEditModelAndView(memberForm, "member.duplicated.username");
				else
					result = this.createEditModelAndView(memberForm, "member.registration.error");
			}
		return result;
	}

	// SAVE ------------------------------------------------------------------------------------
	@RequestMapping(value = "/member/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(final Member prune, final BindingResult binding) {
		ModelAndView result;
		final Member member;

		member = this.memberService.reconstruct(prune, binding);

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());

			result = new ModelAndView("member/member/edit");
			result.addObject("member", prune);
		}

		else
			try {
				this.memberService.save(member);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				System.out.println(member);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = this.editModelAndView(member, "member.registration.error");
			}
		return result;
	}

	@RequestMapping(value = "/member/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		final Member member;

		try {
			member = this.memberService.findByPrincipal();

			// Set relations to null to use as a prune object

			member.setFinder(null);
			member.setEnrols(null);
			member.setDropouts(null);
			member.setUserAccount(null);

			result = new ModelAndView("member/member/edit");
			result.addObject("member", member);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}
	protected ModelAndView createEditModelAndView(final MemberForm memberForm) {
		ModelAndView result;

		result = this.createEditModelAndView(memberForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final MemberForm memberForm, final String messageCode) {
		final ModelAndView result;

		result = new ModelAndView("member/create");
		result.addObject("memberForm", memberForm);

		result.addObject("message", messageCode);

		return result;

	}
	protected ModelAndView editModelAndView(final Member member) {
		ModelAndView result;

		result = this.editModelAndView(member, null);

		return result;
	}

	protected ModelAndView editModelAndView(final Member member, final String message) {
		ModelAndView result;

		result = new ModelAndView("member/member/edit");
		result.addObject("member", member);
		result.addObject("message", message);

		return result;
	}

	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}
}
