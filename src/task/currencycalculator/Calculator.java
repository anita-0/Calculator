package task.currencycalculator;

import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JOptionPane;
import task.currencycalculator.userinterface.CalcFrame;
/**
 * @author Anita Kaszuba
 */
/**
 * Klasa g��wna, wywo�uje okno ramowe oraz parser xml.
 * Posiada metod� obliczaj�c� warto�� wyj�ciow�.
 */
public class Calculator {
	
	public static void main(String[] args) {
 		EventQueue.invokeLater(new Runnable() {
 			@Override
 			public void run() {
 				try {
 					new DOM();
 				} catch (IOException e1) {
 					JOptionPane.showMessageDialog (null, "B��d odczytu pliku xml ", "B��d", JOptionPane.ERROR_MESSAGE);
 					e1.printStackTrace();
 				}
 				new CalcFrame();
 			}
 		});
 	}
	
	/**
	 * @return Warto�� po przeliczeniu na wskazan� walut�
	 */
	public static double calculate(double inValue, String rate) {
		double outValue;
		double doubleRate = 0;
		try {
			doubleRate = Double.parseDouble(rate);
		} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog (null, "Nie uda�o si� wykona� operacji - niepoprawna warto�� w pliku xml ", "B��d", JOptionPane.ERROR_MESSAGE);
		} 
		outValue =  inValue*doubleRate;
		return outValue;
	}
}
