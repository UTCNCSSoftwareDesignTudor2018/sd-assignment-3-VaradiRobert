package controllers;

import java.util.ArrayList;
import java.util.List;


public class Observable {
	public List<Observer> observers;
	public Observable() {
		observers = new ArrayList<Observer>();
	}
	public void register(Observer observer) {
		observers.add(observer);
	}
	public void unregisterObserver(Observer observer) {
		observers.remove(observer);
	}
	
	public void notifyAllObservers(List<ArticleProxy> articles) {
		for(Observer o : observers) {
			o.update(articles);
		}
	}
}
