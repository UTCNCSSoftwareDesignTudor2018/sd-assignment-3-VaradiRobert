package com.application.controller.controllers;

import java.util.List;

import com.application.data.entities.Article;
import com.application.services.business.AuthorService;
import com.application.services.business.interfaces.AuthorServiceInterface;

public class WriterController {
	private String currentUserName;
	private AuthorServiceInterface writerService;
	
	public WriterController() {
		writerService = new AuthorService();
	}
	
	public boolean login(String userName, String password) {
		boolean loggedIn = writerService.login(userName, password);
		if(loggedIn) {
			this.currentUserName = userName;
		}
		return loggedIn;
	}
	public List<Article>createArticle(String title, String abstractContent, String body) {
		return null;
	}
	public List<Article> updateArticle(String oldTitle, String title, String abstractContent, String body) {
		return null;
	}
	public List<Article> deleteArticle(String title) {
		return null;
	}
	public String logout() {
		return null;
	}
}
