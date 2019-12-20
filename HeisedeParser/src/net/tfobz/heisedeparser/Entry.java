package net.tfobz.heisedeparser;

import org.w3c.dom.Element;

public class Entry
{
	String title = "";
	String id = "";
	String updated = "";
	String published = "";
	String link = "";
	String summary = "";
	String content = "";

	public Entry(Element entry) 
	{
		Element title = (Element) entry.getElementsByTagName("title").item(0);
		Element id = (Element) entry.getElementsByTagName("id").item(0);
		Element updated = (Element) entry.getElementsByTagName("updated").item(0);
		Element published = (Element) entry.getElementsByTagName("published").item(0);
		Element link = (Element) entry.getElementsByTagName("link").item(0);
		Element summary = (Element) entry.getElementsByTagName("summary").item(0);
		Element content = (Element) entry.getElementsByTagName("content").item(0);
		
		this.title = title.getTextContent();
		this.id = id.getTextContent();
		this.updated = updated.getTextContent();
		this.published = published.getTextContent();
		this.link = link.getAttribute("href");
		this.summary = summary.getTextContent();
		this.content = content.getTextContent();
	}
	
	@Override
	public String toString() 
	{
		return title + "\n" +
						id	+ "\n" + 
						updated + "\n" + 
						published + "\n" + 
						link + "\n" + 
						summary + "\n" + 
						content + "\n";
	}
	
}
