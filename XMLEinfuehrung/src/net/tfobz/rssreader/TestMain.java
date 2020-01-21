package net.tfobz.rssreader;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

public class TestMain
{

	public static void main(String[] args)
	{
		RssReader rssReader = null;
		
		//Wetter Suedtirol
		try {
			rssReader = new RssReader();
			rssReader.urlParser("http://www.provinz.bz.it/wetter/rss.asp");
		} catch (XMLStreamException | IOException e) {e.printStackTrace();}
		
		rssReader.outPut();
		rssReader = null;
		
		//Spiegel
		try {
			rssReader = new RssReader();
			rssReader.urlParser("https://www.spiegel.de/schlagzeilen/tops/index.rss");
		} catch (XMLStreamException | IOException e) {e.printStackTrace();}
		
		rssReader.outPut();
		rssReader = null;
		
		//Suedtriol news
		try {
			rssReader = new RssReader();
			rssReader.urlParser("https://www.suedtirolnews.it/feed");
		} catch (XMLStreamException | IOException e) {e.printStackTrace();}
		
		rssReader.outPut();
	}

}
