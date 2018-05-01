package com.application.data.daos;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.application.data.daos.interfaces.ArticleDAOInterface;
import com.application.data.daos.session.SessionFactoryBuilder;
import com.application.data.entities.Article;

public class ArticleDAO implements ArticleDAOInterface {

	@Override
	public void save(Article article) {
		SessionFactory factory = SessionFactoryBuilder.getSessionFactoryInstance();
		Session session = factory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		session.save(article);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> findAll() {
		SessionFactory factory = SessionFactoryBuilder.getSessionFactoryInstance();
		Session session = factory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		List<Article> articles = (List<Article>)session.createQuery("FROM Author").list();
		tx.commit();
		session.close();
		return articles;
	}

	@Override
	public List<Article> findAllByAuthorUserName(String userName) {
		List<Article> articles = findAll();
		return articles
				.stream()
				.filter(
						a -> {
							return a.getAuthor().getUserName().equals(userName);
						})
				.collect(Collectors.toList());
	}

}
