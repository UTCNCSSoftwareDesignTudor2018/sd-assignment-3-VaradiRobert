package com.application.data.daos.interfaces;

import java.util.List;

import com.application.data.entities.Article;

public interface ArticleDAOInterface {
	public void save(Article article);
	public List<Article> findAll();
	public List<Article> findAllByAuthorUserName(String userName);
}
