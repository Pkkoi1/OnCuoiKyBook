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
//@AllArgsConstructor
//@ToString
@Table(name = "publishers")
public class Publisher implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "publisher_id", nullable = false)
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;



    public Publisher(String id, String name, String address, String phone, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Publisher() {
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}