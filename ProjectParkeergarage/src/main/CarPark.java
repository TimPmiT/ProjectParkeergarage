package main;

import javax.swing.*;

import controller.*;
import model.*;
import view.*;

/**
 * This class sets up the GUI on the screen of the life application.
 * 
 * @author Femke Hoornveld, Koen Gorter
 * @version 1.1 (11-04-2016)
 */
public class CarPark {
	private JFrame screen;
	private AbstractView statView;
	private AbstractView dayView;
	private AbstractView carParkView;
	private AbstractView floorLabelView;
	private AbstractView pieView;
	private AbstractView statPieView;
    private CarParkLogic carPark;
    private AbstractController runController;
    private AbstractController dayController;
    private AbstractController settingsController;
    
    private int tickPause;
    public static boolean run;
	
	/**
	 * The Constructor creates new instances of LifeLogic, InitController, RunController, FieldView, StatView and screen.
	 * It sets the values (title, layout, size) for this screen, fills it and then makes it visible. 
	 */
	public CarPark() {
		carPark = new CarParkLogic(3, 6, 30);
		runController = new RunController(carPark);
		dayController = new DayController(carPark);
		settingsController = new SettingsController(carPark);
		carParkView = new CarParkView(carPark);
		floorLabelView = new FloorLabelView(carPark);
		pieView = new PieView(carPark);
		statPieView = new StatPieView(carPark);
		statView = new StatView(carPark);
		dayView = new DayView(carPark);
		
		screen=new JFrame("Car park simulation");
		screen.setSize(1000, 1000);
//		screen.setResizable(false);
		screen.setLayout(null);
		screen.getContentPane().add(statView);
		screen.getContentPane().add(dayView);
		screen.getContentPane().add(carParkView);
		screen.getContentPane().add(floorLabelView);
		screen.getContentPane().add(pieView);
		screen.getContentPane().add(statPieView);
		screen.getContentPane().add(runController);
		screen.getContentPane().add(dayController);
		screen.getContentPane().add(settingsController);

		statView.setBounds(50, 448, 250, 200);
		dayView.setBounds(50, 643, 200, 40);
		carParkView.setBounds(50, 10, 700, 330);
		floorLabelView.setBounds(0, 340, 700, 20);
		pieView.setBounds(300, 450, 390, 210);
		statPieView.setBounds(300, 660, 410, 150);
		runController.setBounds(0, 370, 750, 80);
		dayController.setBounds(50, 693, 200, 150);
		settingsController.setBounds(760, 10, 200, 300);

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
