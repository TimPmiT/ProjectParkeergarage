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

    private JLabel title;
    private JLabel carsEnterLabel;
    private JLabel carsPayLabel;
    private JLabel carsExitLabel;
    private JLabel carsMemberExitLabel;
    private JLabel carsInfoEnter;
    private JLabel carsInfoPay;
    private JLabel carsInfoExit;
    private JLabel carsInfoMembersExit;
    private JLabel copyright;
    
    private int numberOfEnteringCars;
    private int numberOfPayingCars;
    private int numberOfExitingCars;
    private int numberOfMembersExiting;

    /**
     * Constructor creates an instance of the StatView.
     * It stores its data to the given model.
     *
     * @param model The model that applies to this view
     */
    public StatView(CarParkLogic model) {
        super(model);

        title = new JLabel("Live car park information:");
        title.setFont(new Font("SansSerif", Font.BOLD, 14));
        
        carsEnterLabel= new JLabel("Cars waiting to enter:");
        carsPayLabel = new JLabel("Cars waiting to pay:");
        carsExitLabel = new JLabel("Cars (no members) waiting to exit:");
        carsMemberExitLabel = new JLabel ("Cars of members waiting to exit:");
        carsInfoEnter = new JLabel("");
        carsInfoPay = new JLabel("");
        carsInfoExit  = new JLabel("");
        carsInfoMembersExit = new JLabel ("");

        copyright = new JLabel("Yumthy © 2016");
        
		this.setLayout(null);
        add(title);
        add(carsEnterLabel);
        add(carsInfoEnter);
        add(carsPayLabel);
        add(carsInfoPay);
        add(carsExitLabel);
        add(carsInfoExit);
        add(carsMemberExitLabel);
        add(carsInfoMembersExit);
        add(copyright);
        
        title.setBounds(5, 0, 220, 20);
        carsEnterLabel.setBounds(25, 35, 220, 20);
        carsInfoEnter.setBounds(90, 49, 140, 20);
        carsPayLabel.setBounds(27, 70, 220, 20);
        carsInfoPay.setBounds(90, 84, 140, 20);
        carsExitLabel.setBounds(0, 105, 220, 20);
        carsInfoExit.setBounds(90, 118, 140, 20);
        carsMemberExitLabel.setBounds(2, 140, 250, 20);
        carsInfoMembersExit.setBounds(90, 153, 140, 20);
        copyright.setBounds(0, 308, 100, 20);
    }

    /**
     * Get's called by the super model class when something needs to be updated
     */
    public void updateView(){

        CarParkLogic carPark = (CarParkLogic) super.model;

        numberOfEnteringCars = carPark.getNumberOfEntering(); 
        numberOfPayingCars = carPark.getNumberOfPaying(); 
        numberOfExitingCars = carPark.getNumberOfExiting(); 
        numberOfMembersExiting = carPark.getNumberOfExitingMembers();

        carsInfoEnter.setText("" + numberOfEnteringCars);
        carsInfoPay.setText("" + numberOfPayingCars);
        carsInfoExit.setText("" + numberOfExitingCars);
        carsInfoMembersExit.setText("" + numberOfMembersExiting);

        setVisible(true);
        super.updateView();
    }
}