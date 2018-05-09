package views;

import controllers.Article;
import controllers.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewArticleAsReaderScene extends Scene {
	private Stage stage;
	private Controller controller;
	private Label authorNameLabel;
	private Label titleLabel;
	private TextArea summaryTextArea;
	private ScrollPane summaryScrollPane;
	private TextArea contentTextArea;
	private ScrollPane contentScrollPane;
	private Article article;
	public ViewArticleAsReaderScene(Controller controller, Stage stage, Article article, GridPane grid) {
		super(grid);
		this.controller = controller;
		this.stage = stage;
		this.article = article;
		initializeComponents();
		addComponents(grid);
	}
	
	private void addComponents(GridPane grid) {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
		grid.add(titleLabel, 0, 0);
		grid.add(authorNameLabel, 0, 1);
		grid.add(summaryScrollPane, 1, 2);
		grid.add(contentScrollPane, 0, 2);
	}
	
	private void initializeComponents() {
		authorNameLabel = new Label("Author: " + article.getAuthor().getFirstName() + " " + article.getAuthor().getLastName());
		titleLabel = new Label("Title: " + article.getTitle());
		summaryTextArea = new TextArea();
		summaryTextArea.setPrefWidth(400);
		summaryTextArea.setPrefHeight(295);
		summaryTextArea.setWrapText(true);
		summaryTextArea.setEditable(false);
		summaryScrollPane = new ScrollPane();
        summaryScrollPane.setContent(summaryTextArea);
        summaryScrollPane.setFitToWidth(true);
        summaryScrollPane.setPrefWidth(400);
        summaryScrollPane.setPrefHeight(300);
        summaryTextArea.setText(article.getAbstractContent());
        contentTextArea = new TextArea();
		contentTextArea.setPrefWidth(400);
		contentTextArea.setPrefHeight(295);
		contentTextArea.setWrapText(true);
		contentTextArea.setEditable(false);
		contentScrollPane = new ScrollPane();
        contentScrollPane.setContent(contentTextArea);
        contentScrollPane.setFitToWidth(true);
        contentScrollPane.setPrefWidth(400);
        contentScrollPane.setPrefHeight(300);
        contentTextArea.setText(article.getBody());
	}
	
	private void setActionListeners() {
		
	}
}
