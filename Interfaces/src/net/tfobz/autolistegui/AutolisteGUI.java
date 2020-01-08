package net.tfobz.autolistegui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.tfobz.meinedefaultliste.MeinIterator;
import net.tfobz.meinedefaultliste.MeineDefaultListe;

public class AutolisteGUI extends JFrame
{

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtErstzulassung;

	private MeineDefaultListe autoListe = new MeineDefaultListe();
	private MeinIterator autoIterator = autoListe.elemente();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try {
					AutolisteGUI frame = new AutolisteGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AutolisteGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 186);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		
		JButton btnLoeschen = new JButton("Loeschen");
		btnLoeschen.setBackground(Color.LIGHT_GRAY);
		btnLoeschen.setBounds(341, 124, 89, 23);
		getContentPane().add(btnLoeschen);
		
		JButton btnNeu = new JButton("Neu");
		btnNeu.setBackground(Color.LIGHT_GRAY);
		btnNeu.setBounds(252, 124, 89, 23);
		getContentPane().add(btnNeu);
		
		JButton btnNaechstes = new JButton("Naechstes");
		btnNaechstes.setBackground(Color.LIGHT_GRAY);
		btnNaechstes.setBounds(163, 124, 89, 23);
		getContentPane().add(btnNaechstes);
		
		txtName = new JTextField();
		txtName.setBounds(163, 22, 178, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtErstzulassung = new JTextField();
		txtErstzulassung.setColumns(10);
		txtErstzulassung.setBounds(163, 53, 96, 20);
		contentPane.add(txtErstzulassung);
		
		JLabel lblName = new JLabel("Name : ");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(105, 25, 48, 14);
		contentPane.add(lblName);
		
		JLabel lblErstzulassung = new JLabel("Erstzulassung :");
		lblErstzulassung.setHorizontalAlignment(SwingConstants.RIGHT);
		lblErstzulassung.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErstzulassung.setBounds(64, 56, 89, 14);
		contentPane.add(lblErstzulassung);
		
		
		btnNeu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(autoListe.istLeer())
				{
					if(txtErstzulassung.getText() != null || txtName.getText() != null)
					{
						MeinIterator tempIterator = autoListe.elemente();
						
						while (tempIterator.hatNaechstesElement()) 
						{
							if(tempIterator.naechstesElement().equals(new Auto(txtName.getText(), Integer.parseInt(txtErstzulassung.getText()))) )
							System.out.println("Doppelt");
						}
						
						try {
							Integer.parseInt(txtErstzulassung.getText());
							autoIterator.einfuegenElement(new Auto(txtName.getText(), Integer.parseInt(txtErstzulassung.getText())));
							System.out.println("hinzugefuegt");
						} catch (Exception e2) {System.out.println("Keine gueltige Jahreszahl");}
					}
				}
				else 
				{
					autoIterator.setzenAktuellesElement(new Auto(txtName.getText(), Integer.parseInt(txtErstzulassung.getText())));
				}
			}
		});
		
		
		btnNaechstes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(autoListe.istLeer()) JOptionPane.showMessageDialog(AutolisteGUI.this, "Liste ist Leer!");
				else 
				{
					autoIterator.setzenAktuellesElement(new Auto(txtName.getText(), Integer.parseInt(txtErstzulassung.getText())));
					if(autoIterator.hatNaechstesElement())
						autoIterator.naechstesElement();
					else 
					{
						JOptionPane.showMessageDialog(AutolisteGUI.this, "Keine Elemente mehr Vorhanden!");
						autoIterator = autoListe.elemente();
					}
				}
			}
		});
	}
}
