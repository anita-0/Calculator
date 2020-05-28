package task.currencycalculator;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * @author Anita Kaszuba
 */
/**
 * Klasa parsuje plik xml.
 * Pobiera plik, odnajduje potrzebne dane i zapisuje je w tablicy.
 */
public class DOM {
	static String[][] xmlData;	/**!< Tablica na dane pobrane z xml */
	
	public DOM() throws IOException {
			
			try {
			    File file = new File("eurofxref-daily.xml");
			    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			    DocumentBuilder db = dbf.newDocumentBuilder();
			    Document doc = db.parse(file);
			    doc.getDocumentElement().normalize();
				 
				NodeList cubeNodeList = doc.getElementsByTagName("Cube");
				xmlData = new String[2][cubeNodeList.getLength()-2];
				for (int i = 0; i < cubeNodeList.getLength(); i++) {
					NamedNodeMap attributes = cubeNodeList.item(i).getAttributes();
					if(attributes.getLength() == 2) {
						Node cubeNode = cubeNodeList.item(i);
						if (cubeNode.getNodeType() == Node.ELEMENT_NODE) {
				    		Element element = (Element) cubeNode;
				    		xmlData[0][i-2] = element.getAttribute("currency");
				    		xmlData[1][i-2] = element.getAttribute("rate");
						}
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog (null, "B³¹d odczytu pliku xml ", "B³¹d", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			
	}
	/**
	 * @return tablica danych pobranych z pliku xml
	 */
	public static String[][] getXmlData(){
		return xmlData;
	}
	
}
