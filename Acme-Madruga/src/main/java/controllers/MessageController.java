
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Message;
import domain.MessageBox;
import services.ActorService;
import services.MessageBoxService;
import services.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private MessageBoxService messageBoxService;

	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		JOptionPane.showMessageDialog(null, "Forbidden operation");
		return new ModelAndView("redirect:/");
	}

	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Collection.class, "recipients", new CustomCollectionEditor(Collection.class) {

			@Override
			protected Object convertElement(final Object element) {
				Integer id = null;

				if (element instanceof String && !((String) element).equals(""))
					// From the JSP 'element' will be a String
					try {
						id = Integer.parseInt((String) element);
					} catch (final NumberFormatException e) {
						System.out.println("Element was " + ((String) element));
						e.printStackTrace();
					}
				else if (element instanceof Integer)
					// From the database 'element' will be a Long
					id = (Integer) element;

				return id != null ? MessageController.this.actorService.findOne(id) : null;
			}
		});
	}

	// List -------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int messageBoxID) {
		final ModelAndView result;
		Collection<Message> messages;
		MessageBox msgBox;

		try {
			Actor principal = this.actorService.findByPrincipal();
			msgBox = this.messageBoxService.findOne(messageBoxID);
			Assert.isTrue(principal.getMessageBoxes().contains(msgBox));
		} catch (final Exception e) {
			result = this.forbiddenOperation();
			return result;
		}

		messages = this.messageService.findAllByMessageBox(messageBoxID);

		result = new ModelAndView("message/list");
		result.addObject("messageBox", messageBoxID);
		result.addObject("messages", messages);
		result.addObject("requestURI", "message/list.do");

		return result;
	}

	// Create -----------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Message mesage;

		mesage = this.messageService.create();
		mesage.setIsNotification(false);
		result = this.createModelAndView(mesage);

		return result;
	}

	// Create Broadcast
	// ------------------------------------------------------------------------
	@RequestMapping(value = "/broadcast", method = RequestMethod.GET)
	public ModelAndView broadcast() {
		ModelAndView result;
		Message mesage;

		mesage = this.messageService.create();
		mesage.setIsNotification(true);

		result = new ModelAndView("message/broadcast");
		result.addObject("mesage", mesage);

		return result;
	}

	// Send Broadcast
	// -------------------------------------------------------------
	@RequestMapping(value = "/broadcast", method = RequestMethod.POST, params = "send")
	public ModelAndView sendBroadcast(@ModelAttribute("mesage") @Valid Message mesage, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			List<ObjectError> errors = binding.getAllErrors();
			for (ObjectError e : errors)
				System.out.println(e.toString());
			result = new ModelAndView("message/broadcast");
			result.addObject("mesage", mesage);
		} else
			try {
				Collection<Actor> recipients = this.actorService.findAll();
				recipients.remove(this.actorService.findByPrincipal());
				mesage.setRecipients(recipients);
				this.messageService.save(mesage);
				int messageBoxID = this.actorService.findByPrincipal().getMessageBox("out").getId();
				result = new ModelAndView("redirect:list.do?messageBoxID=" + messageBoxID + "");
			} catch (final Throwable oops) {
				result = new ModelAndView("message/broadcast");
				result.addObject("mesage", mesage);
				result.addObject("mesage", "message.commit.error");
			}
		return result;
	}

	// Send -------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("mesage") @Valid final Message mesage, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {	
			List<ObjectError> errors = binding.getAllErrors();
			for (ObjectError e : errors)
				System.out.println(e.toString());
			result = this.createModelAndView(mesage);
		}
		else
			try {
				this.messageService.save(mesage);
				int messageBoxID = this.actorService.findByPrincipal().getMessageBox("out").getId();
				result = new ModelAndView("redirect:list.do?messageBoxID=" + messageBoxID + "");
			} catch (final Throwable oops) {
				result = this.createModelAndView(mesage, "message.commit.error");
			}
		return result;
	}

	// Display ---------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int messageID, @RequestParam int messageBoxID) {
		ModelAndView result;
		Message message;
		MessageBox msgBox;
		Collection<MessageBox> actorBoxes;

		try {
			Actor principal = this.actorService.findByPrincipal();
			msgBox = this.messageBoxService.findOne(messageBoxID);
			message = this.messageService.findOne(messageID);
			Assert.isTrue(principal.getMessageBoxes().contains(msgBox));
			Assert.isTrue(principal.getMessageBox(msgBox.getName()).getMessages().contains(message));
		} catch (final Exception e) {
			result = this.forbiddenOperation();
			return result;
		}

		Collection<MessageBox> messageBoxes = new ArrayList<MessageBox>();
		message = this.messageService.findOne(messageID);
		actorBoxes = this.messageBoxService.findAllByActor(this.actorService.findByPrincipal().getId());
		for (final MessageBox b : actorBoxes)
			if (!b.getMessages().contains(message) && b.getIsSystemBox() == false)
				messageBoxes.add(b);

		result = new ModelAndView("message/display");
		result.addObject("messageBoxes", messageBoxes);
		result.addObject("messageBoxID", messageBoxID);
		result.addObject("mesage", message);
		result.addObject("mesageRecipients", message.getRecipients());

		return result;
	}

	// Delete ------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int messageID, @RequestParam final int messageBoxID) {
		ModelAndView result;
		MessageBox messageBox;
		Message message;

		try {
			try {
				final Actor principal = this.actorService.findByPrincipal();
				messageBox = this.messageBoxService.findOne(messageBoxID);
				message = this.messageService.findOne(messageID);
				Assert.isTrue(principal.getMessageBoxes().contains(messageBox));
				Assert.isTrue(principal.getMessageBox(messageBox.getName()).getMessages().contains(message));

			} catch (final Exception e) {
				result = this.forbiddenOperation();
				return result;
			}
			this.messageService.delete(message, messageBox);
			result = new ModelAndView("redirect:list.do?messageBoxID=" + messageBoxID + "");
			result.addObject("messageBox", messageBoxID);
		} catch (final Throwable oops) {
			message = this.messageService.findOne(messageID);
			result = this.createModelAndView(message, "messageBox.commit.error");
		}

		return result;
	}

	// Move ------------------------------------------------------
	@RequestMapping(value = "/move", method = RequestMethod.GET)
	public ModelAndView move(@RequestParam final int messageID, @RequestParam final int srcBoxID,
			@RequestParam final int destBoxID) {
		ModelAndView result;
		MessageBox srcBox;
		MessageBox destBox;
		Message message;

		try {
			try {
				final Actor principal = this.actorService.findByPrincipal();
				srcBox = this.messageBoxService.findOne(srcBoxID);
				destBox = this.messageBoxService.findOne(destBoxID);
				message = this.messageService.findOne(messageID);
				Assert.isTrue(principal.getMessageBoxes().contains(srcBox));
				Assert.isTrue(principal.getMessageBox(srcBox.getName()).getMessages().contains(message));
				Assert.isTrue(principal.getMessageBoxes().contains(destBox));

			} catch (final Exception e) {
				result = this.forbiddenOperation();
				return result;
			}
			this.messageService.moveMessage(message, srcBox, destBox);
			result = new ModelAndView("redirect:list.do?messageBoxID=" + destBoxID + "");
			result.addObject("messageBox", srcBoxID);
		} catch (final Throwable oops) {
			message = this.messageService.findOne(messageID);
			result = this.createModelAndView(message, "messageBox.commit.error");
		}

		return result;
	}

	// Copy ------------------------------------------------------
	@RequestMapping(value = "/copy", method = RequestMethod.GET)
	public ModelAndView copy(@RequestParam final int messageID, @RequestParam final int srcBoxID,
			@RequestParam final int destBoxID) {
		ModelAndView result;
		MessageBox srcBox;
		MessageBox destBox;
		Message message;

		try {
			try {
				final Actor principal = this.actorService.findByPrincipal();
				srcBox = this.messageBoxService.findOne(srcBoxID);
				destBox = this.messageBoxService.findOne(destBoxID);
				message = this.messageService.findOne(messageID);
				Assert.isTrue(principal.getMessageBoxes().contains(srcBox));
				Assert.isTrue(principal.getMessageBox(srcBox.getName()).getMessages().contains(message));
				Assert.isTrue(principal.getMessageBoxes().contains(destBox));

			} catch (final Exception e) {
				result = this.forbiddenOperation();
				return result;
			}
			this.messageService.copyMessage(message, srcBox, destBox);
			result = new ModelAndView("redirect:list.do?messageBoxID=" + destBoxID + "");
			result.addObject("messageBox", srcBoxID);
		} catch (final Throwable oops) {
			message = this.messageService.findOne(messageID);
			result = this.createModelAndView(message, "messageBox.commit.error");
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createModelAndView(final Message mesage) {
		ModelAndView result;

		result = this.createModelAndView(mesage, null);

		return result;
	}

	protected ModelAndView createModelAndView(final Message mesage, final String message) {
		ModelAndView result;

		final Collection<Actor> actorList = this.actorService.findAll();

		result = new ModelAndView("message/create");
		result.addObject("mesage", mesage);
		result.addObject("message", message);
		result.addObject("actorList", actorList);

		return result;
	}

	private ModelAndView forbiddenOperation() {
		JOptionPane.showMessageDialog(null, "Forbidden operation");
		return new ModelAndView("redirect:/messageBox/list.do");
	}
}
