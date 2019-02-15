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
    private Integer             row;
    private Integer             column;
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
    public int getRow() {
        return this.row;
    }

    public void setRow(final int row) {
        this.row = row;
    }

    @Min(0)
    public int getColumn() {
        return this.column;
    }

    public void setColumn(final int column) {
        this.column = column;
    }

    public String getReason(){
        return this.reason;
    }

    public void setReason(String reason){
        this.reason=reason;
    }

}