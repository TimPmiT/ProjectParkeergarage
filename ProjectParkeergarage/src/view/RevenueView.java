package view;

import model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Class RevenueView represents the amount of revenue generated by different sources. 
 *
 * @author Thomas Lambregts
 * @version 1.0 (12-04-2016)
 */

public class RevenueView extends AbstractView {

    private Dimension size;

    private JLabel title;
    private JLabel normalCarRevenue;
    private JLabel memberCarRevenue;
    private JLabel combinedTotalRevenue;
    
    private JLabel timeLabel;
    private JLabel elapsedTime; 
    
    private String numberOfNormalCars;
    private String numberOfMemberCars;
    private String numberOfCombinedRevenue; 
    private String time; 
    

    /**
     * Constructor creates an instance of the RevenueView.
     * It stores its data to the given model.
     *
     * @param model The model that applies to this view
     */
    public RevenueView(CarParkLogic model) {
        super(model);

        this.size = new Dimension(100, 100);

        title = new JLabel("Live revenue information:");
        title.setFont(new Font("SansSerif", Font.BOLD, 14));
        
        normalCarRevenue = new JLabel("");
        memberCarRevenue = new JLabel("");
        combinedTotalRevenue = new JLabel("");
        
        timeLabel = new JLabel("Time:");
        timeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        elapsedTime = new JLabel("");
       
		this.setLayout(null);
        add(title);
        add(normalCarRevenue);
        add(memberCarRevenue);
        add(combinedTotalRevenue);
        add(timeLabel);
        add(elapsedTime);
        
        title.setBounds(0, 5, 220, 20);
        normalCarRevenue.setBounds(0, 45, 400, 20);
        memberCarRevenue.setBounds(0, 65, 400, 20);
        combinedTotalRevenue.setBounds(0, 85, 400, 20);
        
        timeLabel.setBounds(0, 130, 100, 20);
        elapsedTime.setBounds(0, 150, 400, 20); 
    }

    /**
     * Get's called by the super model class when something needs to be updated
     */
    public void updateView(){

        CarParkLogic carPark = (CarParkLogic) super.model;

        numberOfNormalCars = carPark.getRevenueNormal(); 
        numberOfMemberCars = carPark.getRevenueMembers(); 
        numberOfCombinedRevenue = carPark.getRevenueTotal();
        time = carPark.getTime();

        normalCarRevenue.setText("Revenue from normal customers: " + "�" +  numberOfNormalCars);
        memberCarRevenue.setText("Revenue from members: " + "�" + numberOfMemberCars);
        combinedTotalRevenue.setText("Total revenue: " + "�" + numberOfCombinedRevenue);
        elapsedTime.setText(time);

        setVisible(true);
        super.updateView();
    }
}