package model;

import view.*;
import java.util.*;
 /**
 * AbstractModel is a class that has several subclasses that use model as backbone and extend on it.
 * This class is used for polymorphism.
 * 
 * @authors Femke Hoornveld, Koen Gorter
 * @version 1.0 (11-04-2016)
 */
public abstract class AbstractModel {
	private List<AbstractView> views;
	
	/**
	 * The constructor creates an ArrayList that can hold views that applies to the created model.
	 */
	public AbstractModel() {
		views=new ArrayList<AbstractView>();
	}
	
	/**
	 * This method adds a view of the model to the ArrayList.
	 * @param view View of the model that needs to be added to the ArrayList
	 */
	public void addView(AbstractView view) {
		views.add(view);
	}
	
	/**
	 * This method updates all the views that apply to the model. 
	 */
	public void notifyViews() {
		for(AbstractView v: views) v.updateView();
	}
	
	public void tick() {
	}
	
	/**
	 * Empty methods to override in a subclass.
	 */
	public void setSteps(int steps) {}
	
	public void setEnterSpeed(int newSpeed) {}
	public void setPaySpeed(int newSpeed){}
	public void setExitSpeed(int newSpeed){}
	public int getEnterSpeed() { return 0; }
	public int getPaySpeed() { return 0; }
	public int getExitSpeed(){ return 0; }
}