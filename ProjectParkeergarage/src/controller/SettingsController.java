package controller;

import main.*;
import model.*;
import exception.*;

import java.awt.event.*;
import javax.swing.*;

/**
 * This class creates the labels, buttons, text fields needed to change the speed of cars entering the car park,
 * of car owners paying and of cars exiting the car park.
 * It catches the input the input is used on the model.
 * 
 * @author Femke Hoornveld
 * @version 1.0 (12-04-2016)
 */
public class SettingsController extends AbstractController implements ActionListener {
	private static final long serialVersionUID = -8776795932665582315L;
	private JLabel changeEnterSpeed;
	private JLabel changePaySpeed;
	private JLabel changeExitSpeed;
	
	private JTextField enterSpeed;
	private JTextField paySpeed;
	private JTextField exitSpeed;
	
	private JButton setChangeEnter;
	private JButton setChangePay;
	private JButton setChangeExit;
	
	/**
	 * The constructor of the class creates the buttons and puts them on the JPanel.
	 * 
	 * @param carPark The model belonging to this controller.
	 */
	public SettingsController(CarParkLogic carPark) {
		super(carPark);
		setSize(100, 300);
		changeEnterSpeed = new JLabel ("Change entering speed:");
		enterSpeed = new JTextField();
		setChangeEnter =new JButton("Set entering speed");
		setChangeEnter.addActionListener(this);
		changePaySpeed = new JLabel ("Change payment speed:");
		paySpeed = new JTextField();
		setChangePay =new JButton("Set payment speed");
		setChangePay.addActionListener(this);
		changeExitSpeed = new JLabel("Change exit speed:");
		exitSpeed = new JTextField();
		setChangeExit = new JButton("Set exiting speed");
		setChangeExit.addActionListener(this);
		
		this.setLayout(null);
		add(changeEnterSpeed);
		add(enterSpeed);
		add(setChangeEnter);
		add(changePaySpeed);
		add(paySpeed);
		add(setChangePay);
		add(changeExitSpeed);
		add(exitSpeed);
		add(setChangeExit);
		changeEnterSpeed.setBounds(5, 20, 150, 30);
		enterSpeed.setBounds(0, 45, 150, 30);
		setChangeEnter.setBounds(0, 75, 150, 30);
		changePaySpeed.setBounds(5, 105, 150, 30);
		paySpeed.setBounds(0, 130, 150, 30);
		setChangePay.setBounds(0, 160, 150, 30);
		changeExitSpeed.setBounds(5, 190, 150, 30);
		exitSpeed.setBounds(0, 215, 150, 30);
		setChangeExit.setBounds(0, 245, 150, 30);

		setVisible(true);
	}

	@Override
	/**
	 * Making the buttons and text field of this controller react to input of the user.
	 * Saving the input to the model carPark.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == setChangeEnter) {
			try {
				int speedEntering = parseSpeedEntering();	
				if (speedEntering < 0 || speedEntering > 20) throw new SimException("Illegal number of steps");
				model.setEnterSpeed(speedEntering);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return;
		}
		
		if (e.getSource() == setChangePay) {
			try {
				int speedPayment = parseSpeedPayment();	
				if (speedPayment < 0 || speedPayment > 20) throw new SimException("Illegal number of steps");
				model.setPaySpeed(speedPayment);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return;
		}
		
		if (e.getSource() == setChangeExit) {
			try {
				int speedExiting = parseSpeedExiting();				
				if (speedExiting < 0 || speedExiting > 20) throw new SimException("Illegal number of steps");
				model.setExitSpeed(speedExiting);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return;
		}
		
	}
	
	/**
	 * This method returns the input of the text field for changing the entering speed.
	 * @return Input of the text field
	 * @throws NumberFormatException
	 */
	private int parseSpeedEntering() throws NumberFormatException {
		int text = Integer.parseInt(enterSpeed.getText());
		enterSpeed.setText(""); // emptying the text field after storing the value
		return text;
	}
	
	/**
	 * This method returns the input of the text field for changing the payment speed.
	 * @return Input of the text field
	 * @throws NumberFormatException
	 */
	private int parseSpeedPayment() throws NumberFormatException {
		int text = Integer.parseInt(paySpeed.getText());
		paySpeed.setText(""); // emptying the text field after storing the value
		return text;
	}
	
	/**
	 * This method returns the input of the text field for changing the exiting speed.
	 * @return Input of the text field
	 * @throws NumberFormatException
	 */
	private int parseSpeedExiting() throws NumberFormatException {
		int text = Integer.parseInt(exitSpeed.getText());
		exitSpeed.setText(""); // emptying the text field after storing the value
		return text;
	}
}
