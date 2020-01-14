package net.tfobz.xmlparser;

public class Item
{
	protected String title = null;
	protected String link = null;
	protected String description = null;
	protected String author = null;
	protected String pubDate = null;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	@Override
	public String toString() {
		return "item:\ntitle=" + title + "\nlink=" + link + "\ndescription=" + description +
				"\nauthor=" + author + "\npubDate=" + pubDate;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean ret = false;
		if (o != null && o instanceof Item) {
			Item item = (Item)o;
			ret = (title + link + description + author + pubDate)
					.equals(item.title + item.link + item.description + item.author + item.pubDate);
		}
		return ret;
	}
}
