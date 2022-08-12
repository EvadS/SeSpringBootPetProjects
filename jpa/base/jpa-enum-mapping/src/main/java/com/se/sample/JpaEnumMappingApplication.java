package com.se.sample;

import com.se.sample.model.domain.Article;
import com.se.sample.model.enums.Category;
import com.se.sample.model.enums.Priority;
import com.se.sample.model.enums.Status;
import com.se.sample.model.enums.Type;
import com.se.sample.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;

@SpringBootApplication
public class JpaEnumMappingApplication implements CommandLineRunner {

    @Autowired
    ArticleRepository articleRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaEnumMappingApplication.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {

        articleRepository.deleteAllInBatch();

        Article article1 = createTmpArticle();
        articleRepository.save(article1);

        Collection<Article>  articles = articleRepository.findAll();

        System.out.println("Current articles.");
        articles.forEach(it -> System.out.println(it));
        System.out.println("====================================");

    }

    private static Article createTmpArticle (){

        Article article = new Article();
        article.setTitle("ordinal title");
        article.setStatus(Status.OPEN);

        article.setType(Type.EXTERNAL);
        article.setPriority(Priority.HIGH);
        article.setCategory(Category.MUSIC);

        return article;
    }
}
