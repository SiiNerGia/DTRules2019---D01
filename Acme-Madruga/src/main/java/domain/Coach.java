package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.Valid;
import java.util.Collection;


@Entity
@Access(AccessType.PROPERTY)
public class Coach extends DomainEntity {

    // Attributes

    private String				title;
    private String              description;
    private Collection<Url>     pictures;


    // Getters & setters

    @NotBlank
    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    @NotBlank
    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    @ElementCollection
    @Valid
    public Collection<Url> getPictures() {
        return this.pictures;
    }

    public void setPictures(final Collection<Url> pictures) {
        this.pictures = pictures;
    }

}
