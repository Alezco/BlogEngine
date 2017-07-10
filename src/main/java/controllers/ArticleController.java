package controllers;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named("article")
public class ArticleController {
    public void listArticles() {
        System.out.println("================");
        System.out.println("LOL");
        System.out.println("================");
    }
}
