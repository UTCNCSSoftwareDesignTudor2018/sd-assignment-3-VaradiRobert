package com.application.services.business;

import java.util.List;

import com.application.data.daos.ArticleDAO;
import com.application.data.daos.interfaces.ArticleDAOInterface;
import com.application.data.entities.Article;
import com.application.data.entities.Author;
import com.application.services.business.interfaces.ArticleServiceInterface;

public class ArticleService implements ArticleServiceInterface {

	private ArticleDAOInterface articleDAO;
	
	public ArticleService() {
		articleDAO = new ArticleDAO();
	}
	
	@Override
	public void saveArticle(String title, String abstractContent, String body, Author author) {
		Article article = new Article();
		article.setAbstractContent(abstractContent);
		article.setTitle(title);
		article.setAuthor(author);
		article.setBody(body);
		articleDAO.save(article);
	}

	@Override
	public List<Article> getArticles() {
		return articleDAO.findAll();
	}

	@Override
	public List<Article> getArticles(String authorUserName) {
		return articleDAO.findAllByAuthorUserName(authorUserName);
	}

	@Override
	public void deleteArticle(String title, String authorUserName) {
		
	}

	@Override
	public Article getArticle(String authorUserName, String title) {
		// TODO Auto-generated method stub
		return null;
	}

}
