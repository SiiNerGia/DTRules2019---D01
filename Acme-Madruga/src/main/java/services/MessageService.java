
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Configurations;
import domain.Message;
import domain.MessageBox;

@Service
@Transactional
public class MessageService {

	// Manage Repository
	@Autowired
	private MessageRepository		messageRepository;

	// Supporting devices
	@Autowired
	private ConfigurationsService	configurationsService;

	@Autowired
	private ActorService			actorService;


	// CRUD methods
	public Message create() {
		final Message result = new Message();
		final Calendar calendar = new GregorianCalendar();
		final Collection<MessageBox> messageBox = new ArrayList<MessageBox>();
		final Collection<Actor> recipients = new ArrayList<Actor>();
		final Collection<String> tags = new ArrayList<String>();

		result.setMessageBoxes(messageBox);
		result.setRecipients(recipients);
		result.setTags(tags);
		result.setMoment(calendar.getTime());

		return result;
	}

	public Message findOne(final int messageID) {
		final Message result = this.messageRepository.findOne(messageID);
		Assert.notNull(result);

		return result;
	}

	public Message save(final Message message) {
		Assert.notNull(message);
		Message result;

		result = this.messageRepository.save(message);
		return result;
	}

	public void delete(final Message message, final MessageBox srcMessageBox) {
		Assert.notNull(message);
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		final Actor actor = this.actorService.findOneByUsername(userAccount.getUsername());

		Assert.isTrue(message.getRecipients().contains(actor) || message.getSender().equals(actor));

		final Boolean actorRole;
		if (message.getSender() == null)
			actorRole = true;
		else if (message.getSender().equals(actor))
			actorRole = true;
		else
			actorRole = false;

		if (srcMessageBox.getName().equals("trash")) {
			for (final MessageBox box : actor.getMessageBoxes())
				if (box.getMessages().contains(message)) {
					actor.getMessageBox(box.getName()).deleteMessage(message);
					message.getMessageBoxes().remove(box);
				}
			if (!actorRole)
				message.getRecipients().remove(actor);
			else
				message.setSender(null);
			if ((message.getRecipients().size() == 0) && (message.getMessageBoxes().size() == 0) && (message.getSender() == null))
				this.messageRepository.delete(message);
			else
				this.messageRepository.save(message);
		} else {
			Assert.isTrue(srcMessageBox.getMessages().contains(message));
			this.moveMessage(message, srcMessageBox, actor.getMessageBox("trash"));
		}
	}

	// Other methods

	public Message moveMessage(final Message message, final MessageBox srcMessageBox, final MessageBox destMessageBox) {
		Assert.notNull(message);
		Assert.notNull(srcMessageBox);
		Assert.notNull(destMessageBox);
		Assert.notNull(LoginService.getPrincipal());

		final Actor actor = this.actorService.findOneByUsername(LoginService.getPrincipal().getUsername());
		Assert.isTrue(actor.getMessageBoxes().contains(srcMessageBox));
		Assert.isTrue(actor.getMessageBoxes().contains(destMessageBox));

		Assert.isTrue(actor.getMessageBox(srcMessageBox.getName()).getMessages().contains(message));
		//Assert.isTrue(!actor.getMessageBox(destMessageBox.getName()).getMessages().contains(message));

		Assert.isTrue(message.getRecipients().contains(actor) || message.getSender().equals(actor));
		Assert.isTrue(message.getMessageBoxes().contains(srcMessageBox));

		if (!destMessageBox.getMessages().contains(message)) {
			message.getMessageBoxes().remove(srcMessageBox);
			message.getMessageBoxes().add(destMessageBox);
			srcMessageBox.deleteMessage(message);
			destMessageBox.addMessage(message);
		} else {
			message.getMessageBoxes().remove(srcMessageBox);
			srcMessageBox.deleteMessage(message);
		}

		return this.messageRepository.save(message);
	}

	public Message copyMessage(final Message message, final MessageBox srcMessageBox, final MessageBox destMessageBox) {
		Assert.notNull(message);
		Assert.notNull(srcMessageBox);
		Assert.notNull(destMessageBox);
		Assert.notNull(LoginService.getPrincipal());

		final Actor actor = this.actorService.findOneByUsername(LoginService.getPrincipal().getUsername());
		Assert.isTrue(actor.getMessageBoxes().contains(srcMessageBox));
		Assert.isTrue(actor.getMessageBoxes().contains(destMessageBox));

		Assert.isTrue(actor.getMessageBox(srcMessageBox.getName()).getMessages().contains(message));
		Assert.isTrue(!actor.getMessageBox(destMessageBox.getName()).getMessages().contains(message));

		Assert.isTrue(message.getRecipients().contains(actor) || message.getSender().equals(actor));
		Assert.isTrue(message.getMessageBoxes().contains(srcMessageBox));

		message.getMessageBoxes().add(destMessageBox);
		destMessageBox.addMessage(message);

		return this.messageRepository.save(message);
	}
	// Aux methods

	private Boolean checkSpam(final Message message) {
		Boolean spam = false;

		final Configurations configuration = this.configurationsService.getConfiguration();
		final Collection<String> spamWords = configuration.getSpamWords();
		for (final String word : spamWords)
			if (message.getSubject().contains(word)) {
				spam = true;
				break;
			}
		if (!spam)
			for (final String word : spamWords)
				if (message.getBody().contains(word)) {
					spam = true;
					break;
				}

		return spam;
	}

	public Collection<Message> findAllByMessageBox(final int messageBoxID) {
		final Collection<Message> result = this.messageRepository.findByMessageBox(messageBoxID);
		Assert.notNull(result);

		return result;
	}

	public Collection<Message> findAllBySender(final int senderID) {
		final Collection<Message> result = this.messageRepository.findAllBySender(senderID);
		Assert.notNull(result);

		return result;
	}

}
