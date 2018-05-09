package com.application.controller.controllers;

import java.util.List;

import com.application.data.entities.Article;
import com.application.services.business.ArticleService;
import com.application.services.business.interfaces.ArticleServiceInterface;

public class ReaderController {
	private ArticleServiceInterface articleService;
	public ReaderController() {
		articleService = new ArticleService();
	}
	
	public List<Article> getArticles() {
		return articleService.getArticles();
	}
	public Article getArticle(String authorUserName, String title) {
		return articleService.getArticle(authorUserName, title);
	}
}
