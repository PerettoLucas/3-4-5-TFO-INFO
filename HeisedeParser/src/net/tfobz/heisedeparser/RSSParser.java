package net.tfobz.heisedeparser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class RSSParser
{

	public static void main(String[] args) {
		try {
			RSSParser p = new RSSParser();
			URL url = new URL("https://www.heise.de/rss/heise-top-atom.xml");
			p.parse(url.openStream());
		} catch (Exception e) {
		}
	}

	private DocumentBuilder builder = null;

	public RSSParser() throws ParserConfigurationException {

		builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

	}

	private void parse(InputStream in) throws SAXException, IOException {

		Document document = builder.parse(in); 
		Element feed = document.getDocumentElement();

		for (int i = 0; i < feed.getElementsByTagName("entry").getLength(); i++) 
		{
			Element entry = (Element) feed.getElementsByTagName("entry").item(i);
			System.out.println(new Entry(entry).toString());
		}
	}

}
