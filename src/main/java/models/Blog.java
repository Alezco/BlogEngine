package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Timestamp creationDate;

    @Column
    private Boolean archived;

    @ManyToOne
    private User owner;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "blog")
    private List<Article> articles;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", archived=" + archived +
                '}';
    }
}
