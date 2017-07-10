package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Timestamp creationDate;

    @Column
    private Boolean archived;

    @JsonIgnore
    @ManyToOne
    private Blog blog;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private List<Comment> comments;

    public Article() {}

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
        this.creationDate = new Timestamp(System.currentTimeMillis());
        this.archived = false;
        this.blog = new Blog();
        this.comments = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "title : " + title + " , content : " + content;
    }
}
