package controllers;

import java.util.List;

public interface Observer {
	public void update(List<ArticleProxy> articles);
}
