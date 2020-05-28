package task.currencycalculator.userinterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * @author Anita Kaszuba
 */
/**
 * Klasa okna ramowego, tworzy panel.
 */
public class CalcFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public CalcFrame() {
		super("Kalkulator Walut");
		JPanel calcPanel = new CalcPanel();
 		add(calcPanel);
		
		setSize(280, 300);
		setLocation(300, 100);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
