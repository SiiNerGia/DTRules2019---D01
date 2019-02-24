
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.MemberService;
import domain.Member;

@Controller
@RequestMapping("/member")
public class MemberController extends AbstractController {

	@Autowired
	private MemberService	memberService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Member member;

		member = this.memberService.create();

		result = this.createEditModelAndView(member);

		return result;
	}

	//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	//	public ModelAndView save(@Valid final Member member, final BindingResult binding) {
	//		ModelAndView result;
	//
	//		if (binding.hasErrors())
	//			result = this.createEditModelAndView(member);
	//		else
	//			try {
	//				this.memberService.save(member);
	//				result = new ModelAndView("redirect:list.do"); //TODO: mirar a donde hay que redireccionar esto
	//
	//			} catch (final Throwable oops) {
	//				result = this.createEditModelAndView(member, "member.commit.error");
	//			}
	//		return result;
	//	}

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
