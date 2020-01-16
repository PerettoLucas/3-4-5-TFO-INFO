package net.tfobz.rssreader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class SimpleParser
{
	public static final String XML_FILE = "src/net/tfobz/rssreader/party.xml";

	public static void main(String[] args) throws FileNotFoundException, XMLStreamException 
	{	
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream in = new FileInputStream(XML_FILE);
		XMLStreamReader parser = factory.createXMLStreamReader(in);
		
		String characters = "";
		while (parser.hasNext()) {
			int elementType = parser.next();
			switch (elementType) {
				case XMLStreamConstants.START_DOCUMENT: 
				{
					System.out.println(elementType + " START_DOCUMENT: " + parser.getVersion());
					break;
				}
				case XMLStreamConstants.END_DOCUMENT : 
				{
					System.out.println(elementType + " END_DOCUMENT: ");
					parser.close();
					break;
				}
				case XMLStreamConstants.NAMESPACE: 
				{
					System.out.println(elementType + "NAMESPACE: " + parser.getNamespaceURI());
					break;
				}
				case XMLStreamConstants.START_ELEMENT: 
				{
					
					characters = "";
					System.out.print(elementType + " START_ELEMENT: " + parser.getLocalName() + " ");
					for (int i = 0; i < parser.getAttributeCount(); i++)
						System.out.print(parser.getAttributeName(i) + "=" + parser.getAttributeValue(i) + " ");
					System.out.println();
					break;
				}
				case XMLStreamConstants.END_ELEMENT: 
				{
					System.out.println(elementType + " END_ELEMENT: " + parser.getLocalName() + " " + 
							characters.replaceAll("\n","").trim());
					characters = "";
					break;
				}
				case XMLStreamConstants.CHARACTERS: 
				{
					if (!parser.isWhiteSpace() && parser.getText() != null && parser.getText().length() > 0)
						characters+=parser.getText();
					break;
				}
			}
		}
	}
}
