package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
//@ToString
@Table(name = "books")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Book.listRatedBooks", query = "SELECT b FROM Book b INNER JOIN b.reviews r " +
                "INNER JOIN b.authors a WHERE r.rating > :rating and a = :author"),
        @NamedQuery(name = "Book.listBooks", query = "SELECT b FROM Book b"),
})
public class Book implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    protected String ISBN;
    protected String name;
    @Column(name = "publish_year")
    protected int publishYear;
    @Column(name = "num_of_pages")
    protected int numOfPages;
    protected double price;


    @ElementCollection
    @CollectionTable(name = "books_authors", joinColumns = @JoinColumn(name = "ISBN"))
    @Column(name = "author", nullable = false)
    protected Set<String> authors;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id")
    protected Publisher publisher;

    @OneToMany(mappedBy = "ISBN", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected Set<Review> reviews;

//	@OneToOne(mappedBy = "book")
//	private BookTranslation bookTranslation;

    public Book() {

    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", name='" + name + '\'' +
                ", publishYear=" + publishYear +
                ", numOfPages=" + numOfPages +
                ", price=" + price +
                ", authors=" + authors +
                ", publisher=" + publisher +
                ", reviews=" + reviews +
                '}';
    }
}
