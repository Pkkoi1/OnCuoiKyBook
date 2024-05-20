package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Table(name = "book_translations")
public class BookTranslation extends Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private String language;
    @Column(name = "translate_name")
    private String translateName;


//    @Override
//    public String toString() {
//        return "BookTranslation{" +
//                "language='" + language + '\'' +
//                ", translateName='" + translateName + '\'' +
//                ", ISBN='" + ISBN + '\'' +
//                ", name='" + name + '\'' +
//                ", publishYear=" + publishYear +
//                ", numOfPages=" + numOfPages +
//                ", price=" + price +
//                ", authors=" + authors +
//                ", publisher=" + publisher +
//                ", reviews=" + reviews +
//                '}';
//    }
}