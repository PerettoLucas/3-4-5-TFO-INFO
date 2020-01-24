package net.tfobz.xmlparser;

import java.io.IOException;

import javax.swing.JEditorPane;
import javax.xml.stream.XMLStreamException;

public class RssReader2Runnable extends RssReader2 implements Runnable {
	
	private JEditorPane editorPane = null;
	public RssReader2Runnable(String urlString, JEditorPane editorPane) {
		super(urlString);
		this.editorPane = editorPane;
	}

	@Override
	public void run() {
		String item = "";
		try {
			item = this.getNewest();
		} catch (XMLStreamException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TODO EventQueue
		editorPane.setText(item);
		
	}
}
