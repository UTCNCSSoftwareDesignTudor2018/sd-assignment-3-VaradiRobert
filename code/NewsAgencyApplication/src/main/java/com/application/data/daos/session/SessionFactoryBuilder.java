package com.application.data.daos.session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

@SuppressWarnings("deprecation")
public class SessionFactoryBuilder {
	private static SessionFactory factory;
	public static SessionFactory getSessionFactoryInstance() {
		if(factory == null) {
			factory = new AnnotationConfiguration()
					.configure()
					.addAnnotatedClass(com.application.data.entities.Article.class)
					.addAnnotatedClass(com.application.data.entities.Author.class)
					.buildSessionFactory();
		}
		return factory;
	}
}
