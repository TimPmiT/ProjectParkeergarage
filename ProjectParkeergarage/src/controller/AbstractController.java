package controller;

import javax.swing.*;

import model.*;

/**
 * All controllers should extend AbstractController.
 * Initializes the variable model with a CarParkLogic object.
 * 
 * @authors Femke Hoornveld, Koen Gorter
 * @version 1.0 (11-04-2016)
 *
 */
public abstract class AbstractController extends JPanel {
	protected AbstractModel model;
	
	/**
	 * The constructor initializes the instance variable model with a model that applies to this controller.
	 * @param model Model life is ascribed to the instance variable life
	 */
	public AbstractController(AbstractModel model) {
		this.model = model;
	}
}
