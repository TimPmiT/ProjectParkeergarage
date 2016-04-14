package controller;

import logic.*;

import java.awt.event.*;
import javax.swing.*;

/**
 * This class creates the button init, and the text field size and degree.
 * It catches the input the input is used on the model, and the view will be updated accordingly to the model.
 * 
 * @author Femke Hoornveld, Koen Gorter
 * @version 1.0 (11-04-2016)
 */
public class InitController extends AbstractController implements ActionListener {
	private static final long serialVersionUID = 8084081366423909672L;
	private JTextField size;
	private JTextField degree;
	private JButton init;
	
	
	public InitController(LifeLogic life) {
		super(life);
		setSize(90, 130);
		size=new JTextField();
		degree=new JTextField();
		init=new JButton("Create");
		init.addActionListener(this);
		
		this.setLayout(null);
		add(size);
		add(degree);
		add(init);
		size.setBounds(10, 10, 70, 30);
		degree.setBounds(10, 50, 70, 30);
		init.setBounds(10, 90, 70, 30);

		setVisible(true);
	}

	@Override
	/**
	 * Making the buttons and text field of this controller react to input of the user.
	 * Saving the input to the model life.
	 */
	public void actionPerformed(ActionEvent e) {
		try {
			int sizeField=parseSize();
			float degreeField=parseDegree();
			life.setSize(sizeField);
			life.setDegree(degreeField);
			life.randomInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * This method returns the input of the text field size.
	 * @return Input of the text field
	 * @throws NumberFormatException
	 */
	private int parseSize() throws NumberFormatException {
		return Integer.parseInt(size.getText());
	}
	
	/**
	 * This method returns the input of the text field degree.
	 * @return Input of the text field
	 * @throws NumberFormatException
	 */
	private float parseDegree() throws NumberFormatException {
		return Float.parseFloat(degree.getText());
	}
	

}
