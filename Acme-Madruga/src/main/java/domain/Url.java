
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Embeddable
@Access(AccessType.PROPERTY)
public class Url {

	// Attribute
	private String link;
	private int targetId;


	@NotBlank
	@URL
	public String getLink() {
		return this.link;
	}

	public void setLink(final String link) {
		this.link = link;
	}
	
	
	public int getTargetId() {
		return targetId;
	}

	
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	@Override
	public String toString() {
		return "Picture [link=" + this.link + "]";
	}

}
