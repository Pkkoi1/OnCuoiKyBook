package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Entity
//@AllArgsConstructor
//@ToString
@Table(name = "reviews")

public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    private int  rating;
    private String comment;

    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ISBN")
    private Book ISBN;

    @Id
    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person personId;

    public Review(int rating, String comment, Book ISBN, Person personId) {
        this.rating = rating;
        this.comment = comment;
        this.ISBN = ISBN;
        this.personId = personId;
    }
    public Review() {
    }

    @Override
    public String toString() {
        return "Review{" +
                "rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}