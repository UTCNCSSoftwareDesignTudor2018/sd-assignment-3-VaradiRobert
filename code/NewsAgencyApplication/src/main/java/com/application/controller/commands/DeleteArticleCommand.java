package com.application.controller.commands;

public class DeleteArticleCommand implements Command {
	private String title;
	public DeleteArticleCommand(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
