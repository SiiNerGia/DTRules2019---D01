
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
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
	@RequestMapping(value = "/member/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final MemberForm memberForm, final BindingResult binding) {
		ModelAndView result;
		String password;

		//		Member usuario = memberService.findByPrincipal();
		//		
		//		usuario.setAddress(member.getAddress());
		//		usuario.setEmail(member.getEmail());
		//		usuario.setMiddleName(member.getMiddleName());
		//		usuario.setName(member.getName());
		//		usuario.setPhoneNumber(member.getPhoneNumber());
		//		usuario.setSurname(member.getSurname());
		//		usuario.setUsername(member.getUsername());
		//		
		//		
		//		usuario.getUserAccount().setPassword(usuario.getUserAccount().getPassword());
		//		usuario.getUserAccount().setUsername(member.getUserAccount().getUsername());

		try {

			final Member member = this.memberService.reconstruct(memberForm, binding);

			if (binding.hasErrors())

				result = this.createEditModelAndView(memberForm);

			else {

				// member.getUserAccount().setId(LoginService.getPrincipal().getId());
				password = Md5.encodeMd5(member.getUserAccount().getPassword());
				member.getUserAccount().setPassword(password);
				this.memberService.save(member);
				result = new ModelAndView("redirect:/welcome/index"); //TODO: mirar a donde hay que redireccionar esto

			}

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(memberForm, "member.commit.error");
		}
		return result;
	}
	@RequestMapping(value = "member/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		final ModelAndView result;
		Member member;
		member = this.memberService.findByPrincipal();

		Assert.notNull(member);

		final MemberForm memberForm = new MemberForm();

		memberForm.setAddress(member.getAddress());
		memberForm.setEmail(member.getEmail());
		memberForm.setMiddleName(member.getMiddleName());
		memberForm.setName(member.getName());
		memberForm.setPhoneNumber(member.getPhoneNumber());
		memberForm.setPhoto(member.getPhoto());
		memberForm.setSurname(member.getSurname());
		memberForm.setUserAccount(member.getUserAccount());

		result = this.createEditModelAndView(memberForm);

		return result;
	}
	protected ModelAndView createEditModelAndView(final MemberForm memberForm) {
		ModelAndView result;

		result = this.createEditModelAndView(memberForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final MemberForm memberForm, final String messageCode) {
		final ModelAndView result;

		result = new ModelAndView("member/member/edit");
		result.addObject("memberForm", memberForm);

		result.addObject("message", messageCode);

		return result;

	}

	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}
}
