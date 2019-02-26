
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageBoxRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Message;
import domain.MessageBox;

@Service
@Transactional
public class MessageBoxService {

	// Manage Repository
	@Autowired
	private MessageBoxRepository	messageBoxRepository;

	// Supporting services
	@Autowired
	private ActorService			actorService;


	// CRUD methods
	public MessageBox create() {
		final MessageBox result = new MessageBox();
		result.setIsSystemBox(false);
		result.setMessages(new ArrayList<Message>());

		return result;
	}

	public MessageBox findOne(final int messageBoxID) {
		final MessageBox result = this.messageBoxRepository.findOne(messageBoxID);
		Assert.notNull(result);

		return result;
	}

	public Collection<MessageBox> findAll() {
		final Collection<MessageBox> result = this.messageBoxRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public MessageBox save(final MessageBox messageBox) {
		Assert.notNull(messageBox);
		final MessageBox result;
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		if (messageBox.getId() == 0) {
			final Actor actor = this.actorService.findOneByUsername(userAccount.getUsername());
			messageBox.setIsSystemBox(false);
			result = this.messageBoxRepository.save(messageBox);
			actor.getMessageBoxes().add(result);
		} else {
			Assert.isTrue(messageBox.getIsSystemBox() == false);
			result = this.messageBoxRepository.save(messageBox);
		}
		return result;
	}

	public MessageBox update(final MessageBox messageBox) {
		Assert.notNull(messageBox);
		Assert.isTrue(messageBox.getIsSystemBox() == false);

		return this.messageBoxRepository.save(messageBox);
	}

	public void delete(final MessageBox messageBox) {
		Assert.notNull(messageBox);
		Assert.isTrue(messageBox.getIsSystemBox() == false);
		final UserAccount userAccount = LoginService.getPrincipal();

		this.actorService.findOneByUsername(userAccount.getUsername()).getMessageBoxes().remove(messageBox);

		this.messageBoxRepository.delete(messageBox);
	}

	// Other methods

	public Collection<MessageBox> findAllByActor(final int actorID) {
		final Collection<MessageBox> result = this.messageBoxRepository.findAllByActor(actorID);
		Assert.notNull(result);

		return result;
	}

	public MessageBox findOneByActorAndName(final int actorID, final String name) {
		Assert.notNull(name);
		final MessageBox result = this.messageBoxRepository.findOneByActorAndName(actorID, name);
		Assert.notNull(result);

		return result;
	}
	public Collection<MessageBox> createSystemMessageBox() {
		final Collection<MessageBox> result = new ArrayList<MessageBox>();
		final Collection<Message> messages = new ArrayList<Message>();
		final MessageBox in = new MessageBox();
		final MessageBox out = new MessageBox();
		final MessageBox trash = new MessageBox();
		final MessageBox spam = new MessageBox();

		in.setName("in");
		in.setMessages(messages);
		in.setIsSystemBox(true);

		out.setName("out");
		out.setMessages(messages);
		out.setIsSystemBox(true);

		trash.setName("trash");
		trash.setMessages(messages);
		trash.setIsSystemBox(true);

		spam.setName("spam");
		spam.setMessages(messages);
		spam.setIsSystemBox(true);

		result.add(in);
		result.add(out);
		result.add(trash);
		result.add(spam);

		this.messageBoxRepository.save(in);
		this.messageBoxRepository.save(out);
		this.messageBoxRepository.save(trash);
		this.messageBoxRepository.save(spam);

		return result;
	}
}
