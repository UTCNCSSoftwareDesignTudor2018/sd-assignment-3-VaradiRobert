package com.application.connection;

import java.util.ArrayList;
import java.util.List;

import com.application.data.entities.Article;

public class Observable {
	private List<Observer> observers;
	public Observable() {
		observers = new ArrayList<Observer>();
	}
	public void register(Observer observer) {
		observers.add(observer);
	}
	
	public void notifyAllObservers(List<Article> articles) {
		for(Observer o : observers) {
			o.update(articles);
		}
	}
}
