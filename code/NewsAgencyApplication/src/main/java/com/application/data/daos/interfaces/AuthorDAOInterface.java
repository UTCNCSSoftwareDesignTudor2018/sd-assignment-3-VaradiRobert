package com.application.data.daos.interfaces;

import java.util.List;

import com.application.data.entities.Author;

public interface AuthorDAOInterface {
	public void save(Author author);
	public List<Author> findAll();
	public Author findById(int id);
	public Author findByUserName(String userName);
}
