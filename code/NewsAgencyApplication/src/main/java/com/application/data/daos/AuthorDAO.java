package com.application.data.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.application.data.daos.interfaces.AuthorDAOInterface;
import com.application.data.daos.session.SessionFactoryBuilder;
import com.application.data.entities.Author;

public class AuthorDAO implements AuthorDAOInterface {

	@Override
	public void save(Author author) {
		SessionFactory factory = SessionFactoryBuilder.getSessionFactoryInstance();
		Session session = factory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		session.save(author);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Author> findAll() {
		SessionFactory factory = SessionFactoryBuilder.getSessionFactoryInstance();
		Session session = factory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		List<Author> authors = (List<Author>)session.createQuery("FROM Author").list();
		tx.commit();
		session.close();
		return authors;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Author findById(int id) {
		String hql = "FROM Author A WHERE A.id = " + id;
		SessionFactory factory = SessionFactoryBuilder.getSessionFactoryInstance();
		Session session = factory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		List<Author> authors = (List<Author>)session.createQuery(hql).list();
		tx.commit();
		session.close();
		return authors.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Author findByUserName(String userName) {
		String hql = "FROM Author A WHERE A.userName = \'" + userName + "\'";
		SessionFactory factory = SessionFactoryBuilder.getSessionFactoryInstance();
		Session session = factory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		List<Author> authors = (List<Author>)session.createQuery(hql).list();
		tx.commit();
		session.close();
		return authors.get(0);
	}

}
