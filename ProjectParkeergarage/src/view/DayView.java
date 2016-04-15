package view;

import model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Class StatView is a representation in numbers of the amount of cars entering, paying and leaving.
 *
 * @author Koen Gorter
 * @version 1.0 (14-04-2016)
 */

public class DayView extends AbstractView {

    // private Dimension size;

    private JLabel title;
    private JLabel dayInfoLabel;
    private String currentDay;

    /**
     * Constructor creates an instance of the StatView.
     * It stores its data to the given model.
     *
     * @param model The model that applies to this view
     */
    public DayView(CarParkLogic model) {
        super(model);

       // this.size = new Dimension(100, 50);

        title = new JLabel("The day of the week is:");
        title.setFont(new Font("SansSerif", Font.BOLD, 14));
        dayInfoLabel= new JLabel("");

		this.setLayout(null);
        add(title);
        add(dayInfoLabel);
        
        title.setBounds(5, 0, 220, 20);
        dayInfoLabel.setBounds(55, 20, 100, 20);
    }

    /**
     * Get's called by the super model class when something needs to be updated
     */
    public void updateView(){
        CarParkLogic carPark = (CarParkLogic) super.model;

        currentDay = carPark.getCurrentDay(); 

        dayInfoLabel.setText(currentDay);

        setVisible(true);
        super.updateView();
    }
}