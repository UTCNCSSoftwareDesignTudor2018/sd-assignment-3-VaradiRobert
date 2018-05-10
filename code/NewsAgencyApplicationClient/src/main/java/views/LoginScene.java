package views;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import controllers.ArticleProxy;
import controllers.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScene extends Scene {
	private Text sceneTitle;
	private Button loginButton;
	private Label userNameLabel;
	private Label passwordLabel;
	private TextField userNameTextField;
	private PasswordField passwordField;
	private Button readerButton;
	private Button exitButton;
	private GridPane parent;
	private Controller controller;
	private Stage stage;
	
	private void initializeComponents() {
		sceneTitle = new Text("Welcome");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		loginButton = new Button("Login as Writer");
		readerButton = new Button("I am a reader");
		passwordField = new PasswordField();
		userNameTextField = new TextField();
		userNameLabel = new Label("User Name: ");
		passwordLabel = new Label("Password: ");
		exitButton = new Button("Exit");
	}
	
	private void setActionListeners() {
		loginButton.setOnAction(new EventHandler<ActionEvent>() {	 
    	    @Override
    	    public void handle(ActionEvent e) {
    	        try {
    	        	String userName = userNameTextField.getText();
					List<ArticleProxy> articles = controller.sendWriterLoginCommand(userName, passwordField.getText());
					if(articles != null) {
						stage.setScene(new ViewArticlesAsWriterScene(controller, userName, new VBox(20), articles, 500, 500, stage));
					} else {
						
					}
				} catch (JsonGenerationException e1) {
					e1.printStackTrace();
				} catch (JsonMappingException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	    }
    	});
		readerButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					List<ArticleProxy> articles = controller.sendViewAllArticlesCommand();
					if(articles != null) {
						stage.setScene(new ViewArticlesAsReaderScene(controller, new VBox(20), articles, 500, 500, stage));
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				((Stage)exitButton.getScene().getWindow()).close();
			}
		});
	}
	
	private void addComponents() {
		parent.add(sceneTitle, 0, 0, 2, 1);
    	parent.add(userNameLabel, 0, 1);
    	parent.add(userNameTextField, 1, 1);
    	parent.add(passwordLabel, 0, 2);
    	parent.add(passwordField, 1, 2);
    	HBox hbBtn = new HBox(10);
    	hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
    	hbBtn.getChildren().add(loginButton);
    	hbBtn.getChildren().add(readerButton);
    	hbBtn.getChildren().add(exitButton);
    	parent.add(hbBtn, 1, 4);
	}
	
	public LoginScene(GridPane parent, Controller controller, Stage stage) {
		super(parent, 500, 400);
		this.parent = parent;
		initializeComponents();
    	addComponents();
    	setActionListeners();
    	this.controller = controller;
    	this.stage = stage;
	}
	
}
