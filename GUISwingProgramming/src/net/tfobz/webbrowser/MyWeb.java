package net.tfobz.webbrowser;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.security.auth.callback.LanguageCallback;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;


@SuppressWarnings("serial")
public class MyWeb extends JFrame 
{

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MyWeb frame=new MyWeb();
					frame.setVisible(true);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyWeb()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,500);
		setLocationRelativeTo(null);
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		contentPane.add(editorPane, BorderLayout.CENTER);
		
		JLabel lblAdresse = new JLabel("Adresse :");
		panel.add(lblAdresse);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(40);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String URL = "";
				try
				{
					if(!textField.getText().isEmpty()) URL = textField.getText();
					if(!textField.getText().substring(0,7).contains("https://")) URL = "https://" + textField.getText();
					
					editorPane.setPage(URL);
				}catch(IOException e) { e.printStackTrace(); }
				
			}
		});
		
		MeinKeyAdapter mykeyadapter = new MeinKeyAdapter();
		textField.addKeyListener(mykeyadapter);
		addKeyListener(mykeyadapter);
		btnSearch.addKeyListener(mykeyadapter);
		lblAdresse.addKeyListener(mykeyadapter);
		editorPane.addKeyListener(mykeyadapter);
		
		panel.add(btnSearch);
		
		KeyListener enterListener = new KeyListener()
		{
			
			@Override
			public void keyTyped(KeyEvent arg0){}
			
			@Override
			public void keyReleased(KeyEvent arg0){}
			
			@Override
			public void keyPressed(KeyEvent arg0)
			{
				if(arg0.getKeyCode() == 10) 
				{
					try
					{
						editorPane.setPage(textField.getText());
					}catch(IOException e) {e.printStackTrace();}
				}					
			}
		};
		
		textField.addKeyListener(enterListener);
		
		
		editorPane.addHyperlinkListener(new HyperlinkListener()
		{
			
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e)
			{System.out.println(e.getEventType());
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) 
				{
					JEditorPane pane = (JEditorPane) e.getSource();
					if (e instanceof HTMLFrameHyperlinkEvent) 
					{
						HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) e;
						HTMLDocument doc = (HTMLDocument) pane.getDocument();
						doc.processHTMLFrameHyperlinkEvent(evt);
					} else 
					{
						try 
						{
							pane.setPage(e.getURL());
							System.out.println("Gehe zu URL");
						} catch (Throwable t) 
						{
							JOptionPane.showMessageDialog(MyWeb.this, "Kann dem Hyperlink nicht folgen", "Fehler",JOptionPane.ERROR_MESSAGE);
						}
					}
				}              
           }                    
		});
		
		JScrollPane jsp = new JScrollPane(editorPane,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(jsp, BorderLayout.CENTER);
		
		
		
		

	}
	private class MeinKeyAdapter extends KeyAdapter
	{
		public void keyPressed(KeyEvent e) 
		{
			if (e.isAltDown() && e.getKeyCode() == KeyEvent.VK_S) 
			{
				textField.requestFocusInWindow();
				textField.selectAll();
			}
			
		}
	}
}
