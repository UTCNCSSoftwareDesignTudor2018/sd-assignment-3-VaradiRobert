package com.application.data.daos.interfaces;

import java.util.List;

import com.application.data.entities.ArticleProxy;

public interface ArticleProxyDAOInterface {
	public void save(ArticleProxy article);
	public List<ArticleProxy> findAll();
}
