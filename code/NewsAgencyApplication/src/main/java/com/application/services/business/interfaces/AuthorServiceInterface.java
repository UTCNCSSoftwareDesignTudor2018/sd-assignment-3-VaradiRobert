package com.application.services.business.interfaces;

import java.util.List;

import com.application.data.entities.Article;

public interface AuthorServiceInterface {
	public boolean login(String userName, String password);
	public List<Article> getArticles(String authorUserName);
	public void createarticle(String title, String abstractContent, String body, String authorUserName);
	public void updateArticle(String title, String abstractContent, String body, String authorUserName);
	public void deleteArticle(String title, String authorUserName);
}
