package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MiscController extends AbstractController {
		

	@RequestMapping("/cookies")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("cookies");

		return result;
	}

	@RequestMapping("/terms")
	public ModelAndView action1() {
		ModelAndView result;

		result = new ModelAndView("terms");

		return result;
	}

}