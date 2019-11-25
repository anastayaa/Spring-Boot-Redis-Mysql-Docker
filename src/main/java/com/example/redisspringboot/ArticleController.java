package com.example.redisspringboot;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("/articles")
public class ArticleController {

  public static Logger logger = LoggerFactory.getLogger(ArticleController.class);

  @Autowired
  private ArticleService service;

  @PostMapping(consumes = "application/json")
  public Article save(@RequestBody Article article) {
    logger.info("Creating article of category " + article.getCategory() + " ...");
    return service.save(article);
  }

  @GetMapping
  public List<Article> findAll() {
    return service.findAll();
  }

  @GetMapping("/by")
  public List<Article> findByCategory(@RequestParam(name = "category", required = true) String category) {
    List<Article> articles = service.findByCategory(category);
    logger.info("Database access count for fetching articles of category " +
      category + " : " + Utils.getDbAccessCount(category));
    return articles;
  }
}
