package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;


@Entity
@Access(AccessType.PROPERTY)
public class Float extends DomainEntity {

    // Attributes

    private String				title;
    private String              description;
    private String              picture;


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

    @URL
    public String getPicture(){
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
