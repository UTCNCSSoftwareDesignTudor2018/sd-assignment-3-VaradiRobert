package com.application.services.business;

import java.util.List;

import com.application.data.daos.AuthorDAO;
import com.application.data.daos.interfaces.AuthorDAOInterface;
import com.application.data.entities.Article;
import com.application.data.entities.Author;
import com.application.services.business.interfaces.ArticleServiceInterface;
import com.application.services.business.interfaces.AuthorServiceInterface;

public class AuthorService implements AuthorServiceInterface {

	private AuthorDAOInterface authorDAO;
	private ArticleServiceInterface articleService;
	
	public AuthorService() {
		authorDAO = new AuthorDAO();
		articleService = new ArticleService();
	}

	@Override
	public boolean login(String userName, String password) {
		Author author = authorDAO.findByUserName(userName);
		if(author != null) {
			if(author.getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public List<Article> getArticles(String authorUserName) {
		return articleService.getArticles(authorUserName);
	}

	@Override
	public void createarticle(String title, String abstractContent, String body, String authorUserName) {
		Author author = authorDAO.findByUserName(authorUserName);
		articleService.saveArticle(title, abstractContent, body, author);
	}

	@Override
	public void updateArticle(String title, String abstractContent, String body, String authorUserName) {
		Author author = authorDAO.findByUserName(authorUserName);
		articleService.saveArticle(title, abstractContent, body, author);
	}

	@Override
	public void deleteArticle(String title, String authorUserName) {
		articleService.deleteArticle(title, authorUserName);		
	}
}
