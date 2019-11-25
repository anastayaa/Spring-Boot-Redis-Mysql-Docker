package com.example.redisspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

  @Autowired
  private ArticleRepository repository;

  @CacheEvict(cacheManager = "redisCacheManager", cacheNames = "articles", key = "#article.category", condition = "#article.category=='MODE'")
  public Article save(Article article) {
    return repository.save(article);
  }

  public List<Article> findAll() {
    return repository.findAll();
  }

  @Cacheable(cacheManager = "redisCacheManager", cacheNames = "articles", key = "#category", condition = "#category=='MODE'")
  public List<Article> findByCategory(String category) {
    Utils.incrementDbAccessFor(category);
    return repository.findByCategory(category);
  }
}

