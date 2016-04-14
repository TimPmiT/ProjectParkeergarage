package controller;

import javax.swing.*;

import model.*;

/**
 * All controllers should extend AbstractController.
 * Initializes the variable life with a LifeLogic object.
 * 
 * @authors Femke Hoornveld, Koen Gorter
 * @version 1.0 (11-04-2016)
 *
 */
public abstract class AbstractController extends JPanel {
	protected AbstractModel model;
	
	/**
	 * The constructor initializes the instance variable life with a model that applies to this controller.
	 * @param life Model life is ascribed to the instance variable life
	 */
	public AbstractController(AbstractModel model) {
		this.model = model;
	}
}
