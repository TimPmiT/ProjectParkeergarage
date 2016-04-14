package view;

import model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Class StatView is a representation in numbers of the amount of cars entering, paying and leaving.
 *
 * @author Femke Hoornveld
 * @version 1.0 (12-04-2016)
 */

public class StatView extends AbstractView {

    private Dimension size;

    private JLabel title;
    private JLabel carsEnterLabel;
    private JLabel carsPayLabel;
    private JLabel carsExitLabel;
    private JLabel carsInfoEnter;
    private JLabel carsInfoPay;
    private JLabel carsInfoExit;
    private int numberOfEnteringCars;
    private int numberOfPayingCars;
    private int numberOfExitingCars;

    /**
     * Constructor creates an instance of the StatView.
     * It stores its data to the given model.
     *
     * @param model The model that applies to this view
     */
    public StatView(CarParkLogic model) {
        super(model);

        this.size = new Dimension(100, 100);

        title = new JLabel("Live car park information:");
        title.setFont(new Font("SansSerif", Font.BOLD, 14));
        
        carsEnterLabel= new JLabel("Number of cars waiting to enter:");
        carsPayLabel = new JLabel("Number of cars waiting to pay:");
        carsExitLabel = new JLabel("Number of cars waiting to exit:");
        carsInfoEnter = new JLabel("");
        carsInfoPay = new JLabel("");
        carsInfoExit  = new JLabel("");

		this.setLayout(null);
        add(title);
        add(carsEnterLabel);
        add(carsInfoEnter);
        add(carsPayLabel);
        add(carsInfoPay);
        add(carsExitLabel);
        add(carsInfoExit);
        
        title.setBounds(0, 5, 220, 20);
        carsEnterLabel.setBounds(0, 55, 220, 20);
        carsInfoEnter.setBounds(80, 70, 140, 20);
        carsPayLabel.setBounds(0, 100, 220, 20);
        carsInfoPay.setBounds(80, 115, 140, 20);
        carsExitLabel.setBounds(0, 145, 220, 20);
        carsInfoExit.setBounds(80, 160, 140, 20);
    }

    /**
     * Get's called by the super model class when something needs to be updated
     */
    public void updateView(){

        CarParkLogic carPark = (CarParkLogic) super.model;

        numberOfEnteringCars = carPark.getNumberOfEntering(); 
        numberOfPayingCars = carPark.getNumberOfPaying(); 
        numberOfExitingCars = carPark.getNumberOfExiting(); 

        carsInfoEnter.setText("" + numberOfEnteringCars);
        carsInfoPay.setText("" + numberOfPayingCars);
        carsInfoExit.setText("" + numberOfExitingCars);

        setVisible(true);
        super.updateView();
    }
}