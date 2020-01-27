package net.tfobz.xmlparser;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JEditorPane;

public class MessageRunnable implements Runnable
{
	private JEditorPane jEditorPane;
	private StringBuilder stringBuilder;
	
	public MessageRunnable(JEditorPane jEditorPane, StringBuilder stringBuilder) 
	{
		this.jEditorPane = jEditorPane;
	}
	
	
	@Override
	public void run() 
	{
		stringBuilder.append("<b> Message : </b> Updating Channels....." );
		
		final String stringBuilder_copy = stringBuilder.toString();
		try {
			EventQueue.invokeAndWait(() -> jEditorPane.setText(stringBuilder_copy));
		} catch (InvocationTargetException | InterruptedException e) {e.printStackTrace();}
	}
	
	
	
}
