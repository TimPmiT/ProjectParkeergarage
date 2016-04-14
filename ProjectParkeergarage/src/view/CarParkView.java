package view;

import model.*;

import javax.swing.*;
import java.awt.*;
/**
 * Class CarParkView is a visual representation of the car park with it's floors, rows and spots.
 *
 * @author Femke Hoornveld
 * @version 2.0 (11-04-2016)
 */
public class CarParkView extends AbstractView{

    private JLabel title;
    // private JLabel totalCars;
    // private JLabel totalPassholders;
    // private Image carParkImage;
    private Image carParkImage;
    private Dimension size;

    /**
     * Constructor creates an instance of the CarParkView.
     * It stores its data to the given model.
     *
     * @param model The model that applies to this view
     */
    public CarParkView(CarParkLogic model) {
        super(model);
        title = new JLabel("Representation of the car Park");
        title.setFont(new Font("SansSerif", Font.BOLD, 14));
        // this.totalCars = new JLabel("Cars");
        // this.totalPassholders = new JLabel("Pass holders");
        size = new Dimension(0, 0); // TODO veranderen?

        // Put the labels on a specific place on the jpanel.
        title.setBounds(5, 5, 150, 10);
        // totalPassholders.setBounds(500,5,250,10);
        // totalCars.setBounds(270,5,150,10);


        // Add the labels to the view.
        add(title);
        // add(totalCars);
        // add(totalPassholders);
    }

    @Override
    /**
     * Redisplay the car park as opposed to painting it over the last one.
     */
    public void paintComponent(Graphics g) {
        if (carParkImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(carParkImage, 0, 0, null);
        }
        else {
            // Rescale the previous image.
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }

    /**
     * Notify the model that applies to the view that the view should be updated.
     */
    public void updateView() {

        CarParkLogic carPark = (CarParkLogic) super.model;

        // totalCars.setText(Language.get("cars")+ carPark.getTotalCarIndex());
        // totalPassholders.setText(Language.get("pass")+carPark.getTotalPassholderIndex());
        
        // Create a new car park image if the size has changed.
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }

        Graphics graphics = carParkImage.getGraphics();


        for (int floor = 0; floor < carPark.getNumberOfFloors(); floor++) {
            for (int row = 0; row < carPark.getNumberOfRows(); row++) {
                for (int place = 0; place < carPark.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = carPark.getCarAt(location);
                    Color color;

                    if (car == null) {
                    	color = Color.WHITE;
                    } else if (car instanceof ParkingPassHolder) {
                        color = Color.GREEN;
                    } else {
                        color = Color.RED;
                    }

                    drawPlace(graphics, location, color);
                }
            }
        }

        setVisible(true);
        super.updateView();
    }

    /**
     * Paint a parking spot on the screen in a given color.
     */
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                (location.getFloor() * 260 + (1 + (int) Math.floor(location.getRow() * 0.5)) * 60 + (location.getRow() % 2) * 20) -59,
                location.getPlace() * 10 + 30,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }
}
