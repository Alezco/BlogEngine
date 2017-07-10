package models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition="VARCHAR(500)")
    private String content;

    @Column
    private Timestamp creationDate;

    @ManyToOne
    private User author;

    @ManyToOne
    private Article article;

    public Comment(String content, User user, Article article) {
        this.creationDate = new Timestamp(System.currentTimeMillis());
        this.content = content;
        this.author = user;
        this.article = article;
    }

    public Comment() {}
}
