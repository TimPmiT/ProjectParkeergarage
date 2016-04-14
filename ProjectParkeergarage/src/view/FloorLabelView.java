package view;

import model.*;

import javax.swing.*;
import java.awt.*;
/**
 * Class CarParkView is a visual representation of the car park with it's floors, rows and spots.
 *
 * @author Femke Hoornveld
 * @version 1.0 (13-04-2016)
 */
public class FloorLabelView extends AbstractView {
    private JLabel floorOne;
    private JLabel floorTwo;
    private JLabel floorThree;
    
    /**
     * Constructor creates an instance of the floorLabelView.
     * It stores its data to the given model.
     *
     * @param model The model that applies to this view
     */
    public FloorLabelView(CarParkLogic model) {
        super(model);
        
        floorOne = new JLabel("Floor 1");
        floorOne.setFont(new Font("SansSerif", Font.BOLD, 14));
        floorTwo = new JLabel("Floor 2");
        floorTwo.setFont(new Font("SansSerif", Font.BOLD, 14));
        floorThree = new JLabel ("Floor 3");
        floorThree.setFont(new Font("SansSerif", Font.BOLD, 14));
        
        this.setLayout(null);
        floorOne.setBounds(110, 0, 60, 20);
        floorTwo.setBounds(365, 0, 60, 20);
        floorThree.setBounds(625, 0, 60, 20);; 
        
        add(floorOne);
        add(floorTwo);
        add(floorThree);
    }
}
