package com.application.services.business.interfaces;

import java.util.List;

import com.application.data.entities.Article;
import com.application.data.entities.Author;

public interface ArticleServiceInterface {
	public void saveArticle(String title, String abstractContent, String body, Author author);
	public List<Article> getArticles();
	public List<Article> getArticles(String authorUserName);
	public Article getArticle(String authorUserName, String title);
	public void deleteArticle(String title, String authorUserName);
}
