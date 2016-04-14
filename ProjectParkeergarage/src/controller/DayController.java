package controller;

import main.*;
import model.*;
import exception.*;

import java.awt.event.*;
import javax.swing.*;

/**
 * This class is used to control the day in wich the simulation takes place.
 * But is just a dummy for now.
 * 
 * @author Koen Gorter
 * @version 1.0 (14-04-2016)
 */
public class DayController extends AbstractController implements ActionListener {
	private JLabel labelDay;
	private JTextField day;
	private JButton setDay;
	
	/**
	 * The constructor of the class creates the buttons and puts them on the JPanel.
	 * 
	 * @param carPark The model belonging to this controller.
	 */
	public DayController(CarParkLogic carPark) {
		super(carPark);
		setSize(200, 150);
		labelDay = new JLabel("Enter Day:");
		day = new JTextField();
		setDay = new JButton("Set day");
		setDay.addActionListener(this);
		
		//adding to layout
		this.setLayout(null);
		add(labelDay);
		add(day);
		add(setDay);
		
		//setting the size and bounds
		labelDay.setBounds(55, 0, 200, 20);
		day.setBounds(10, 30, 150, 20);
		setDay.setBounds(45, 60, 80, 20);
		
		setVisible(true);
	}
	
	@Override
	/**
	 * 
	 */
	public void actionPerformed(ActionEvent e){
		CarParkLogic carPark = (CarParkLogic) super.model;
		
		if(e.getSource() == setDay){
			carPark.setCurrentDay(day.getText());
		}
	}
}