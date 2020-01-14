package net.tfobz.xmlparser;

import java.util.ArrayList;
import java.util.List;

public class Channel
{
	protected String url = null;
	protected String title = null;
	protected String link = null;
	protected String description = null;
	protected String language = null;
	protected String copyright = null;
	
	protected List<Item> items = new ArrayList<>();
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		return "channel:\ntitle=" + title + "\nlink=" + link + "\ndescription=" + description +
				"\nlanguage=" + language + "\ncopyright=" + copyright;
	}
}
