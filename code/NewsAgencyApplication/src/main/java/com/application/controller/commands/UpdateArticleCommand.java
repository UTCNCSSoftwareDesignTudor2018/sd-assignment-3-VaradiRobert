package com.application.controller.commands;

public class UpdateArticleCommand implements Command {
	private String oldTitle;
	private String title;
	private String abstractContent;
	private String body;
	public UpdateArticleCommand(String title, String abstractContent, String body) {
		this.title = title;
		this.abstractContent = abstractContent;
		this.body = body;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbstractContent() {
		return abstractContent;
	}
	public void setAbstractContent(String abstractContent) {
		this.abstractContent = abstractContent;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getOldTitle() {
		return oldTitle;
	}
	public void setOldTitle(String oldTitle) {
		this.oldTitle = oldTitle;
	}
}
