package views;


import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import controllers.ArticleProxy;
import controllers.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateArticleScene extends Scene {
	private Stage stage;
	private Controller controller;
	private Label titleLabel;
	private TextField titleTextField;
	private TextArea summaryTextArea;
	private ScrollPane summaryScrollPane;
	private TextArea contentTextArea;
	private ScrollPane contentScrollPane;
	private String authorUserName;
	private Button saveArticleButton;
	public CreateArticleScene(Controller controller, String authorUserName, Stage stage, GridPane grid) {
		super(grid);
		this.controller = controller;
		this.stage = stage;
		this.authorUserName = authorUserName;
		initializeComponents();
		addComponents(grid);
		setActionListeners();
	}
	
	private void addComponents(GridPane grid) {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
		grid.add(titleLabel, 0, 0);
		grid.add(titleTextField, 1, 0);
		grid.add(summaryScrollPane, 1, 2);
		grid.add(contentScrollPane, 0, 2);
		grid.add(saveArticleButton, 0, 3);
	}
	
	private void initializeComponents() {
		saveArticleButton = new Button("Save Article");
		titleLabel = new Label("Title: ");
		titleTextField = new TextField();
		summaryTextArea = new TextArea();
		summaryTextArea.setPrefWidth(400);
		summaryTextArea.setPrefHeight(295);
		summaryTextArea.setWrapText(true);
		summaryTextArea.setEditable(true);
		summaryScrollPane = new ScrollPane();
        summaryScrollPane.setContent(summaryTextArea);
        summaryScrollPane.setFitToWidth(true);
        summaryScrollPane.setPrefWidth(400);
        summaryScrollPane.setPrefHeight(300);
        contentTextArea = new TextArea();
		contentTextArea.setPrefWidth(400);
		contentTextArea.setPrefHeight(295);
		contentTextArea.setWrapText(true);
		contentTextArea.setEditable(true);
		contentScrollPane = new ScrollPane();
        contentScrollPane.setContent(contentTextArea);
        contentScrollPane.setFitToWidth(true);
        contentScrollPane.setPrefWidth(400);
        contentScrollPane.setPrefHeight(300);
	}
	
	private void setActionListeners() {
		saveArticleButton.setOnAction(new EventHandler<ActionEvent>() {	 
    	    @Override
    	    public void handle(ActionEvent e) {
    	    	try {
					List<ArticleProxy> articles = controller.sendSaveArticleCommand(authorUserName, titleTextField.getText(), summaryTextArea.getText(), contentTextArea.getText());
					stage.setScene(new ViewArticlesAsWriterScene(controller, authorUserName, new VBox(), articles, 500, 500, stage));
    	    	} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
    	    }
    	});
	}
}
