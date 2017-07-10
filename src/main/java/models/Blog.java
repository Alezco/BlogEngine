package models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @ManyToOne
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "blog")
    private List<Article> articles;
}
