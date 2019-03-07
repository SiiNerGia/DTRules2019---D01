
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
import services.DropoutService;
import services.EnrolService;
import services.MemberService;
import domain.Dropout;
import domain.Enrol;
import domain.Member;

@Controller
@RequestMapping("/dropout")
public class DropOutController extends AbstractController {

	@Autowired
	private DropoutService		dropoutService;

	@Autowired
	private MemberService		memberService;

	@Autowired
	private EnrolService		enrolService;

	@Autowired
	private BrotherhoodService	brotherhoodService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int brotherhoodId) {
		final ModelAndView result;
		final Dropout dropout;
		Enrol enrol;
		Member member;

		//Usuario que esta logeado
		member = this.memberService.findByPrincipal();

		//Tengo que encontrar el enrol que contiene el id del member
		// y el id de la brotherhood.

		enrol = this.enrolService.findByBrothehoodAndMemberId(brotherhoodId, member.getId());

		//Borro el enrol una vez que lo he encontrado
		this.enrolService.delete(enrol);

		//Ahora tengo que crear el correspondiente dropout

		dropout = this.dropoutService.create();

		//Seteo las propiedades del dropout que corresponden

		dropout.setBrotherhood(this.brotherhoodService.findOne(brotherhoodId));
		dropout.setMember(member);

		//Lo guardo en la base de datos
		this.dropoutService.save(dropout);

		//Creo el modelandview correspondiente que vuelve a la vista

		result = new ModelAndView("brotherhood/list");

		return result;

	}
}
