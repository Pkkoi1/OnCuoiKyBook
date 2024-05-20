package entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "people")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "person_id", nullable = false)
    private int id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    private String email;
    @Column(name = "professional_title")
    private String professionalTitle;

    @OneToMany(mappedBy = "personId")
    private Set<Review> reviews;


    public Person(int id, String lastName, String firstName, String email, String professionalTitle, Set<Review> reviews) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.professionalTitle = professionalTitle;
        this.reviews = reviews;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", professionalTitle='" + professionalTitle + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}
