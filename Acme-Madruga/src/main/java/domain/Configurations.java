
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Configurations extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private int					cacheTime;
	private int					finderMaxResult;
	private Collection<String>	spamWords;
	private Collection<String>	positiveWords;
	private Collection<String>	negativeWords;
	private double				vat;
	private String				countryCode;
	private Collection<String>	brandName;
	private String 				title;
	private String 				logo;


	@Range(min = 1, max = 24)
	public int getCacheTime() {
		return this.cacheTime;
	}

	public void setCacheTime(final int cacheTime) {
		this.cacheTime = cacheTime;
	}

	@Range(min = 10, max = 100)
	public Integer getFinderMaxResult() {
		return this.finderMaxResult;
	}

	public void setFinderMaxResult(final Integer finderMaxResult) {
		this.finderMaxResult = finderMaxResult;
	}

	@NotNull
	@NotEmpty
	@ElementCollection
	public Collection<String> getSpamWords() {
		return this.spamWords;
	}

	public void setSpamWords(final Collection<String> spamWords) {
		this.spamWords = spamWords;
	}

	@NotNull
	@NotEmpty
	@ElementCollection
	public Collection<String> getPositiveWords() {
		return this.positiveWords;
	}

	public void setPositiveWords(final Collection<String> positiveWords) {
		this.positiveWords = positiveWords;
	}

	@NotNull
	@NotEmpty
	@ElementCollection
	public Collection<String> getNegativeWords() {
		return this.negativeWords;
	}

	public void setNegativeWords(final Collection<String> negativeWords) {
		this.negativeWords = negativeWords;
	}

	public double getVat() {
		return this.vat;
	}

	public void setVat(final double vat) {
		this.vat = vat;
	}

	@NotBlank
	@Pattern(regexp = "([+]?\\d{1,2})")
	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	@NotNull
	@NotEmpty
	@ElementCollection
	public Collection<String> getBrandName() {
		return this.brandName;
	}
	public void setBrandName(final Collection<String> brandName) {
		this.brandName = brandName;
	}
	

	@NotEmpty
	public String getTitle() {
		return title;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}

	@NotEmpty
	@URL
	public String getLogo() {
		return logo;
	}

	
	public void setLogo(String logo) {
		this.logo = logo;
	}




	// Relationships ----------------------------------------------------------


	// Other methods
}
