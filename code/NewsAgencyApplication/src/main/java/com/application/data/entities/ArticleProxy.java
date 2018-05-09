package com.application.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proxies")
public class ArticleProxy {
	@Id @GeneratedValue
	private int id;
	
	@Column(name = "author_user_name")
	private String authorUserName;
	
	@Column(name = "author_full_name")
	private String authorFullName;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "summary", columnDefinition = "text")
	private String summary;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getAuthorUserName() {
		return authorUserName;
	}
	public void setAuthorUserName(String authorUserName) {
		this.authorUserName = authorUserName;
	}
	public String getAuthorFullName() {
		return authorFullName;
	}
	public void setAuthorFullName(String authorFullName) {
		this.authorFullName = authorFullName;
	}
}
