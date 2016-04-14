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

        this.size = new Dimension(100, 100);

    	gradenHoekStart = 0;
    	x = 50;
    	y = 0;
        
    }

    // TODO Volgens mij moet dit in de methode updateview maar dan heeft ie geen
    // g graphics meer om op te tekenen, sooooo, what to do
    public void paint(Graphics g) {
    	// Background of pie graph
    	//g.setColor(Color.WHITE);
    	//g.fillOval(x, y, WIDTH_ARC, HEIGHT_ARC);
    	
		// Slice 1 \\
		// methode van maken TODO 
		int procent = (amountCars * PERCENT) / MAX_CARS;
		int gradenHoek2 = ((procent * MAX_DEGREES) / PERCENT);
		g.setColor(Color.RED);
		g.fillArc(x, y, WIDTH_ARC, HEIGHT_ARC, gradenHoekStart, gradenHoek2);
		
		// Slice 2 \\
		int procent2 = ((amountPassHolders * PERCENT) / MAX_CARS);
		int gradenHoek3 = ((procent2 * MAX_DEGREES) / PERCENT);
		int start = gradenHoekStart + gradenHoek2;
		g.setColor(Color.GREEN);
		g.fillArc(x, y, WIDTH_ARC, HEIGHT_ARC, start, gradenHoek3);
		
    	/*
		// Legenda vierkantjes \\
		int xRect = 50;
		int yRect = 250;
		g.setColor(Color.RED);
		g.fillRect(xRect, yRect, 10, 10);
		
		int xRect2 = 50;
		int yRect2 = 270;
		g.setColor(Color.GREEN);
		g.fillRect(xRect2, yRect2, 10, 10);
		*/
		
		
    }
    
    /**
     * Get's called by the super model class when something needs to be updated
     */
    public void updateView(){

        CarParkLogic carPark = (CarParkLogic) super.model;

    	amountCars = carPark.getTotalCars();
    	amountPassHolders = carPark.getTotalPassHolders();

        setVisible(true);
        super.updateView();
    }
}