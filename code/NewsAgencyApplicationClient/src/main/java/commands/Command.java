package commands;

public interface Command {
	static final class Constants {
		public static final String WRITER_LOGIN_COMMAND = "WriterLoginCommand";
		public static final String LOGOUT_COMMAND = "LogoutCommand";
		public static final String CREATE_ARTICLE_COMMAND = "CreateArticleCommand";
		public static final String UPDATE_ARTICLE_COMMAND = "UpdateArticleCommand";
		public static final String DELETE_ARTICLE_COMMAND = "DeleteArticleCommand";
		public static final String VIEW_ARTICLES_COMMAND = "ViewArticlesCommand";
		public static final String VIEW_AUTHORS_ARTICLES_COMMAND = "ViewAuthorsArticlesCommand";
		public static final String READ_ARTICLE_COMMAND = "ReadArticleCommand";
	}
}
