package net.tfobz.Ratenrechner;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class GUITilgungsplan extends JFrame
{

	private JPanel contentPane;
	private String tilgungsplan = "";
	private JFileChooser jFileChooser;
	private JEditorPane editorPane;

	/**
	 * Create the frame.
	 * @throws RatenRechnerException 
	 */
	public GUITilgungsplan(String tilgungsplan) throws RatenRechnerException
	{
		this.tilgungsplan = tilgungsplan;
		
		setTitle("Tilgungsplan");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100,100,490,461);
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		contentPane.add(editorPane, BorderLayout.CENTER);
		
		JScrollPane jsp = new JScrollPane(editorPane,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(jsp);
		
		editorPane.setText(this.tilgungsplan);
		
		JButton btnSpeichereTilgungsplan = new JButton("Speichere Tilgungsplan");
		contentPane.add(btnSpeichereTilgungsplan, BorderLayout.SOUTH);
		
		btnSpeichereTilgungsplan.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				saveFile();
			}
		});
		setVisible(true);
		
	}

	private void saveFile()
	{
		jFileChooser = new JFileChooser();
		
        jFileChooser.setAcceptAllFileFilterUsed(false);
       

        FileNameExtensionFilter filter = new FileNameExtensionFilter("HTML-Format", "html");
        jFileChooser.addChoosableFileFilter(filter);
	
		
		String filename = JOptionPane.showInputDialog("Name this file");
		if(!filename.contains(".html")) filename += ".html"; 
			
		jFileChooser.setSelectedFile(new File(filename));
		BufferedWriter writer;
		int ret = jFileChooser.showSaveDialog(null);
		if (ret == JFileChooser.APPROVE_OPTION) 
		{
			try 
			{
				writer = new BufferedWriter(new FileWriter(jFileChooser.getSelectedFile()));
				editorPane.write(writer);
				writer.close();
				JOptionPane.showMessageDialog(null, "File has been saved", "File Saved", JOptionPane.INFORMATION_MESSAGE);

			} catch (IOException ex) {ex.printStackTrace();}
			
		} else if (ret == JFileChooser.CANCEL_OPTION) JOptionPane.showMessageDialog(null, "File save has been canceled");
	}
}
