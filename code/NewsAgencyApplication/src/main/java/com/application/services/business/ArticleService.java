package com.application.services.business;

import java.util.List;
import java.util.stream.Collectors;

import com.application.connection.Observable;
import com.application.data.daos.ArticleDAO;
import com.application.data.daos.interfaces.ArticleDAOInterface;
import com.application.data.entities.Article;
import com.application.data.entities.Author;
import com.application.services.business.interfaces.ArticleServiceInterface;

public class ArticleService extends Observable implements ArticleServiceInterface {

	private ArticleDAOInterface articleDAO;
	private static ArticleServiceInterface singleInstance;
	
	private ArticleService() {
		articleDAO = new ArticleDAO();
	}
	
	public static ArticleServiceInterface getInstance() {
		if(singleInstance == null) {
			synchronized(ArticleService.class) {
				if(singleInstance == null) {
					singleInstance = new ArticleService();
				}
			}
		}
		return singleInstance;
	}
	
	@Override
	public synchronized void saveArticle(String title, String abstractContent, String body, Author author) {
		Article article = new Article();
		article.setAbstractContent(abstractContent);
		article.setTitle(title);
		article.setAuthor(author);
		article.setBody(body);
		articleDAO.save(article);
		notifyAllObservers(articleDAO.findAll());
	}

	@Override
	public synchronized List<Article> getArticles() {
		return articleDAO.findAll();
	}

	@Override
	public synchronized List<Article> getArticles(String authorUserName) {
		return articleDAO.findAllByAuthorUserName(authorUserName);
	}

	@Override
	public synchronized void deleteArticle(String title, String authorUserName) {
		
	}

	@Override
	public synchronized Article getArticle(String authorUserName, String title) {
		List<Article> articles = articleDAO.findAllByAuthorUserName(authorUserName);
		System.out.println("Server.ArticleService.getArticle(): " + authorUserName + " " + articles.size());
		List<Article> filtered = articles.stream().filter(a -> {
			return a.getTitle().equals(title);
		}).collect(Collectors.toList());
		if(filtered.size() > 0) {
			return filtered.get(0);
		} else {
			return null;
		}
	}

}
