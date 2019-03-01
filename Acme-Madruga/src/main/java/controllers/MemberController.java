
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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


	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final MemberForm memberForm, final BindingResult binding) {
		ModelAndView result;
		String password;
		Member member;

		member = this.memberService.reconstruct(memberForm, binding);

		if (binding.hasErrors())

			result = this.createEditModelAndView(member);
		else
			try {
				password = Md5.encodeMd5(member.getUserAccount().getPassword());
				member.getUserAccount().setPassword(password);
				this.memberService.save(member);
				result = new ModelAndView("redirect:/**"); //TODO: mirar a donde hay que redireccionar esto

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(member, "member.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int memberId) {
		final ModelAndView result;
		Member member;

		member = this.memberService.findOne(memberId);
		Assert.notNull(member);
		result = this.createEditModelAndView(member);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Member member) {
		ModelAndView result;

		result = this.createEditModelAndView(member, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Member member, final String messageCode) {
		final ModelAndView result;

		result = new ModelAndView("member/create");
		result.addObject("member", member);

		result.addObject("message", messageCode);

		return result;

	}
}
