package view;

import model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Class PieView is a visual representation of the number of cars and cars with pass holders.
 *
 * @author Femke Hoornveld
 * @version 1.0 (12-04-2016)
 */

public class PieView extends AbstractView {

    private Dimension size;
    
	private int gradenHoekStart;
	private int x;
	private int y;
	private int amountCars;
	private int amountPassHolders;
	private Image pieViewImage;
	private static final int WIDTH_ARC = 200;
	private static final int HEIGHT_ARC = 200;
	private static final int MAX_DEGREES = 360;
	private static final int MAX_CARS = 540;
	private static final int PERCENT = 100;

    /**
     * Constructor creates an instance of the PieView.
     * It stores its data to the given model.
     *
     * @param model The model that applies to this view
     */
    public PieView(CarParkLogic model) {
        super(model);

        this.size = new Dimension(0, 0);

    	gradenHoekStart = 0;
    	x = 50;
    	y = 0;
        
    }

    // TODO Volgens mij moet dit in de methode updateview maar dan heeft ie geen
    // g graphics meer om op te tekenen, sooooo, what to do
    public void paintComponent(Graphics g) {
    	if (pieViewImage == null) {
    		return;
    	}
    	
    	Dimension currentSize = getSize();
    	if(size.equals(currentSize)) {
    		g.drawImage(pieViewImage, 0, 0, null);
    	} else {
    		g.drawImage(pieViewImage,  0, 0 , currentSize.width, currentSize.height, null);
    	}
    }
    
    /**
     * Get's called by the super model class when something needs to be updated.
     */
    public void updateView() {
    	CarParkLogic carPark = (CarParkLogic) super.model;
   		amountCars = carPark.getTotalCars();
   		amountPassHolders = carPark.getTotalPassHolders();
   		
    	if (!size.equals(getSize())) {
    		size = getSize();
    		pieViewImage = createImage(size.width, size.height);
    	}
    	
    	Graphics graphics = pieViewImage.getGraphics();
    	
    	// Background of pie graph
    	graphics.setColor(Color.WHITE);
    	graphics.fillRect(x, y, WIDTH_ARC+10, HEIGHT_ARC+10);
    	graphics.setColor(Color.LIGHT_GRAY);
    	graphics.fillOval(x+5, y+5, WIDTH_ARC, HEIGHT_ARC);
    	
    	
		// Slice 1 \\
		// methode van maken TODO 
		int procent = (amountCars * PERCENT) / MAX_CARS;
		int gradenHoek2 = ((procent * MAX_DEGREES) / PERCENT);
		graphics.setColor(Color.RED);
		graphics.fillArc(x+5, y+5, WIDTH_ARC, HEIGHT_ARC, gradenHoekStart, gradenHoek2);
		
		// Slice 2 \\
		int procent2 = ((amountPassHolders * PERCENT) / MAX_CARS);
		int gradenHoek3 = ((procent2 * MAX_DEGREES) / PERCENT);
		int start = gradenHoekStart + gradenHoek2;
		graphics.setColor(Color.GREEN);
		graphics.fillArc(x+5, y+5, WIDTH_ARC, HEIGHT_ARC, start, gradenHoek3);
    	
		setVisible(true);
	    super.updateView();
    }
    
}