package com.application.controller.commands;

public class ViewAuthorsArticlesCommand implements Command {
	private String authorUserName;

	public ViewAuthorsArticlesCommand(String authorUserName) {
		this.authorUserName = authorUserName;
	}
	public String getAuthorUserName() {
		return authorUserName;
	}
	public void setAuthorUserName(String authorUserName) {
		this.authorUserName = authorUserName;
	}
	
}
