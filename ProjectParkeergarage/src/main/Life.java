package main;

import javax.swing.*;

import controller.*;
import logic.*;
import view.*;

/**
 * This class sets up the GUI on the screen of the life application.
 * 
 * @author Thomas Lambregts, Femke Hoornveld
 * @version 1.0 (11-04-2016)
 */
public class Life {
	private JFrame screen;
	private AbstractView fieldView;
	private AbstractView statView;
	private LifeLogic lifelogic;
	private AbstractController initController;
	private AbstractController runController;
	
	/**
	 * The Constructor creates new instances of LifeLogic, InitController, RunController, FieldView, StatView and screen.
	 * It sets the values (title, layout, size) for this screen, fills it and then makes it visible. 
	 */
	public Life() {
		lifelogic=new LifeLogic();
		initController=new InitController(lifelogic);
		runController=new RunController(lifelogic);
		fieldView=new FieldView(lifelogic);
		statView=new StatView(lifelogic);
		
		screen=new JFrame("The Conway game of Life");
		screen.setSize(540, 285);
		screen.setResizable(false);
		screen.setLayout(null);
		screen.getContentPane().add(fieldView);
		screen.getContentPane().add(statView);
		screen.getContentPane().add(runController);
		screen.getContentPane().add(initController);
		fieldView.setBounds(10, 10, 200, 200);
		statView.setBounds(230, 10, 200, 200);
		runController.setBounds(0, 210, 450, 50);
		initController.setBounds(440, 10, 90, 130);
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);
	}
}
