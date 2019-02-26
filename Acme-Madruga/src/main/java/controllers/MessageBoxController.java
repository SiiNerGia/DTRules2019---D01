
package controllers;

import java.util.Collection;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.MessageBoxService;
import domain.Actor;
import domain.MessageBox;

@Controller
@RequestMapping("/messageBox")
public class MessageBoxController extends AbstractController {

	@Autowired
	private MessageBoxService	messageBoxService;

	@Autowired
	private ActorService		actorService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		JOptionPane.showMessageDialog(null, "Forbidden operation");
		return new ModelAndView("redirect:/");
	}

	// List -------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<MessageBox> messageBoxes;

		messageBoxes = this.messageBoxService.findAllByActor(this.actorService.findByPrincipal().getId());

		result = new ModelAndView("messageBox/list");
		result.addObject("messageBoxes", messageBoxes);
		result.addObject("requestURI", "messageBox/list.do");

		return result;
	}
	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		MessageBox messageBox;

		messageBox = this.messageBoxService.create();
		result = this.createEditModelAndView(messageBox);

		return result;
	}

	// Edition -------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int messageBoxID) {
		ModelAndView result;
		MessageBox messageBox;

		try {
			final Actor principal = this.actorService.findByPrincipal();
			messageBox = this.messageBoxService.findOne(messageBoxID);
			Assert.isTrue(principal.getMessageBoxes().contains(messageBox));
			Assert.isTrue(messageBox.getIsSystemBox() == false);
		} catch (final Exception e) {
			result = this.forbiddenOperation();
			return result;
		}

		result = this.createEditModelAndView(messageBox);

		return result;
	}

	// Save -------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final MessageBox messageBox, final BindingResult binding) {
		ModelAndView result;
		MessageBox msgBox;
		if (binding.hasErrors())
			result = this.createEditModelAndView(messageBox);
		else
			try {
				try {
					final Actor principal = this.actorService.findByPrincipal();
					if (messageBox.getId() != 0) {
						msgBox = this.messageBoxService.findOne(messageBox.getId());
						Assert.isTrue(principal.getMessageBoxes().contains(msgBox));
						Assert.isTrue(messageBox.getIsSystemBox() == false);
					}
				} catch (final Exception e) {
					result = this.forbiddenOperation();
					return result;
				}

				this.messageBoxService.save(messageBox);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(messageBox, "messageBox.commit.error");
			}

		return result;
	}

	// Delete ------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final MessageBox messageBox, final BindingResult binding) {
		ModelAndView result;
		MessageBox msgBox;

		try {
			try {
				final Actor principal = this.actorService.findByPrincipal();
				msgBox = this.messageBoxService.findOne(messageBox.getId());
				Assert.isTrue(principal.getMessageBoxes().contains(msgBox));

			} catch (final Exception e) {
				result = this.forbiddenOperation();
				return result;
			}
			this.messageBoxService.delete(messageBox);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(messageBox, "messageBox.commit.error");
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final MessageBox messageBox) {
		ModelAndView result;

		result = this.createEditModelAndView(messageBox, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final MessageBox messageBox, final String message) {
		ModelAndView result;

		result = new ModelAndView("messageBox/edit");
		result.addObject("messageBox", messageBox);
		result.addObject("message", message);

		return result;
	}

	private ModelAndView forbiddenOperation() {
		JOptionPane.showMessageDialog(null, "Forbidden operation");
		return new ModelAndView("redirect:/messageBox/list.do");
	}
}
