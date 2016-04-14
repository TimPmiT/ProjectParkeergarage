package main;

import javax.swing.*;

import controller.*;
import model.*;
import view.*;

/**
 * This class sets up the GUI on the screen of the life application.
 * 
 * @author Femke Hoornveld
 * @version 1.0 (11-04-2016)
 */
public class Life {
	private JFrame screen;
//	private AbstractView fieldView;
//	private AbstractView statView;
	private AbstractView carParkView;
//	private LifeLogic lifelogic;
    private CarParkLogic carPark;
//	private AbstractController initController;
    private AbstractController runController;
    
    private int tickPause;
    public static boolean run;
	
	/**
	 * The Constructor creates new instances of LifeLogic, InitController, RunController, FieldView, StatView and screen.
	 * It sets the values (title, layout, size) for this screen, fills it and then makes it visible. 
	 */
	public Life() {
//		lifelogic=new LifeLogic();
		carPark = new CarParkLogic(3, 6, 30);
//		initController = new InitController(lifelogic);
		runController = new RunController(carPark);
		carParkView = new CarParkView(carPark);
//		fieldView = new FieldView(lifelogic);
//		statView = new StatView(lifelogic);
		
		screen=new JFrame("Car park simulation");
		screen.setSize(1000, 750);
//		screen.setResizable(false);
		screen.setLayout(null);
//		screen.getContentPane().add(fieldView);
//		screen.getContentPane().add(statView);
		screen.getContentPane().add(carParkView);
		screen.getContentPane().add(runController);
//		screen.getContentPane().add(initController);
//		fieldView.setBounds(10, 10, 200, 200);
//		statView.setBounds(230, 10, 200, 200);
		carParkView.setBounds(0, 10, 680, 300);
		runController.setBounds(0, 320, 450, 50);
//		initController.setBounds(440, 10, 90, 130);
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);
		
		/**
		 *  Load the view
		 */
        carParkView.updateView();

        run = true;
        tickPause = 100;

        while(true){
            if (run) {
                carPark.tick();
            }
            try{
                Thread.sleep(tickPause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

	}
}
