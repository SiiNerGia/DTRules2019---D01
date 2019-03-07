
package controllers;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.EnrolService;
import services.MemberService;
import domain.Enrol;
import domain.Member;

@Controller
@RequestMapping("/enrol")
public class EnrolController extends AbstractController {

	@Autowired
	private EnrolService		enrolService;

	@Autowired
	private BrotherhoodService	brotherhoodService;

	@Autowired
	private MemberService		memberService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/member/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int brotherhoodId) {
		final ModelAndView result;
		Enrol enrol;
		Member user;

		user = this.memberService.findByPrincipal();

		enrol = this.enrolService.create();

		enrol.setBrotherhood(this.brotherhoodService.findOne(brotherhoodId));
		enrol.setMember(user);

		this.enrolService.save(enrol);

		result = new ModelAndView("redirect:/");

		return result;
	}
}
