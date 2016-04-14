package logic;

import exception.*;
import java.util.*;

/**
 * This class 
 * 
 * @author Femke Hoornveld, Koen Gorter
 * @version 1.0 (11-04-2016)
 */
public class LifeLogic extends AbstractModel implements Runnable {
	private int size;
	private static final int MIN_SIZE=10;
	private static final int MAX_SIZE=50;
	private boolean sizeIsSet;
	
	private float degree;
	private static final float MIN_DEGREE=0.0f;
	private static final float MAX_DEGREE=1.0f;
	private boolean degreeIsSet;
	
	private boolean initRun;
	
	private int[][] fieldOriginal;
	private int[][] fieldUnderConstruction;
	private Random r;
	
	private int numOfSteps;
	private boolean run;
	
	/**
	 * The constructor initializes the fields of this model.
	 */
	public LifeLogic() {
		size=MIN_SIZE-1;
		degree=MIN_DEGREE-1;
		sizeIsSet=false;
		degreeIsSet=false;
		r=new Random();
		run=false;
	}
	
	/**
	 * This method reacts to the size set by the user. If the size is out of bounds with the set max and min,
	 * the model will not use the input and throw an exception.
	 * 
	 * @param size The size for the model
	 * @throws LifeException
	 */
	public void setSize(int size) throws LifeException {
		if (size<MIN_SIZE) {
			throw new LifeException("Size too small");
		}
		if (size>MAX_SIZE) {
			throw new LifeException("Size too large");
		}
		this.size=size;
		sizeIsSet=true;
		fieldOriginal=new int[size][size];
		fieldUnderConstruction=new int[size][size];
		initRun=false;
	}
	
	/**
	 * This method reacts to the degrees set by the user. If the size is out of bounds with the set max and min,
	 * the model will not use the input and throw an exception.
	 * 
	 * @param degree The degree is the amount of degrees that are used to draw an arc, a part of the pie graph.
	 * @throws LifeException
	 */
	public void setDegree(float degree) throws LifeException {
		if (degree<MIN_DEGREE) 
			throw new LifeException("Degree too small");
		if (degree>MAX_DEGREE)
			throw new LifeException("Degree too large");
		this.degree=degree;
		degreeIsSet=true;
		initRun=false;
	}
	
	/**
	 * This method executes one step in the simulator. It updates the pi graph and updates the views. 
	 * The method throws exceptions if the size and the degrees
	 * are not set and when the button run has not yet been clicked.
	 * 
	 * @throws LifeException
	 */
	public void doStep() throws LifeException {
		if (!sizeIsSet || !degreeIsSet)
			throw new LifeException("Size and/or degree is not set yet");
		if (!initRun)
			throw new LifeException("Run init first");
		calculateRound();
		notifyViews();
	}
	
	/**
	 * This method is a case of overloading.
	 * This method executes the amount of steps given as parameter. It updates the pi graph and updates the views. 
	 * The method throws exceptions if the size and the degrees
	 * are not set and when the button run has not yet been clicked.
	 * 
	 * @param numOfSteps Number of steps that need to be executed
	 * @throws LifeException
	 */
	public void doSteps(int numOfSteps) throws LifeException {
		if (!sizeIsSet || !degreeIsSet)
			throw new LifeException("Size and/or degree is not set yet");
		if (!initRun)
			throw new LifeException("Run init first");
		this.numOfSteps=numOfSteps;
		run=true;
		new Thread(this).start();
	}
	
	/**
	 * This stops the program by setting the run variable to false.
	 */
	public void stopSteps() {
		run=false;
	}
	
	/**
	 * @return fieldOriginal The array of the layout of the field
	 */
	public int[][] getState() {
		return fieldOriginal;
	}
	
	/**
	 * Firstly it checks if the variables size and degree are set. When these are not set the method throws a exception.
	 * 
	 * 
	 * 
	 * @throws LifeException 
	 */
	public void randomInit() throws LifeException {
		if (!sizeIsSet || !degreeIsSet)
			throw new LifeException("Size and/or degree is not set yet");
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				if (r.nextFloat()<=degree) fieldOriginal[i][j]=1; else fieldOriginal[i][j]=0;
		initRun=true;
		notifyViews();
	}
	
	/**
	 * 
	 */
	private void calculateRound() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				int count=0;
				for(int k=Math.max(0, i-1);k<=Math.min(size-1, i+1);k++) {
					for(int l=Math.max(0, j-1);l<=Math.min(size-1, j+1);l++) {
						count+=fieldOriginal[k][l];
					}
				}
				System.out.println(count);
				if (fieldOriginal[i][j]==1) count--;
				if (count==2 || count==3) fieldUnderConstruction[i][j]=1; else fieldUnderConstruction[i][j]=0;
			}
		}
		int[][] temp=fieldOriginal;
		fieldOriginal=fieldUnderConstruction;
		fieldUnderConstruction=temp;
	}

	@Override
	/**
	 * (dit is start() of zoiets zei Harald?
	 */
	public void run() {
		for(int i=0;i<numOfSteps && run;i++) {
			calculateRound();
			notifyViews();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		run=false;
	}

	
}
