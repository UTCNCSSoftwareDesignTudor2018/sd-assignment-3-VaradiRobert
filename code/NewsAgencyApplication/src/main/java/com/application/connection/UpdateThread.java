package com.application.connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.application.data.entities.Article;
import com.application.services.business.ArticleService;

public class UpdateThread implements Observer {
	private Socket socket;
	private int clientNumber;
	private PrintWriter out;
	private ObjectMapper mapper;
	
	public UpdateThread(Socket socket, int clientNumber) {
		mapper = new ObjectMapper();
		this.socket = socket;
		this.clientNumber = clientNumber;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch(IOException e) {
			
		}
		((ArticleService)ArticleService.getInstance()).register(this);
		System.out.println("UpdateSocket: New connection with " + clientNumber + " at " + socket);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(List<Article> articles) {
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
		out.println(jsonResponse);
	}
}
