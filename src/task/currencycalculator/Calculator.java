package task.currencycalculator;

import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JOptionPane;
import task.currencycalculator.userinterface.CalcFrame;
/**
 * @author Anita Kaszuba
 */
/**
 * Klasa g³ówna, wywo³uje okno ramowe oraz parser xml.
 * Posiada metodê obliczaj¹c¹ wartoœæ wyjœciow¹.
 */
public class Calculator {
	
	public static void main(String[] args) {
 		EventQueue.invokeLater(new Runnable() {
 			@Override
 			public void run() {
 				try {
 					new DOM();
 				} catch (IOException e1) {
 					JOptionPane.showMessageDialog (null, "B³¹d odczytu pliku xml ", "B³¹d", JOptionPane.ERROR_MESSAGE);
 					e1.printStackTrace();
 				}
 				new CalcFrame();
 			}
 		});
 	}
	
	/**
	 * @return Wartoœæ po przeliczeniu na wskazan¹ walutê
	 */
	public static double calculate(double inValue, String rate) {
		double outValue;
		double doubleRate = 0;
		try {
			doubleRate = Double.parseDouble(rate);
		} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog (null, "Nie uda³o siê wykonaæ operacji - niepoprawna wartoœæ w pliku xml ", "B³¹d", JOptionPane.ERROR_MESSAGE);
		} 
		outValue =  inValue*doubleRate;
		return outValue;
	}
}
