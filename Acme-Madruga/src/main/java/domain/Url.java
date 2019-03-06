
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Embeddable
@Access(AccessType.PROPERTY)
public class Url {

	// Attribute
	private String	link;
	private int		targetId;


	@NotBlank
	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getLink() {
		return this.link;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	public int getTargetId() {
		return this.targetId;
	}

	public void setTargetId(final int targetId) {
		this.targetId = targetId;
	}

	@Override
	public String toString() {
		return "Picture [link=" + this.link + "]";
	}

}
