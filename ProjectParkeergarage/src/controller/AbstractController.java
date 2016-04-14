package controller;

import logic.*;

import javax.swing.*;

/**
 * AbstractController is a class that all controllers should extend.
 * Initializes the variable life with a LifeLogic object.
 * 
 * @authors Femke Hoornveld, Koen Gorter
 * @version 1.0 (11-04-2016)
 *
 */
public abstract class AbstractController extends JPanel {
	protected LifeLogic life;
	
	/**
	 * The constructor initializes the instance variable life with a model that applies to this controller.
	 * @param life Model life is ascribed to the instance variable life
	 */
	public AbstractController(LifeLogic life) {
		this.life=life;
	}
}
