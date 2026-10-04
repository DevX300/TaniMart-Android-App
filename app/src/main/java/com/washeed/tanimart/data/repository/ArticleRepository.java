package com.washeed.tanimart.data.repository;

import com.washeed.tanimart.data.Entities.Article;
import com.washeed.tanimart.data.dao.ArticleDao;

import java.util.List;

public class ArticleRepository {

    ArticleDao articleDao;

    public ArticleRepository(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    //---------Article Logic--------------------

    public void articleInsert(Article article){
        articleDao.insert(article);
    }
    public void articleUpdate(Article article){
        articleDao.update(article);
    }
    public void articleDelete(Article article){
        articleDao.delete(article);
    }
    public List<Article> articleList(){
        return articleDao.getAllArticles();
    }
    public Article getArticle(int articleId){
        return articleDao.getArticleById(articleId);
    }

}
