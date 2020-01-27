package net.tfobz.xmlparser;

import java.awt.EventQueue;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JEditorPane;
import javax.xml.stream.XMLStreamException;

public class RssReader2Runnable extends RssReader2 implements Runnable {
	
	private JEditorPane editorPane = null;
	private String item = "";
	private StringBuilder stringBuilder;
	
	
	public RssReader2Runnable(String urlString, JEditorPane editorPane, StringBuilder stringBuilder) {
		super(urlString);
		this.editorPane = editorPane;
		this.stringBuilder = stringBuilder;
	}

	@Override
	public void run() {
		try {
			stringBuilder.append(this.getNewest() + "\n");
		} catch (XMLStreamException | IOException e) {e.printStackTrace();}
		
		final String stringBuilder_copy = stringBuilder.toString();
		try {
			EventQueue.invokeAndWait(new Runnable() {
				
				@Override
				public void run() 
				{
					editorPane.setText(stringBuilder_copy);
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {e.printStackTrace();}
		
	}
}
