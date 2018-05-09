package views;

import java.io.IOException;

import controllers.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class View extends Application {

    public static void main(String [] args) {
        Application.launch(args);
    }

	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("News Agency Application");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        try {
			primaryStage.setScene(new LoginScene(grid, new Controller(), primaryStage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        primaryStage.show();
    }
	 
}