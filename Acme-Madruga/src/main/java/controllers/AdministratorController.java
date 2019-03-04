/*
 * AdministratorController.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Administrator;
import domain.Brotherhood;
import domain.Procession;
import services.ActorService;
import services.AdministratorService;
import services.ConfigurationsService;
import services.MessageBoxService;
import utilities.Md5;


@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ActorService			actorService;

//	@Autowired
//	private PositionService			positionService;

	@Autowired
	private MessageBoxService		messageBoxService;

	@Autowired
	private ConfigurationsService	configurationsService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		JOptionPane.showMessageDialog(null, "Forbidden operation");
		return new ModelAndView("redirect:/");
	}

	// List -------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Administrator> admins;

		admins = this.administratorService.findAll();

		result = new ModelAndView("administrator/list");
		result.addObject("administrators", admins);
		result.addObject("requestURI", "administrator/list.do");

		return result;
	}

	// Create -----------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Administrator admin;

		admin = this.administratorService.create();

		result = new ModelAndView("administrator/create");
		result.addObject("administrator", admin);

		return result;
	}

	// Save -----------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Administrator admin, final BindingResult binding) {
		ModelAndView result;
		String password;
		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			admin.setMessageBoxes(this.messageBoxService.createSystemMessageBox());
			result = new ModelAndView("administrator/create");
			result.addObject("administrator", admin);
		} else
			try {
				password = Md5.encodeMd5(admin.getUserAccount().getPassword());
				admin.getUserAccount().setPassword(password);
				this.administratorService.save(admin);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = new ModelAndView("administrator/create");
				result.addObject("administrator", admin);
				if (oops instanceof DataIntegrityViolationException)
					result.addObject("message", "admin.duplicated.username");
				else {
					System.out.println(oops.getCause().toString());
					result.addObject("message", "admin.registration.error");
				}
			}
		return result;
	}

	// Update -----------------------------------------------------------
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Administrator admin;

		admin = this.administratorService.findByPrincipal();
		result = new ModelAndView("administrator/update");
		result.addObject("administrator", admin);

		return result;
	}

	// Save Update ----------------------------------------------------------
	@RequestMapping(value = "/update", method = RequestMethod.POST, params = "update")
	public ModelAndView edit(@Valid final Administrator admin, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			result = new ModelAndView("administrator/update");
			result.addObject("administrator", admin);
		} else
			try {
				this.administratorService.save(admin);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = new ModelAndView("administrator/update");
				result.addObject("administrator", admin);
				result.addObject("message", "administrator.commit.error");
			}
		return result;
	}

	// Dashboard -----------------------------------------------------------
	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		final ModelAndView result;

		// Queries
		Object[] query1 = this.administratorService.query1();
		Collection<Brotherhood> query2 = this.administratorService.query2();
		Collection<Brotherhood> query3 = this.administratorService.query3();
		Collection<Double> query4 = this.administratorService.query4();
		Collection<Procession> query5 = this.administratorService.query5();

		Collection<Object> query7 = this.administratorService.query7();

//		final Collection<Position> query8a = this.positionService.findAll();
//		final Collection<Integer> query8b = null;
//
//		for (final Position p : query8a)
//			query8b.add(this.administratorService.query8(p.getId()));

		result = new ModelAndView("administrator/dashboard");

		result.addObject("query1", query1);
		result.addObject("query2", query2);
		result.addObject("query3", query3);
		result.addObject("query4", query4);
		result.addObject("query5", query5);
		result.addObject("query7", query7);

		
		// Charts
		
//		int[] values = this.administratorService.querySpammersGetValues();
//		
//		PieOptions options = new PieOptions();
//		options.setResponsive(true).setTitle(new Title()
//								   .setText("Spammers Pie")
//								   .setDisplay(true));
//		
//		PieDataset dataset = new PieDataset().setData(values)
//											 .addBackgroundColors(Color.RED, Color.BLUE)
//											 .setBorderWidth(4)
//											 .setBorderColor(Color.BLACK);
//		
//		PieData data = new PieData().addLabels("Spammers", "Not Spammers")
//									.addDataset(dataset);
//		String pieChart = new PieChart(data, options).toJson();
//		
//		result.addObject("pieChart", pieChart);
		
		int spammers = this.administratorService.queryGetSpammers();
		int notSpammers = this.administratorService.queryGetNotSpammers();
		
		
		result.addObject("spammers", spammers);
		result.addObject("notSpammers", notSpammers);
		
		//result.addObject("query7", query7);

//		result.addObject("query8a", query8a);
//		result.addObject("query8b", query8b);

		return result;
	}
	/**
	 * 
	 * SPAM
	 * ****************************************************************************
	 */

	// Spammer list ---------------------------------------------------------------------
	@RequestMapping(value = "/spammers", method = RequestMethod.GET)
	public ModelAndView suspiciousList() {
		ModelAndView result;
		Collection<Actor> suspicious;

		suspicious = this.administratorService.getSpammers();

		result = new ModelAndView("administrator/spammers");
		result.addObject("suspicious", suspicious);
		result.addObject("requestURI", "administrator/spammers.do");

		return result;
	}

	// Compute Spammers -------------------------------------------------------------------
	@RequestMapping(value = "/computeSpammers", method = RequestMethod.GET)
	public ModelAndView computeSpammers() {

		this.administratorService.computeSpammers();

		return this.suspiciousList();
	}

	// Ban
	// -----------------------------------------------------------------------------------
	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam final int actorId) {
		ModelAndView result;
		Actor actor = null;

		try {
			actor = this.actorService.findOne(actorId);
		} catch (final Exception e) {
			result = this.forbiddenOpperation();
			return result;
		}

		this.administratorService.banAnActor(actor);

		result = this.suspiciousList();
		return result;
	}

	// Unban
	// -----------------------------------------------------------------------------------
	@RequestMapping(value = "/unban", method = RequestMethod.GET)
	public ModelAndView unban(@RequestParam final int actorId) {
		ModelAndView result;
		Actor actor = null;

		try {
			actor = this.actorService.findOne(actorId);
		} catch (final Exception e) {
			result = this.forbiddenOpperation();
			return result;
		}
		this.administratorService.unBanAnActor(actor);

		result = this.suspiciousList();

		return result;
	}

	// Ancillary methods
	// -----------------------------------------------------------------------
	private ModelAndView forbiddenOpperation() {
		return new ModelAndView("redirect:/");
	}

	/**
	 * 
	 * POLARITY SCORE
	 * ****************************************************************************
	 */

	// Score list
	// -------------------------------------------------------------------
	@RequestMapping(value = "/score", method = RequestMethod.GET)
	public ModelAndView scoreList() {
		ModelAndView result;
		Collection<Actor> users;

		users = this.actorService.findAll();
		users.remove(this.administratorService.findByPrincipal());

		result = new ModelAndView("administrator/score");
		result.addObject("users", users);
		result.addObject("requestURI", "administrator/score.do");

		return result;
	}

	// Score Compute
	// -------------------------------------------------------------------
	@RequestMapping(value = "/computeScore", method = RequestMethod.GET)
	public ModelAndView computeScoreList() {
		ModelAndView result;
		Collection<Actor> users;

		this.administratorService.computeAllScores();

		users = this.actorService.findAll();
		users.remove(this.administratorService.findByPrincipal());

		result = new ModelAndView("administrator/score");
		result.addObject("users", users);
		// result.addObject("requestURI", "administrator/score.do");

		return result;
	}

	/**
	 * 
	 * Manage polarity Words
	 * ****************************************************************************
	 */

	// List Words-------------------------------------------------------------
	@RequestMapping(value = "/config/polarityWords/list", method = RequestMethod.GET)
	public ModelAndView wordList() {
		ModelAndView result;
		Collection<String> positiveWords;
		Collection<String> negativeWords;

		positiveWords = this.configurationsService.getConfiguration().getPositiveWords();
		negativeWords = this.configurationsService.getConfiguration().getNegativeWords();

		result = new ModelAndView("administrator/config/polarityWords/list");
		result.addObject("requestURI", "administrator/config/polarityWords/list.do");
		result.addObject("positiveWords", positiveWords);
		result.addObject("negativeWords", negativeWords);

		return result;
	}

	// Add + Words
	// GET-------------------------------------------------------------
	@RequestMapping(value = "/config/polarityWords/addPositiveWord", method = RequestMethod.GET)
	public ModelAndView addPosWord() {
		ModelAndView result;

		result = new ModelAndView("administrator/config/polarityWords/add");
		result.addObject("action", "administrator/config/polarityWords/addPositiveWord.do");
		return result;
	}

	// Add + Word SAVE
	// ------------------------------------------------------------------
	@RequestMapping(value = "/config/polarityWords/addPositiveWord", method = RequestMethod.POST, params = "save")
	public ModelAndView addPosWord(@RequestParam("word") final String word) {
		ModelAndView result;

		try {
			// Add the word and update configurations
			this.administratorService.addPositiveWord(word);
		} catch (final Exception e) {
			result = new ModelAndView("administrator/config/polarityWords/add");
			result.addObject("action", "administrator/config/polarityWords/addPositiveWord.do");
			result.addObject("message", "config.field.error");
			return result;
		}

		result = this.wordList();

		return result;
	}

	// Edit word GET
	// ------------------------------------------------------------------
	@RequestMapping(value = "/config/polarityWords/editPositiveWord", method = RequestMethod.GET)
	public ModelAndView editPosWord(@RequestParam("word") final String word, @RequestParam("index") final int index) {
		ModelAndView result;

		result = new ModelAndView("administrator/config/polarityWords/edit");
		result.addObject("action", "administrator/config/polarityWords/editPositiveWord.do");

		result.addObject("word", word);
		result.addObject("index", index);

		return result;
	}

	// Edit word SAVE
	// ------------------------------------------------------------------
	@RequestMapping(value = "/config/polarityWords/editPositiveWord", method = RequestMethod.POST, params = "save")
	public ModelAndView editPosWordPost(@RequestParam("word") final String word, @RequestParam("index") final int index) {
		ModelAndView result;

		try {
			// Add the word and update configurations
			this.administratorService.editPositiveWord(word, index - 1);
		} catch (final Exception e) {
			e.printStackTrace();
			result = new ModelAndView("administrator/config/polarityWords/edit");
			result.addObject("action", "administrator/config/polarityWords/editPositiveWord.do");

			result.addObject("word", word);
			result.addObject("index", index);

			result.addObject("message", "config.field.error");

			return result;
		}

		result = this.wordList();

		return result;
	}

	// Remove positive word
	// ------------------------------------------------------------------
	@RequestMapping(value = "/config/polarityWords/removePositiveWord", method = RequestMethod.GET)
	public ModelAndView removePosWord(@RequestParam("word") final String word) {

		this.administratorService.removePositiveWord(word);

		return this.wordList();
	}

	// Add - Words
	// GET-------------------------------------------------------------
	@RequestMapping(value = "/config/polarityWords/addNegativeWord", method = RequestMethod.GET)
	public ModelAndView addNegativeWord() {
		ModelAndView result;

		result = new ModelAndView("administrator/config/polarityWords/add");
		result.addObject("action", "administrator/config/polarityWords/addNegativeWord.do");
		return result;
	}

	// Add - Words
	// POS-------------------------------------------------------------
	@RequestMapping(value = "/config/polarityWords/addNegativeWord", method = RequestMethod.POST, params = "save")
	public ModelAndView addNegativeWord(@RequestParam("word") final String word) {
		ModelAndView result;

		try {
			// Add the word and update configurations
			this.administratorService.addNegativeWord(word);
		} catch (final Exception e) {
			result = new ModelAndView("administrator/config/polarityWords/add");
			result.addObject("action", "administrator/config/polarityWords/addNegativeWord.do");
			result.addObject("message", "config.field.error");
			return result;
		}

		result = this.wordList();

		return result;
	}

	// Edit word GET
	// ------------------------------------------------------------------
	@RequestMapping(value = "/config/polarityWords/editNegativeWord", method = RequestMethod.GET)
	public ModelAndView editNegWord(@RequestParam("word") final String word, @RequestParam("index") final int index) {
		ModelAndView result;

		result = new ModelAndView("administrator/config/polarityWords/edit");
		result.addObject("action", "administrator/config/polarityWords/editNegativeWord.do");

		result.addObject("word", word);
		result.addObject("index", index);

		return result;
	}

	// Edit word SAVE
	// ------------------------------------------------------------------
	@RequestMapping(value = "/config/polarityWords/editNegativeWord", method = RequestMethod.POST, params = "save")
	public ModelAndView editNegWordPost(@RequestParam("word") final String word, @RequestParam("index") final int index) {
		ModelAndView result;

		try {
			// Add the word and update configurations
			this.administratorService.editNegativeWord(word, index - 1);
		} catch (final Exception e) {
			e.printStackTrace();
			result = new ModelAndView("administrator/config/polarityWords/edit");
			result.addObject("action", "administrator/config/polarityWords/editNegativeWord.do");

			result.addObject("word", word);
			result.addObject("index", index);

			result.addObject("message", "config.field.error");

			return result;
		}

		result = this.wordList();

		return result;
	}

	// Remove negative word
	// ------------------------------------------------------------------
	@RequestMapping(value = "/config/polarityWords/removeNegativeWord", method = RequestMethod.GET)
	public ModelAndView removeNegWord(@RequestParam("word") final String word) {

		this.administratorService.removeNegativeWord(word);
		return this.wordList();
	}

}
