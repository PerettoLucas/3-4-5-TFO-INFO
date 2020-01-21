package net.tfobz.xmlparser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class RssReader
{
	
	private boolean isitem;
	URL url;
	Channel channel=new Channel();
	ArrayList<Item> itemList=new ArrayList<>();
	Item item;
	
	private String rss_reader_name;

	/**RssReader parses the XML content of a website
	 * 
	 * Automaticly sets a Name for the current RssParser
	 * 
	 * @param url the url to parse
	 * @throws MalformedURLException 
	 */
	public RssReader(String url) throws MalformedURLException
	{
		try {
			urlParser(url);
		} catch (Exception e) {throw new MalformedURLException();}
	
		this.rss_reader_name = this.channel.getTitle();
	}
	
	public String getRss_reader_name() 
	{
		return rss_reader_name;
	}

	public void urlParser(String url) throws XMLStreamException, IOException , MalformedURLException
	{
		this.url = new URL(url);
		InputStream in = this.url.openStream();
		XMLInputFactory factory=XMLInputFactory.newInstance();
		XMLStreamReader parser=factory.createXMLStreamReader(in);
		

		String characters="";
		while(parser.hasNext())
		{
			int elementType=parser.next();
			switch(elementType)
			{

				case XMLStreamConstants.END_DOCUMENT:
				{
					parser.close();
					break;
				}
				case XMLStreamConstants.START_ELEMENT:
				{
					if(parser.getLocalName().equals("item")) {
						isitem=true;
						item =new Item();
					}
					break;
				}
				case XMLStreamConstants.END_ELEMENT:
				{
					String element=parser.getLocalName();
					if(isitem)
					{
						switch(element)
						{
							case "title":
							{
								item.setTitle(characters);
								break;
							}
							case "link":
							{
								item.setLink(characters);
								break;
							}
							case "description":
							{
								item.setDescription(characters);
								break;
							}
							case "author":
							{
								item.setAuthor(characters);
								break;
							}
							case "pubDate":
							{
								item.setPubDate(characters);
								break;
							}
						}
						characters ="";
					}else {
						switch(element)
						{
							case "url":
							{
								channel.setUrl(characters);
								break;
							}
							case "title": 
							{
								channel.setTitle(characters);
								break;
							}
							case "link":
							{
								channel.setLink(characters);
								break;
							}
							case "description": 
							{
								channel.setDescription(characters);
								break;
							}
							case "language":
							{
								channel.setLanguage(characters);
								break;
							}
							case "copyright":
							{
								channel.setCopyright(characters);
								break;
							}
						}
						characters ="";
						
					}
					if(isitem && parser.getLocalName().equals("item")) {
						itemList.add(item);
						isitem = false;
					}
					break;
				}
				case XMLStreamConstants.CHARACTERS:
				{
					if(!parser.isWhiteSpace()&&parser.getText()!=null&&parser.getText().length()>0)
						characters+=parser.getText();
					break;
				}
			}
		}
	}
	
	public void outPut()
	{
		System.out.println(channel.toString());
		System.out.println();
		for (Item item : itemList) 
		{
			System.out.println(item.toString());
		}
		System.out.println();
		
	}
	
	public String getNewest()
	{
		return "Channel : |" + channel.getTitle() + "| Newest Item : " + itemList.get(0).getTitle();
	}
}
