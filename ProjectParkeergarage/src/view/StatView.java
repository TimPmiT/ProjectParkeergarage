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
    private JLabel carsMemberExitLabel;
    private JLabel carsInfoEnter;
    private JLabel carsInfoPay;
    private JLabel carsInfoExit;
    private JLabel carsInfoMembersExit;
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

        this.size = new Dimension(100, 100);

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
        
        title.setBounds(5, 20, 220, 20);
        carsEnterLabel.setBounds(25, 55, 220, 20);
        carsInfoEnter.setBounds(90, 69, 140, 20);
        carsPayLabel.setBounds(27, 90, 220, 20);
        carsInfoPay.setBounds(90, 104, 140, 20);
        carsExitLabel.setBounds(0, 125, 220, 20);
        carsInfoExit.setBounds(90, 138, 140, 20);
        carsMemberExitLabel.setBounds(2, 160, 250, 20);
        carsInfoMembersExit.setBounds(90, 173, 140, 20);
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