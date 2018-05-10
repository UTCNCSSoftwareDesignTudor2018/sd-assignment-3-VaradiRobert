package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import commands.Command;
import commands.CreateArticleCommand;
import commands.ReadArticleCommand;
import commands.ViewAuthorsArticlesCommand;
import commands.WriterLoginCommand;


public class Controller extends Observable {
    private BufferedReader in;
    private PrintWriter out;
    private ObjectMapper mapper;
    private JSONParser parser;
    
    public Controller() throws IOException {
    	super();
    	Client client = new Client();
        this.in = client.getIn();
        this.out = client.getOut();
        this.mapper = new ObjectMapper();
        this.parser = new JSONParser();
        /**new Thread() {
        	@Override
        	public void run() {
        		Socket socket = null;
				try {
					socket = new Socket("localhost", 8091);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                try {
					in = new BufferedReader(
					        new InputStreamReader(socket.getInputStream()));
					while(true) {
						String jsonString = in.readLine();
						JSONArray jsonArray = (JSONArray) parser.parse(jsonString);
						List<ArticleProxy> articles = new ArrayList<ArticleProxy>();
						int length = jsonArray.size();
						for(int i = 0; i < length; i++) {
							JSONObject o = (JSONObject) jsonArray.get(i);
							articles.add(new ArticleProxy(o.get("title").toString(), o.get("summary").toString(), o.get("authorFullName").toString(), o.get("authorUserName").toString()));
						}
						notifyAllObservers(articles);
					}
				} catch (IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }.start();*/
    }
    
    public String sendMessage(String jsonString) {
    	out.println(jsonString);
    	out.flush();
		String response;
		try {
		    response = in.readLine();
		    if (response == null || response.equals("")) {
		          System.exit(0);
		    }
		} catch (IOException ex) {
		       response = "Error: " + ex;
		}
		return response;
    }
    
    private String userName;

    public String getCurrentWriterUserName() {
    	return userName;
    }
    
	public List<ArticleProxy> sendWriterLoginCommand(String userName, String password) throws JsonGenerationException, JsonMappingException, IOException, ParseException {
		WriterLoginCommand loginCommand = new WriterLoginCommand(userName, password);
		String jsonString = mapper.writeValueAsString(loginCommand);
		out.println(Command.Constants.WRITER_LOGIN_COMMAND);
		out.println(jsonString);
		String response = in.readLine();
		JSONObject obj = (JSONObject) parser.parse(response);
		boolean loggedIn = (boolean)obj.get("loggedIn");
		List<ArticleProxy> articleProxies= null;
		if(loggedIn) {
			this.userName = userName;
			articleProxies = sendViewAllArticlesCommand(userName);
		}
		return articleProxies;
	}
	
	public List<ArticleProxy> sendViewAllArticlesCommand(String userName) throws IOException, ParseException {
		ViewAuthorsArticlesCommand command = new ViewAuthorsArticlesCommand(userName);
		String jsonString = mapper.writeValueAsString(command);
		out.println(Command.Constants.VIEW_AUTHORS_ARTICLES_COMMAND);
		out.println(jsonString);
		String response = in.readLine();
		System.out.println(response);
		JSONArray jsonArray = (JSONArray) parser.parse(response);
		List<ArticleProxy> articleProxies = new ArrayList<ArticleProxy>();
		int length = jsonArray.size();
		for(int i = 0; i < length; i++) {
			JSONObject o = (JSONObject) jsonArray.get(i);
			articleProxies.add(new ArticleProxy(o.get("title").toString(), o.get("summary").toString(), o.get("authorFullName").toString(), o.get("authorUserName").toString()));
		}
		for(ArticleProxy ap : articleProxies) {
			System.out.println(ap.getTitle() + " " + ap.getSummary() + " " + ap.getAuthorFullName() + " " + ap.getAuthorUserName());
		}
		return articleProxies;
	}
	
	public List<ArticleProxy> sendViewAllArticlesCommand() throws IOException, ParseException {
		out.println(Command.Constants.VIEW_ARTICLES_COMMAND);
		String response = in.readLine();
		System.out.println(response);
		JSONArray jsonArray = (JSONArray) parser.parse(response);
		List<ArticleProxy> articleProxies = new ArrayList<ArticleProxy>();
		int length = jsonArray.size();
		for(int i = 0; i < length; i++) {
			JSONObject o = (JSONObject) jsonArray.get(i);
			articleProxies.add(new ArticleProxy(o.get("title").toString(), o.get("summary").toString(), o.get("authorFullName").toString(), o.get("authorUserName").toString()));
		}
		for(ArticleProxy ap : articleProxies) {
			System.out.println(ap.getTitle() + " " + ap.getSummary() + " " + ap.getAuthorFullName() + " " + ap.getAuthorUserName());
		}
		return articleProxies;
	}

	public Article sendReadArticleCommand(String authorUserName, String title) throws JsonGenerationException, JsonMappingException, IOException, ParseException {
		ReadArticleCommand readArticleCommand = new ReadArticleCommand(authorUserName, title);
		String jsonString = mapper.writeValueAsString(readArticleCommand);
		out.println(Command.Constants.READ_ARTICLE_COMMAND);
		out.println(jsonString);
		String response = in.readLine();
		System.out.println("Client: " + response);
		JSONObject articleObject = (JSONObject)parser.parse(response);
		JSONObject authorObject = (JSONObject)articleObject.get("author");
		Article article = new Article();
		Author author = new Author();
		author.setFirstName(authorObject.get("firstName").toString());
		author.setLastName(authorObject.get("lastName").toString());
		author.setUserName(authorObject.get("userName").toString());
		article.setAbstractContent(articleObject.get("abstractContent").toString());
		article.setBody(articleObject.get("body").toString());
		article.setTitle(articleObject.get("title").toString());
		article.setAuthor(author);
		return article;
	}

	public List<ArticleProxy> sendSaveArticleCommand(String authorUserName, String title, String summary, String content) throws JsonGenerationException, JsonMappingException, IOException, ParseException {
		CreateArticleCommand command = new CreateArticleCommand(authorUserName, title, summary, content);
		String jsonString = mapper.writeValueAsString(command);
		out.println(Command.Constants.CREATE_ARTICLE_COMMAND);
		out.println(jsonString);
		String response = in.readLine();
		JSONArray jsonArray = (JSONArray) parser.parse(response);
		List<ArticleProxy> articleProxies = new ArrayList<ArticleProxy>();
		int length = jsonArray.size();
		for(int i = 0; i < length; i++) {
			JSONObject o = (JSONObject) jsonArray.get(i);
			articleProxies.add(new ArticleProxy(o.get("title").toString(), o.get("summary").toString(), o.get("authorFullName").toString(), o.get("authorUserName").toString()));
		}
		for(ArticleProxy ap : articleProxies) {
			System.out.println(ap.getTitle() + " " + ap.getSummary() + " " + ap.getAuthorFullName() + " " + ap.getAuthorUserName());
		}
		return articleProxies;
	}
}
