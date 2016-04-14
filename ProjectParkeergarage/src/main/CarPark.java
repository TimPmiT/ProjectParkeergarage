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
public class CarPark {
	private JFrame screen;
//	private AbstractView fieldView;
	private AbstractView statView;
	private AbstractView revenueView; 
	private AbstractView carParkView;
	private AbstractView pieView;
	private AbstractView statPieView;
//	private LifeLogic lifelogic;
    private CarParkLogic carPark;
//	private AbstractController initController;
    private AbstractController runController;
    private AbstractController settingsController;
    
    private int tickPause;
    public static boolean run;
	
	/**
	 * The Constructor creates new instances of LifeLogic, InitController, RunController, FieldView, StatView and screen.
	 * It sets the values (title, layout, size) for this screen, fills it and then makes it visible. 
	 */
	public CarPark() {
//		lifelogic=new LifeLogic();
		carPark = new CarParkLogic(3, 6, 30);
//		initController = new InitController(lifelogic);
		runController = new RunController(carPark);
		settingsController = new SettingsController(carPark);
		carParkView = new CarParkView(carPark);
		pieView = new PieView(carPark);
		statPieView = new StatPieView(carPark);
//		fieldView = new FieldView(lifelogic);
		statView = new StatView(carPark);
		revenueView = new RevenueView(carPark); 
		
		screen=new JFrame("Car park simulation");
		screen.setSize(1000, 1000);
//		screen.setResizable(false);
		screen.setLayout(null);
//		screen.getContentPane().add(fieldView);
		screen.getContentPane().add(statView);
		screen.getContentPane().add(carParkView);
		screen.getContentPane().add(pieView);
		screen.getContentPane().add(statPieView);
		screen.getContentPane().add(runController);
		screen.getContentPane().add(settingsController);
//		screen.getContentPane().add(initController);
		screen.getContentPane().add(revenueView);
//		fieldView.setBounds(10, 10, 200, 200);
		statView.setBounds(50, 390, 220, 300);
		carParkView.setBounds(50, 10, 700, 300);
		pieView.setBounds(300, 390, 400, 210);
		statPieView.setBounds(300, 600, 400, 150);
		runController.setBounds(0, 320, 700, 50);
		settingsController.setBounds(760, 10, 200, 300);
		revenueView.setBounds(700, 390, 250, 300);
//		initController.setBounds(440, 10, 90, 130);
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);
		
		/**
		 *  Load the view
		 */
        carParkView.updateView();

        run = true;
        tickPause = 500;

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
