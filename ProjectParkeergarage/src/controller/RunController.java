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
	private JTextField steps;
	private JButton startSteps;
	private JButton stopSteps;
	private boolean run;
	
	/**
	 * The constructor of the class creates the buttons and puts them on the JPanel.
	 * 
	 * @param life The model belonging to this controller.
	 */
	public RunController(CarParkLogic carPark) {
		super(carPark);
		setSize(450, 50);
		stepOne=new JButton("Step +1");
		stepOne.addActionListener(this);
		steps=new JTextField();
		startSteps=new JButton("Start");
		startSteps.addActionListener(this);
		stopSteps=new JButton("Stop");
		stopSteps.addActionListener(this);
		
		this.setLayout(null);
		add(stepOne);
		add(steps);
		add(startSteps);
		add(stopSteps);
		stepOne.setBounds(50, 10, 70, 30);
		steps.setBounds(140, 10, 70, 30);
		startSteps.setBounds(229, 10, 70, 30);
		stopSteps.setBounds(319, 10, 70, 30);

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
	 * Saving the input to the model life.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==stepOne) {
			try {
				model.tick();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return;
		}
		
		if (e.getSource()==startSteps) {
			try {
				int steps=parseSteps();				
				if (steps<1 || steps>1000) throw new SimException("Illegal number of steps");
				model.setSteps(steps);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return;
		}
		
		if (e.getSource()==stopSteps) {
			Life.run = false;
		}
		
	}
	
	/**
	 * This method returns the input of the text field.
	 * @return Input of the text field
	 * @throws NumberFormatException
	 */
	private int parseSteps() throws NumberFormatException {
		return Integer.parseInt(steps.getText());
	}
}
