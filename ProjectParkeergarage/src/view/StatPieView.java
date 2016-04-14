package view;

import model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Class StatPieView is a legend for the PieView.
 *
 * @author Femke Hoornveld
 * @version 1.0 (13-04-2016)
 */

public class StatPieView extends AbstractView {
    private JLabel title;
    private JLabel legendRed;
    private JLabel amountOfCars;
    private JLabel legendGreen;
    private JLabel amountOfPassHolders;
    private JLabel legendBlue;
    private JLabel amountOfReservations;
    private JLabel legendGray;
    private JLabel amountOfTotal;

	private int amountCars;
	private int amountPassHolders;
	private int amountReservations;
	private int amountTotal;

    /**
     * Constructor creates an instance of the StatPieView.
     * It stores its data to the given model.
     *
     * @param model The model that applies to this view
     */
    public StatPieView(CarParkLogic model) {
        super(model);
        
        // wingding vierkantje: E04D // TODO
        // String vierkantje = "E04D";?? // ook nog boven in fields zetten
       
        title = new JLabel("Pie graph of pass holders vs. no pass holders");
        title.setFont(new Font("SansSerif", Font.BOLD, 14));
        legendRed = new JLabel("RED     Normal cars:");
        amountOfCars = new JLabel("");
        legendGreen = new JLabel("GREEN Pass holders:");
        amountOfPassHolders = new JLabel("");
        legendBlue = new JLabel("BLUE    Reservations:");
        amountOfReservations = new JLabel("");
        legendGray = new JLabel("GRAY   Total:");
        amountOfTotal = new JLabel("");

		this.setLayout(null);
        add(title);
        add(legendRed);
        add(amountOfCars);
        add(legendGreen);
        add(amountOfPassHolders);
        add(legendBlue);
        add(amountOfReservations);
        add(legendGray);
        add(amountOfTotal);
        
        title.setBounds(5, 5, 400, 20);
        legendRed.setBounds(5, 35, 130, 20);
        amountOfCars.setBounds(140, 35, 50, 20);
        legendGreen.setBounds(5, 55, 130, 20);
        amountOfPassHolders.setBounds(140, 55, 50, 20);
        legendBlue.setBounds(5, 75, 130, 20);
        amountOfReservations.setBounds(140, 75, 50, 20);
        legendGray.setBounds(5, 95, 130, 20);
        amountOfTotal.setBounds(140, 95, 100, 20);
    }
    
    /**
     * Get's called by the super model class when something needs to be updated
     */
    public void updateView(){

        CarParkLogic carPark = (CarParkLogic) super.model;

    	amountCars = carPark.getTotalCars();
    	amountPassHolders = carPark.getTotalPassHolders();
    	amountReservations = carPark.getTotalReservations();
    	amountTotal = amountCars + amountPassHolders + amountReservations;

        amountOfCars.setText("" + amountCars);
        amountOfPassHolders.setText("" + amountPassHolders);
        amountOfReservations.setText("" + amountReservations);
        amountOfTotal.setText("" + amountTotal + "/540");

        setVisible(true);
        super.updateView();
    }
}
