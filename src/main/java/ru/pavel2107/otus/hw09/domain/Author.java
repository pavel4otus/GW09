package ru.pavel2107.otus.hw09.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "authors")
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name="id", nullable = false)
    @Setter @Getter private Long id;

    @Column( name = "name")
    @Setter @Getter private String name;

    @Column( name = "birthdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Setter @Getter private LocalDate birthDate;

    @Column( name ="email")
    @Setter @Getter private String    email;

    @Column( name= "phone")
    @Setter @Getter private String    phone;

    @Column( name = "address")
    @Setter @Getter private String    address;

    @OneToMany( mappedBy = "author", fetch = FetchType.LAZY)
    @Setter @Getter private List<Book> books;

    public Author( Long id, String Name, LocalDate birthDate, String email, String phone, String address){
        this.id = id;
        this.name = Name;
        this.birthDate = birthDate;
        this.email     = email;
        this.phone     = phone;
        this.address   = address;
    }
}
