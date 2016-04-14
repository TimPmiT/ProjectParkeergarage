package view;

import javax.swing.*;
import logic.*;

public abstract class AbstractView extends JPanel {
	private static final long serialVersionUID = 6437976554496769048L;
	protected LifeLogic life;

	public AbstractView(LifeLogic life) {
		this.life=life;
		life.addView(this);
	}
	
	public LifeLogic getModel() {
		return life;
	}
	
	public void updateView() {
		repaint();
	}
}
