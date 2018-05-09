package controllers;

import javafx.beans.property.SimpleStringProperty;

public class ArticleProxy {
	private SimpleStringProperty title;
	private SimpleStringProperty summary;
	private SimpleStringProperty authorFullName;
	private SimpleStringProperty authorUserName;
	
	public ArticleProxy(String title, String summary, String authorFullName, String authorUserName) {
		this.title = new SimpleStringProperty(title);
		this.summary = new SimpleStringProperty(summary);
		this.authorFullName = new SimpleStringProperty(authorFullName);
		this.authorUserName = new SimpleStringProperty(authorUserName);
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String title) {
		this.title = new SimpleStringProperty(title);
	}

	public String getSummary() {
		return summary.get();
	}

	public void setSummary(String summary) {
		this.summary = new SimpleStringProperty(summary);
	}

	public String getAuthorFullName() {
		return authorFullName.get();
	}

	public void setAuthorFullName(String authorFullName) {
		this.authorFullName = new SimpleStringProperty(authorFullName);
	}

	public String getAuthorUserName() {
		return authorUserName.get();
	}

	public void setAuthorUserName(String authorUserName) {
		this.authorUserName = new SimpleStringProperty(authorUserName);
	}
	
	
}
