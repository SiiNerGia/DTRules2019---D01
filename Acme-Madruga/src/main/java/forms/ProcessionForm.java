package forms;

import java.util.Date;

public class ProcessionForm {
	
	private String title;
	private String description;
	private Date moment;
	private Boolean draftMode;
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	public Boolean getDraftMode() {
		return draftMode;
	}
	public void setDraftMode(Boolean draftMode) {
		this.draftMode = draftMode;
	}
	
	

}
