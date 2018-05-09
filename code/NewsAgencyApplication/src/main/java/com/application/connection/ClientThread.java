package com.application.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.application.controller.commands.Command;
import com.application.controller.commands.ReadArticleCommand;
import com.application.controller.commands.ViewArticlesCommand;
import com.application.controller.commands.WriterLoginCommand;
import com.application.controller.facade.ApplicationFacade;

public class ClientThread extends Thread {
    private Socket socket;
    private int clientNumber;
    private ApplicationFacade applicationFacade;
    private JSONParser parser;
    private BufferedReader in;
    private PrintWriter out;

    public ClientThread(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        log("New connection with client# " + clientNumber + " at " + socket);
        this.applicationFacade = new ApplicationFacade();
        this.parser = new JSONParser();
    }
    
    public String executeCommand(String commandType) throws JsonParseException, JsonMappingException, IOException, ParseException {
    	String response = null;
    	if(commandType.equals(Command.Constants.WRITER_LOGIN_COMMAND)) {
    		String jsonString = in.readLine();
    		System.out.println(jsonString);
    		JSONObject object = (JSONObject) parser.parse(jsonString);
    		WriterLoginCommand writerLoginCommand = new WriterLoginCommand(object.get("userName").toString(), object.get("password").toString());
    		response = applicationFacade.execute(writerLoginCommand);
    		System.out.println(response);
    	}
    	else if(commandType.equals(Command.Constants.VIEW_ARTICLES_COMMAND)) {
    		response = applicationFacade.execute(new ViewArticlesCommand());
    	}
    	else if(commandType.equals(Command.Constants.READ_ARTICLE_COMMAND)) {
    		String jsonString = in.readLine();
    		System.out.println(jsonString);
    		JSONObject object = (JSONObject)parser.parse(jsonString);
    		ReadArticleCommand readArticleCommand = new ReadArticleCommand(object.get("authorUserName").toString(), object.get("title").toString());
    		response = applicationFacade.execute(readArticleCommand);
    	}
    	System.out.println("Server response: " + response);
		return response;
    }

    public void run() {
        try {
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                String commandType = in.readLine();
                System.out.println(commandType);
                String response = null;
                try {
					response = executeCommand(commandType);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                out.println(response);
            }
        } catch (IOException e) {
            log("Error handling client# " + clientNumber + ": " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                log("Couldn't close a socket, what's going on?");
            }
            log("Connection with client# " + clientNumber + " closed");
        }
    }
    private void log(String message) {
        System.out.println(message);
    }
}