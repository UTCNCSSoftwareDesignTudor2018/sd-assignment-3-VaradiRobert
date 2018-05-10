package com.application.connection;

import java.util.List;

import com.application.data.entities.Article;

public interface Observer {
	public void update(List<Article> articles);
}
