package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Access(AccessType.PROPERTY)
public class Request extends DomainEntity {

    // Attributes

    private String				status;
    private Integer             assignedRow;
    private Integer             assignedColumn;
    private String              reason;


    // Getters & setters

    @NotBlank
    @Pattern(regexp = "^(PENDING|APPROVED|REJECTED)$")
    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status=status;
    }

    @Min(0)
    public int getAssignedRow() {
        return this.assignedRow;
    }

    public void setAssignedRow(final int assignedRow) {
        this.assignedRow = assignedRow;
    }

    @Min(0)
    public int getAssignedColumn() {
        return this.assignedColumn;
    }

    public void setAssignedColumn(final int assignedColumn) {
        this.assignedColumn = assignedColumn;
    }

    public String getReason(){
        return this.reason;
    }

    public void setReason(String reason){
        this.reason=reason;
    }

}