package task.currencycalculator.userinterface;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import task.currencycalculator.Calculator;
import task.currencycalculator.DOM;
/**
 * @author Anita Kaszuba
 */
/**
 * Klasa panelu, tworzy i obs³uguje interfejs u¿ytkownika.
 */
public class CalcPanel extends JPanel implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	public static final int HEIGHT = 300;	/**!< Wysokoœæ panelu */
 	public static final int WIDTH = 280;	/**!< Szerokoœæ panelu */
 	
 	private JButton startButton;		/**!< Przycisk startuje operacjê liczenia */
 	private JTextField inValueField;	/**!< Pole tekstowe na wartoœæ podan¹ przez u¿ytkownika */
 	private JTextField outValueField;	/**!< Pole tekstowe na wyliczon¹ wartoœæ wyjœciow¹ */
 	private JLabel eurLabel;			/**!< wyœwietlenie "EUR" */
 	private JLabel instructionLabel1;	/**!< wyœwietlenie informacji dla u¿ytkownika */
 	private JLabel instructionLabel2;	/**!< wyœwietlenie informacji dla u¿ytkownika */
 	private JComboBox currencies;		/**!< Rozwijana lista wyboru docelowej waluty */
 	String[][] xmlData = DOM.getXmlData();	/**!< Pobranie tablicy z danymi z pliku xml */
 	private int activeRate = 0;			/**!< Okreœla wybrane pole waluty  */

 	public CalcPanel() {
 		
 		setLayout(null);
 		setPreferredSize(new Dimension(WIDTH, HEIGHT));
 		createComponents();
 	}
 	/**
 	 * Funkcja obs³uguje akcje np. klikniêcia.
 	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == startButton) {	/**!< Po klikniêciu przycisku wyliczana i wyœwietlana jest wartoœæ wyjœciowa */
			double inValue;			/**!< Wartoœæ podana przez u¿ytkownika */
			try {
				inValue = Double.parseDouble(inValueField.getText());
				if(inValue != 0.0) {
					NumberFormat f = NumberFormat.getInstance();
					f.setGroupingUsed(false);
					double outValueDouble = Calculator.calculate(inValue, xmlData[1][activeRate]);	/**!< Wywo³anie funkcji obliczaj¹cej wartoœæ wyjœciow¹ */
					if(outValueDouble > 1000000000.0) {
						outValueField.setText("");
						JOptionPane.showMessageDialog (null, "To bardzo du¿a wartoœæ, nie uda³o siê jej wyœwietliæ \n Proszê podaj mniejsz¹ kwotê", "Przekroczenie wartoœci wyjœciowej", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						String outValue = f.format(outValueDouble);
						int n;
						if (outValue.length() > 10)		/**!< Ograniczenie liczby wyœwietlanych cyfr */
							n = 10;
						else
							n = outValue.length();
						outValueField.setText(outValue.substring(0, n)); 
					}
				}
				else 
					JOptionPane.showMessageDialog (null, "Wprowadz wartoœæ wiêksz¹ od 0", "B³¹d", JOptionPane.ERROR_MESSAGE);
			
			} catch (IllegalArgumentException ev) {
				JOptionPane.showMessageDialog (null, "Nie uda³o siê wykonaæ operacji - wpisana niepoprawna wartoœæ", "B³¹d", JOptionPane.ERROR_MESSAGE);
				inValueField.setText("0.0");
			} 
		}
		else if(source == currencies) {		/**!< Po wybraniu waluty zmieniana jest wartoœæ pola aktywnego kursu */
			activeRate = currencies.getSelectedIndex();
			outValueField.setText("");
		}
		else {
			JOptionPane.showMessageDialog (null, "Wyst¹pi³ b³¹d, spróbuj ponownie", "B³¹d", JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Metoda tworzy pola i nadaje im parametry.
	*/
	private void createComponents() {
 	    
 	    inValueField = new JTextField();
 	    inValueField.setSize(80, 30);
 	    inValueField.setLocation(90, 50);
 	    inValueField.setText("0.0");
 	    inValueField.addKeyListener(this);
 	    
 	    outValueField = new JTextField();
 	    outValueField.setSize(80, 30);
	    outValueField.setLocation(90, 110);
 	    outValueField.setText("");
 	    outValueField.setEditable(false);
 	    
 	    startButton = new JButton("Wylicz");
		startButton.setSize(100,40);
		startButton.setLocation(90, 170);
		startButton.addActionListener(this);
		
		eurLabel = new JLabel("EUR");
		eurLabel.setSize(40, 20);
		eurLabel.setLocation(180, 55);
		
		instructionLabel1 = new JLabel("Wpisz wybran¹ kwotê (u¿yj cyfr oraz kropki):");
		instructionLabel1.setSize(240, 60);
		instructionLabel1.setLocation(20, 0);
		Font f = instructionLabel1.getFont();
		instructionLabel1.setFont(new Font(f.getFontName(), f.getStyle(), 9));
		
		instructionLabel2 = new JLabel("Wybierz docelow¹ walutê: ");
		instructionLabel2.setSize(240, 60);
		instructionLabel2.setLocation(20, 70);
		instructionLabel2.setFont(new Font(f.getFontName(), f.getStyle(), 9));
		
		currencies = new JComboBox(xmlData[0]);
		currencies.setSize(60,30);
		currencies.setLocation(175, 110);
		currencies.setSelectedIndex(0);
		currencies.addActionListener(this);
		
		this.add(inValueField);
		this.add(outValueField);
		this.add(startButton);
		this.add(eurLabel);
		this.add(currencies);
		this.add(instructionLabel1);
		this.add(instructionLabel2);
	}
	/**
	 * Funkcja blokuje mo¿liwoœæ wpisania innych znaków ni¿ cyfry i kropka.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if(!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_PERIOD)) {
			e.consume();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {	
	}
}
