package controller;

import main.*;
import model.*;
import exception.*;

import java.awt.event.*;
import javax.swing.*;

/**
 * This class creates the buttons stepOne, startSteps and stopSteps, and the text field steps.
 * It catches the input the input is used on the model, and the view will be updated accordingly to the model.
 * 
 * @author Femke Hoornveld
 * @version 1.0 (11-04-2016)
 */
public class RunController extends AbstractController implements ActionListener {
	private static final long serialVersionUID = -8776795932665582315L;
	private JButton stepOne;
	private JLabel labelSteps;
	private JTextField steps;
	private JButton startSteps;
	private JButton start;
	private JButton stop;
	
	/**
	 * The constructor of the class creates the buttons and puts them on the JPanel.
	 * 
	 * @param carPark The model belonging to this controller.
	 */
	public RunController(CarParkLogic carPark) {
		super(carPark);
		setSize(450, 50);
		stepOne = new JButton("Step +1");
		stepOne.addActionListener(this);
		labelSteps = new JLabel("Enter number of steps:");
		steps = new JTextField();
		startSteps = new JButton("Set steps");
		startSteps.addActionListener(this);
		start = new JButton("Start simulation");
		start.addActionListener(this);
		stop = new JButton("Stop simulation");
		stop.addActionListener(this);
		
		this.setLayout(null);
		add(stepOne);
		add(labelSteps);
		add(steps);
		add(startSteps);
		add(start);
		add(stop);
		
		stepOne.setBounds(40, 10, 100, 30);
		labelSteps.setBounds(40, 37, 150, 30);
		steps.setBounds(180, 44, 70, 20);
		startSteps.setBounds(259, 39, 100, 30);
		start.setBounds(405, 10, 130, 30);
		stop.setBounds(555, 10, 130, 30);

		setVisible(true);
	}
	
    /**
     * Set steps in the simulator
     *
     * @param steps Amount of steps we should do
     */
    private void setSteps(int steps) {
        CarParkLogic carPark = (CarParkLogic) model;

        for(int i = 0; i < steps; i++) {
            carPark.tick();
        }
    }

	@Override
	/**
	 * Making the buttons and text field of this controller react to input of the user.
	 * Saving the input to the model carPark.
	 */
	public void actionPerformed(ActionEvent e) {
		CarParkLogic carPark = (CarParkLogic) super.model;
		
		if (e.getSource() == stepOne) {
			try {
				carPark.tick();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return;
		}
		
		if (e.getSource() == startSteps) {
			try {
				int steps = parseSteps();				
				if (steps<1 || steps>1000) throw new SimException("Illegal number of steps");
				carPark.setSteps(steps);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return;
		}
		
		if (e.getSource() == start) {
			CarPark.run = true;
		}
		
		if (e.getSource() == stop) {
			CarPark.run = false;
		}
		
	}
	
	/**
	 * This method returns the input of the text field.
	 * @return Input of the text field
	 * @throws NumberFormatException
	 */
	private int parseSteps() throws NumberFormatException {
		int text = Integer.parseInt(steps.getText());
		steps.setText(""); // emptying the text field after storing the value
		return text;
	}
}
