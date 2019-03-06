
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Request extends DomainEntity {

	// Attributes

	private String		status;
	private Integer		assignedRow;
	private Integer		assignedColumn;
	private String		reason;
	// relationships --------------------
	private Member		member;
	private Procession	procession;


	// Getters & setters
	@NotBlank
	@Pattern(regexp = "^(PENDING|APPROVED|REJECTED)$")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@Min(0)
	public Integer getAssignedRow() {
		return this.assignedRow;
	}

	public void setAssignedRow(final Integer assignedRow) {
		this.assignedRow = assignedRow;
	}

	@Min(0)
	public Integer getAssignedColumn() {
		return this.assignedColumn;
	}

	public void setAssignedColumn(final Integer assignedColumn) {
		this.assignedColumn = assignedColumn;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getReason() {
		return this.reason;
	}

	public void setReason(final String reason) {
		this.reason = reason;
	}

	// RELATIONSHIPS	---------------------------------------

	@Valid
	@ManyToOne(optional = false)
	public Member getMember() {
		return this.member;
	}

	public void setMember(final Member member) {
		this.member = member;
	}

	@Valid
	@ManyToOne(optional = false)
	public Procession getProcession() {
		return this.procession;
	}

	public void setProcession(final Procession procession) {
		this.procession = procession;
	}

}
