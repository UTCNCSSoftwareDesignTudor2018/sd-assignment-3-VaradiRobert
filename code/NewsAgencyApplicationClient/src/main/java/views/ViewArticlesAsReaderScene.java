package views;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import controllers.Article;
import controllers.ArticleProxy;
import controllers.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ViewArticlesAsReaderScene extends Scene {
	@SuppressWarnings("rawtypes")
	private TableView table;
    @SuppressWarnings("rawtypes")
	private ObservableList data;
    private Controller controller;
    private Stage stage;
    
    public ViewArticlesAsReaderScene(Controller controller, VBox parent, List<ArticleProxy> articles, int width, int height, Stage stage) {
    	super(parent);
    	this.controller = controller;
    	this.stage = stage;
    	Label label = new Label("Articles List");
        label.setTextFill(Color.DARKBLUE);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(label);
    	initializeTable(articles);
    	parent.setPadding(new Insets(25, 25, 25, 25));;
        parent.getChildren().addAll(hb, table);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void initializeTable(List<ArticleProxy> articles) {
        table = new TableView();
        data = getInitialTableData(articles);
        table.setItems(data);

        TableColumn titleColumn = new TableColumn("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory("title"));
        TableColumn summaryColumn = new TableColumn("Summary");
        summaryColumn.setCellValueFactory(new PropertyValueFactory("summary"));
        TableColumn authorFullNameColumn = new TableColumn("Author Name");
        authorFullNameColumn.setCellValueFactory(new PropertyValueFactory("authorFullName"));
        
        table.getColumns().setAll(titleColumn, authorFullNameColumn, summaryColumn);
        table.setPrefWidth(450);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getSelectionModel().selectedIndexProperty().addListener(
                new RowSelectChangeListener());
    }
    
    @SuppressWarnings("rawtypes")
	private class RowSelectChangeListener implements ChangeListener {

		@Override
        public void changed(ObservableValue ov, Object oldVal, Object newVal) {

            int ix = Integer.parseInt(newVal.toString());
            if ((ix == data.size())) {
	
                return;
            }
            ArticleProxy ap = (ArticleProxy)data.get(ix);
            try {
				Article article = controller.sendReadArticleCommand(ap.getAuthorUserName(), ap.getTitle());
				stage.setScene(new ViewArticleAsReaderScene(controller, stage, article, new GridPane()));
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    @SuppressWarnings({ "rawtypes"})
	private ObservableList getInitialTableData(List<ArticleProxy> articleProxies) {

        ObservableList data = null;
		data = FXCollections.observableList(articleProxies);
        return data;
    }
}