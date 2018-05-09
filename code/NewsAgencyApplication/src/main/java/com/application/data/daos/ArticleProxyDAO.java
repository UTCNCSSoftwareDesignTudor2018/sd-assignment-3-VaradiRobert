package com.application.data.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.application.data.daos.interfaces.ArticleProxyDAOInterface;
import com.application.data.daos.session.SessionFactoryBuilder;
import com.application.data.entities.ArticleProxy;

public class ArticleProxyDAO implements ArticleProxyDAOInterface {
	@Override
	public void save(ArticleProxy article) {
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
	public List<ArticleProxy> findAll() {
		SessionFactory factory = SessionFactoryBuilder.getSessionFactoryInstance();
		Session session = factory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		List<ArticleProxy> articles = (List<ArticleProxy>)session.createQuery("FROM ArticleProxy").list();
		tx.commit();
		session.close();
		return articles;
	}

}
