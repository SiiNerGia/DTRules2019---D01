
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BrotherhoodService;
import services.EnrolService;

@Controller
@RequestMapping("/dropout")
public class EnrolController extends AbstractController {

	@Autowired
	private EnrolService		enrolService;

	@Autowired
	private BrotherhoodService	brotherhoodService;
}
