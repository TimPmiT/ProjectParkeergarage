package controller;

import model.*;
import exception.*;

import java.awt.event.*;
import javax.swing.*;

/**
 * This class creates the labels, buttons, text fields needed to change the speed of cars entering the car park,
 * of car owners paying and of cars exiting the car park. It also creates labels, buttons, text fields needed
 * to change the number of parking pass holders and reservations.
 * It catches the input the input is used on the model.
 * 
 * @author Femke Hoornveld
 * @version 2.0 (14-04-2016)
 */
public class SettingsController extends AbstractController implements ActionListener {
	private static final long serialVersionUID = -8776795932665582315L;
	private JLabel changeEnterSpeed;
	private JLabel changePaySpeed;
	private JLabel changeExitSpeed;
	private JLabel changeNumberPass;
	private JLabel changeNumberRes;
	
	private JTextField enterSpeed;
	private JTextField paySpeed;
	private JTextField exitSpeed;
	private JTextField numberOfPass;
	private JTextField numberOfRes;
	
	private JButton setChangeEnter;
	private JButton setChangePay;
	private JButton setChangeExit;
	private JButton setNumberPass;
	private JButton setNumberRes;
	
	/**
	 * The constructor of the class creates the buttons and puts them on the JPanel.
	 * 
	 * @param carPark The model belonging to this controller.
	 */
	public SettingsController(CarParkLogic carPark) {
		super(carPark);
		setSize(100, 300);
		changeEnterSpeed = new JLabel("Change entering speed:");
		enterSpeed = new JTextField();
		setChangeEnter =new JButton("Set entering speed");
		setChangeEnter.addActionListener(this);
		changePaySpeed = new JLabel("Change payment speed:");
		paySpeed = new JTextField();
		setChangePay =new JButton("Set payment speed");
		setChangePay.addActionListener(this);
		changeExitSpeed = new JLabel("Change exit speed:");
		exitSpeed = new JTextField();
		setChangeExit = new JButton("Set exiting speed");
		setChangeExit.addActionListener(this);
		changeNumberPass = new JLabel("Change number of pass holders:");
		numberOfPass = new JTextField();
		setNumberPass = new JButton("Set new number");
		setNumberPass.addActionListener(this);
		changeNumberRes = new JLabel("Change number of reservations:");
		numberOfRes = new JTextField();
		setNumberRes = new JButton("Set new number");
		setNumberRes.addActionListener(this);
		
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
		add(changeNumberPass);
		add(numberOfPass);
		add(setNumberPass);
		add(changeNumberRes);
		add(numberOfRes);
		add(setNumberRes);
		
		changeEnterSpeed.setBounds(5, 20, 150, 30);
		enterSpeed.setBounds(0, 43, 150, 30);
		setChangeEnter.setBounds(0, 71, 150, 30);
		changePaySpeed.setBounds(5, 98, 150, 30);
		paySpeed.setBounds(0, 121, 150, 30);
		setChangePay.setBounds(0, 149, 150, 30);
		changeExitSpeed.setBounds(5, 176, 150, 30);
		exitSpeed.setBounds(0, 199, 150, 30);
		setChangeExit.setBounds(0, 227, 150, 30);
		changeNumberPass.setBounds(5, 254, 220, 30);
		numberOfPass.setBounds(0, 277, 150, 30);
		setNumberPass.setBounds(0, 304, 150, 30);
		changeNumberRes.setBounds(5, 332, 220, 30);
		numberOfRes.setBounds(0, 355, 150, 30);
		setNumberRes.setBounds(0, 381, 150, 30);
		
		setVisible(true);
	}

	@Override
	/**
	 * Making the buttons and text field of this controller react to input of the user.
	 * Saving the input to the model carPark.
	 */
	public void actionPerformed(ActionEvent e) {
		
		CarParkLogic carPark = (CarParkLogic) super.model;
		
		if (e.getSource() == setChangeEnter) {
			try {
				int speedEntering = parseSpeedEntering();	
				if (speedEntering < 0 || speedEntering > 20) throw new SimException("Illegal number of steps");
				carPark.setEnterSpeed(speedEntering);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return;
		}
		
		if (e.getSource() == setChangePay) {
			try {
				int speedPayment = parseSpeedPayment();	
				if (speedPayment < 0 || speedPayment > 20) throw new SimException("Illegal number of steps");
				carPark.setPaySpeed(speedPayment);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return;
		}
		
		if (e.getSource() == setChangeExit) {
			try {
				int speedExiting = parseSpeedExiting();				
				if (speedExiting < 0 || speedExiting > 20) throw new SimException("Illegal number of steps");
				carPark.setExitSpeed(speedExiting);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return;
		}
		
		if (e.getSource() == setNumberPass) {
			try {
				int newNumberOfPass = parseNumberPass();				
				if (newNumberOfPass < 0 || newNumberOfPass > 541) throw new SimException("Illegal number of steps");
				carPark.setNumberOfPassHolders(newNumberOfPass);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return;
		}
		
		if (e.getSource() == setNumberRes) {
			try {
				int newNumberOfRes = parseNumberRes();				
				if (newNumberOfRes < 0 || newNumberOfRes > 541) throw new SimException("Illegal number of steps");
				carPark.setNumberOfReservations(newNumberOfRes);
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
	
	/**
	 * This method returns the input of the text field for changing the number of pass holders.
	 * @return Input of the text field
	 * @throws NumberFormatException
	 */
	private int parseNumberPass() throws NumberFormatException {
		int text = Integer.parseInt(numberOfPass.getText());
		numberOfPass.setText(""); // emptying the text field after storing the value
		return text;
	}
	
	/**
	 * This method returns the input of the text field for changing the number of reservations.
	 * @return Input of the text field
	 * @throws NumberFormatException
	 */
	private int parseNumberRes() throws NumberFormatException {
		int text = Integer.parseInt(numberOfRes.getText());
		numberOfRes.setText(""); // emptying the text field after storing the value
		return text;
	}
}
