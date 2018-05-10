package commands;

public class ReadArticleCommand implements Command {
	private String authorUserName;
	private String title;
	public ReadArticleCommand(String authorUserName, String title) {
		this.authorUserName = authorUserName;
		this.title = title;
	}
	public String getAuthorUserName() {
		return authorUserName;
	}
	public void setAuthorUserName(String authorUserName) {
		this.authorUserName = authorUserName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
