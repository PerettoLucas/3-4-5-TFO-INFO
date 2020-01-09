package net.tfobz.autolistegui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		setTitle("Autoliste");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 187);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JButton btnLoeschen = new JButton("Loeschen");
		btnLoeschen.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnLoeschen.setBackground(Color.LIGHT_GRAY);
		btnLoeschen.setBounds(360, 124, 89, 23);
		getContentPane().add(btnLoeschen);
		
		JButton btnNeu = new JButton("Neu");
		btnNeu.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNeu.setBackground(Color.LIGHT_GRAY);
		btnNeu.setBounds(261, 124, 89, 23);
		getContentPane().add(btnNeu);
		
		JButton btnNaechstes = new JButton("Naechstes");
		btnNaechstes.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNaechstes.setBackground(Color.LIGHT_GRAY);
		btnNaechstes.setBounds(162, 124, 89, 23);
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
				
				try {
					if(!txtErstzulassung.getText().isEmpty() || !txtName.getText().isEmpty())
					{
						Auto auto = new Auto(txtName.getText(), Integer.parseInt(txtErstzulassung.getText()));
	
						if (autoListe.istLeer()) 
						{
							autoListe.einfuegenErstesElement(auto);
							autoIterator.naechstesElement();
						}
						else autoIterator.einfuegenElement(auto);
						JOptionPane.showMessageDialog(null, "Element wurde in die Liste eingefuegt"); 
					} else JOptionPane.showMessageDialog(null, "Die Felder duerfen nicht leer sein!");
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Erstzulassung muss vom typ Zahl sein! : "+txtErstzulassung.getText()); 
				}
			}
		});
		
		
		btnNaechstes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(autoListe.istLeer()) JOptionPane.showMessageDialog(AutolisteGUI.this, "Liste ist Leer!");
				
				//Vorhaeriges Speichern
				try {
					if(!txtErstzulassung.getText().isEmpty() || !txtName.getText().isEmpty())
					{
						Auto auto = new Auto(txtName.getText(), Integer.parseInt(txtErstzulassung.getText()));
						autoIterator.setzenAktuellesElement(auto);
					} else JOptionPane.showMessageDialog(null, "Die Felder duerfen nicht leer sein!");
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Erstzulassung muss vom typ Zahl sein! : "+txtErstzulassung.getText()); 
				}
				
				//Weiterspringen
				if (autoIterator.hatNaechstesElement())
				{
					Auto auto = (Auto)autoIterator.naechstesElement();
					txtName.setText(auto.getName());
					txtErstzulassung.setText(""+auto.getErstzulassung());
				} else 
				{
					JOptionPane.showMessageDialog(null, "Ende der Liste erreicht, springe zum Anfang"); 
					autoIterator.naechstesElement();
					Auto auto = (Auto)autoIterator.naechstesElement();
					txtName.setText(auto.getName());
					txtErstzulassung.setText(""+auto.getErstzulassung());
				}
			}
		});
		
		btnLoeschen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(autoListe.istLeer()) JOptionPane.showMessageDialog(AutolisteGUI.this, "Liste ist Leer!");
				else if (autoIterator.loeschenAktuellesElement()) 
				{
					JOptionPane.showMessageDialog(null, "Erfolgreich geloescht"); 
					txtErstzulassung.setText("");
					txtName.setText("");
				} else JOptionPane.showMessageDialog(null, "Auto Liste ist leer"); 
			}
		});
		
	}
}
