package com.application.controller.facade;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.application.controller.commands.CreateArticleCommand;
import com.application.controller.commands.DeleteArticleCommand;
import com.application.controller.commands.LogoutCommand;
import com.application.controller.commands.UpdateArticleCommand;
import com.application.controller.commands.ViewArticlesCommand;
import com.application.controller.commands.ReadArticleCommand;
import com.application.controller.commands.ViewAuthorsArticlesCommand;
import com.application.controller.commands.WriterLoginCommand;
import com.application.controller.controllers.ReaderController;
import com.application.controller.controllers.WriterController;
import com.application.data.entities.Article;

public class ApplicationFacade {
	private WriterController writerController;
	private ReaderController readerController;
	private ObjectMapper mapper;
	public ApplicationFacade() {
		writerController = new WriterController();
		readerController = new ReaderController();
		mapper = new ObjectMapper();
	}

	@SuppressWarnings("unchecked")
	public String execute(WriterLoginCommand command) throws JsonGenerationException, JsonMappingException, IOException {
		boolean loggedIn = writerController.login(command.getUserName(), command.getPassword());
		JSONObject object = new JSONObject();
		object.put("loggedIn", loggedIn);
		return mapper.writeValueAsString(object);
	}
	public String execute(LogoutCommand command) {
		return null;
	}
	@SuppressWarnings("unchecked")
	public String execute(CreateArticleCommand command) {
		List<Article> articles = writerController.createArticle(command.getUserName(), command.getTitle(), command.getAbstractContent(), command.getBody());
		JSONArray objects = new JSONArray();
		for(Article a : articles) {
			JSONObject obj = new JSONObject();
			obj.put("title", a.getTitle());
			obj.put("summary", a.getAbstractContent());
			obj.put("authorFullName", a.getAuthor().getFirstName() + " " + a.getAuthor().getLastName());
			obj.put("authorUserName", a.getAuthor().getUserName());
			objects.add(obj);
		}
		String jsonResponse = null;
		try {
			jsonResponse = mapper.writeValueAsString(objects);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(jsonResponse);
		return jsonResponse;
	}
	public String execute(UpdateArticleCommand command) {
		writerController.updateArticle(command.getOldTitle(), command.getTitle(), command.getAbstractContent(), command.getBody());
		return "Update";
	}
	public String execute(DeleteArticleCommand command) {
		writerController.deleteArticle(command.getTitle());
		return "Update";
	}
	
	@SuppressWarnings("unchecked")
	public String execute(ViewAuthorsArticlesCommand command) {
		List<Article> allArticles = writerController.getArticles();
		JSONArray objects = new JSONArray();
		for(Article a : allArticles) {
			JSONObject obj = new JSONObject();
			obj.put("title", a.getTitle());
			obj.put("summary", a.getAbstractContent());
			obj.put("authorFullName", a.getAuthor().getFirstName() + " " + a.getAuthor().getLastName());
			obj.put("authorUserName", a.getAuthor().getUserName());
			objects.add(obj);
		}
		String jsonResponse = null;
		try {
			jsonResponse = mapper.writeValueAsString(objects);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonResponse);
		return jsonResponse;
	}
	
	@SuppressWarnings("unchecked")
	public String execute(ViewArticlesCommand command) {
		List<Article> allArticles = readerController.getArticles();
		JSONArray objects = new JSONArray();
		for(Article a : allArticles) {
			JSONObject obj = new JSONObject();
			obj.put("title", a.getTitle());
			obj.put("summary", a.getAbstractContent());
			obj.put("authorFullName", a.getAuthor().getFirstName() + " " + a.getAuthor().getLastName());
			obj.put("authorUserName", a.getAuthor().getUserName());
			objects.add(obj);
		}
		String jsonResponse = null;
		try {
			jsonResponse = mapper.writeValueAsString(objects);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonResponse;
	}
	
	public String execute(ReadArticleCommand command) {
		Article article = readerController.getArticle(command.getAuthorUserName(), command.getTitle());
		String jsonResponse = null;
		try {
			jsonResponse = mapper.writeValueAsString(article);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonResponse;
	}
}
