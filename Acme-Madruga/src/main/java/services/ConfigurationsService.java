
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Configurations;
import repositories.ConfigurationsRepository;

@Service
@Transactional
public class ConfigurationsService {

	// Manage Repository
	@Autowired
	private ConfigurationsRepository	configurationsRepository;


	// Supporting services

	// CRUD methods
	public Configurations getConfiguration() {
		Configurations result = this.configurationsRepository.findAll().get(0);

		Assert.notNull(result);

		return result;
	}

	public Configurations update(final Configurations config) {
		Assert.notNull(config);

		return this.configurationsRepository.save(config);
	}

	// Other business methods

//	public void checkSpam(final Actor actor, final Object object) {
//		Boolean spam = false;
//		final Configurations configuration = this.getConfiguration();
//		final Collection<String> spamWords = configuration.getSpamWords();
//
//		if (actor.getIsSuspicious())
//			spam = true;
//		else
//			switch (object.getClass().getSimpleName()) {
//			case "Application":
//				final Application application = (Application) object;
//				if (!application.getComments().isEmpty())
//					for (final String word : spamWords)
//						if (application.getComments().contains(word)) {
//							spam = true;
//							break;
//						}
//				break;
//			case "Complaint":
//				final Complaint complaint = (Complaint) object;
//				for (final String word : spamWords)
//					if (complaint.getDescription().contains(word)) {
//						spam = true;
//						break;
//					}
//				break;
//			case "EducationRecord":
//				final EducationRecord educationRecord = (EducationRecord) object;
//				if (educationRecord.getComments() != null)
//					if (educationRecord.getComments().size() > 0)
//						for (final String comment : educationRecord.getComments())
//							for (final String word : spamWords)
//								if (comment.contains(word)) {
//									spam = true;
//									break;
//								}
//
//				break;
//			case "Endorsement":
//				final Endorsement endorsement = (Endorsement) object;
//				if (endorsement.getComments() != null)
//					if (endorsement.getComments().size() > 0)
//						for (final String comment : endorsement.getComments())
//							for (final String word : spamWords)
//								if (comment.contains(word)) {
//									spam = true;
//									break;
//								}
//				break;
//			case "EndorserRecord":
//				final EndorserRecord endorserRecord = (EndorserRecord) object;
//				if (endorserRecord.getComments() != null)
//					if (endorserRecord.getComments().size() > 0)
//						for (final String comment : endorserRecord.getComments())
//							for (final String word : spamWords)
//								if (comment.contains(word)) {
//									spam = true;
//									break;
//								}
//				break;
//			case "FixUpTask":
//				final FixUpTask fixUpTask = (FixUpTask) object;
//				for (final String word : spamWords)
//					if (fixUpTask.getDescription().contains(word)) {
//						spam = true;
//						break;
//					}
//				break;
//			case "Message":
//				final Message message = (Message) object;
//				for (final String word : spamWords)
//					if (message.getSubject().contains(word)) {
//						spam = true;
//						break;
//					}
//				if (!spam)
//					for (final String word : spamWords)
//						if (message.getBody().contains(word)) {
//							spam = true;
//							break;
//						}
//				break;
//			case "MiscellaneousRecord":
//				final MiscellaneousRecord miscellaneousRecord = (MiscellaneousRecord) object;
//				if (miscellaneousRecord.getComments() != null)
//					if (miscellaneousRecord.getComments().size() > 0)
//						for (final String comment : miscellaneousRecord.getComments())
//							for (final String word : spamWords)
//								if (comment.contains(word)) {
//									spam = true;
//									break;
//								}
//				break;
//			case "Note":
//				final Note note = (Note) object;
//				for (final String word : spamWords)
//					if (note.getComment().contains(word)) {
//						spam = true;
//						break;
//					}
//				if (!spam)
//					if (note.getOptionalComments() != null)
//						if (note.getOptionalComments().size() > 0)
//							for (final String comment : note.getOptionalComments())
//								for (final String word : spamWords)
//									if (comment.contains(word)) {
//										spam = true;
//										break;
//									}
//				break;
//			case "Phase":
//				final Phase phase = (Phase) object;
//				for (final String word : spamWords)
//					if (phase.getDescription() != null)
//						if (phase.getDescription().contains(word)) {
//							spam = true;
//							break;
//						}
//				break;
//			case "ProfessionalRecord":
//				final ProfessionalRecord professionalRecord = (ProfessionalRecord) object;
//				if (professionalRecord.getComments() != null)
//					if (professionalRecord.getComments().size() > 0)
//						for (final String comment : professionalRecord.getComments())
//							for (final String word : spamWords)
//								if (comment.contains(word)) {
//									spam = true;
//									break;
//								}
//				break;
//			case "Report":
//				final Report report = (Report) object;
//				for (final String word : spamWords)
//					if (report.getDescription().contains(word)) {
//						spam = true;
//						break;
//					}
//				break;
//			case "Section":
//				final Section section = (Section) object;
//				for (final String word : spamWords)
//					if (section.getText().contains(word)) {
//						spam = true;
//						break;
//					}
//				break;
//
//			case "Tutorial":
//				final Tutorial tutorial = (Tutorial) object;
//				for (final String word : spamWords)
//					if (tutorial.getSummary().contains(word)) {
//						spam = true;
//						break;
//					}
//				break;
//			case "Warranty":
//				final Warranty warranty = (Warranty) object;
//				if (warranty.getLaws() != null)
//					for (final String word : spamWords)
//						if (warranty.getLaws().contains(word)) {
//							spam = true;
//							break;
//						}
//				break;
//			}
//		if (spam)
//			actor.setIsSuspicious(true);
//	}
}
