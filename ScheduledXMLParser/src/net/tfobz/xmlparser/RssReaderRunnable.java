package net.tfobz.xmlparser;

import java.awt.EventQueue;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JEditorPane;
import javax.xml.stream.XMLStreamException;

public class RssReaderRunnable extends ScheduledRssReader implements Runnable {
	
	private JEditorPane editorPane = null;
	private StringBuilder stringBuilder;
	
	
	public RssReaderRunnable(String urlString, JEditorPane editorPane, StringBuilder stringBuilder) 
	{
		super(urlString);
		this.editorPane = editorPane;
		this.stringBuilder = stringBuilder;
	}

	@Override
	public void run() {
		try {
			stringBuilder.append(this.getNewest() + "<br>");
		} catch (XMLStreamException | IOException e) {/*Ignore*/}
		
		final String stringBuilder_copy = stringBuilder.toString();
		try {
			EventQueue.invokeAndWait(() -> editorPane.setText(stringBuilder_copy));
		} catch (InvocationTargetException | InterruptedException e) {e.printStackTrace();}
		
	}
}
