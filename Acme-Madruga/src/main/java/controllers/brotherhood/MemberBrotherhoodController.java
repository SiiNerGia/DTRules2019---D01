
package controllers.brotherhood;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.EnrolService;
import services.MemberService;
import services.PositionService;
import controllers.AbstractController;
import domain.Brotherhood;
import domain.Enrol;
import domain.Member;
import domain.Position;

@Controller
@RequestMapping("/member/brotherhood")
public class MemberBrotherhoodController extends AbstractController {

	@Autowired
	private MemberService		memberService;

	@Autowired
	private BrotherhoodService	brotherhoodService;

	@Autowired
	private EnrolService		enrolService;

	@Autowired
	private PositionService		positionService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}
	/******** MAIN METHODS *********/
	// List ------------------------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Enrol> enrols;
		Brotherhood brotherhood;

		try {
			brotherhood = this.brotherhoodService.findByPrincipal();
			enrols = brotherhood.getEnrols();
			result = new ModelAndView("member/list");
			result.addObject("enrols", enrols);
			result.addObject("bro", 1);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	// Display ------------------------------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int memberId) {
		ModelAndView result;
		Member member;
		Brotherhood brotherhood;

		try {
			brotherhood = this.brotherhoodService.findByPrincipal();
			member = this.memberService.findOne(memberId);

			Assert.isTrue(this.memberService.findByBrotherhood(brotherhood).contains(member));

			result = new ModelAndView("member/brotherhood/display");
			result.addObject("member", member);

		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = this.forbiddenOpperation();
		}

		return result;
	}

	// Select Position ------------------------------------------------------------------------------------
	@RequestMapping(value = "/selectPosition", method = RequestMethod.GET)
	public ModelAndView selectPosition(@RequestParam final int enrolId) {
		ModelAndView result;
		Enrol enrol;
		Brotherhood brotherhood;

		try {
			brotherhood = this.brotherhoodService.findByPrincipal();
			enrol = this.enrolService.findOne(enrolId);
			final Collection<Position> positions = this.positionService.findAll();

			Assert.isTrue(brotherhood.getEnrols().contains(enrol));
			//			enrol.setBrotherhood(null);
			//			enrol.setMember(null);

			result = new ModelAndView("member/brotherhood/selectPosition");
			result.addObject("positions", positions);
			result.addObject("enrol", enrol);

		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			oops.printStackTrace();
			result = this.forbiddenOpperation();
		}

		return result;
	}

	// Save enrol ------------------------------------------------------------------------------------
	@RequestMapping(value = "/selectPosition", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Enrol enrol, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors) {
				System.out.println(e.toString());
			}

			result = new ModelAndView("member/brotherhood/selectPosition");
			result.addObject("positions", this.positionService.findAll());
			result.addObject("enrol", enrol);
		} else {
			try {
				this.enrolService.save(enrol);
				result = new ModelAndView("redirect:/member/brotherhood/list.do");
			} catch (final Throwable oops) {
				System.out.println(enrol);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				result = new ModelAndView("member/brotherhood/selectPosition");
				result.addObject("positions", this.positionService.findAll());
				result.addObject("enrol", enrol);
			}
		}
		return result;
	}

	// Disenroll ------------------------------------------------------------------------------------
	@RequestMapping(value = "/disenroll", method = RequestMethod.GET)
	public ModelAndView disenroll(@RequestParam final int enrolId) {
		ModelAndView result;
		Enrol enrol;
		Brotherhood brotherhood;

		try {
			brotherhood = this.brotherhoodService.findByPrincipal();
			enrol = this.enrolService.findOne(enrolId);

			Assert.isTrue(brotherhood.getEnrols().contains(enrol));

			this.enrolService.delete(enrol);

			result = new ModelAndView("redirect:/member/brotherhood/list.do");

		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			oops.printStackTrace();
			result = this.forbiddenOpperation();
		}

		return result;
	}
	/** ANCILLARY METHODS **/

	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}

	/************************************************************************************************/
}
