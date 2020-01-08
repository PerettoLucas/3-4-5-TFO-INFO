package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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

import net.tfobz.autolistegui.Auto;
import net.tfobz.meinedefaultliste.MeinIterator;
import net.tfobz.meinedefaultliste.MeineDefaultListe;


public class AutoListeGUI extends JFrame
{
	private MeineDefaultListe autoListe = new MeineDefaultListe();
	private MeinIterator autoIterator = autoListe.elemente();

	/**
	 * FensterKomponenten
	 */
	private JLabel name = new JLabel("Name: ", SwingConstants.RIGHT);
	private JLabel erstZulassung = new JLabel("Erstzulassung: ", SwingConstants.RIGHT);
	private JTextField t_name = new JTextField();
	private JTextField t_erstZulassung = new JTextField();
	private JButton naechstes = new JButton("Nächstes");
	private JButton neu = new JButton("Neu");
	private JButton loeschen = new JButton("Löschen");
	private JPanel mitte = new JPanel();
	private JPanel unten = new JPanel();
	
	public AutoListeGUI() {
		super("AutoListe");
		this.setPreferredSize(new Dimension(400, 200));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout(10, 10));
		this.setLocation(1200, 300);
		this.setResizable(false);
		Font f = new Font("Arial", Font.BOLD, 18);
		
		neu.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(autoListe.istLeer())
					autoIterator.einfuegenElement(new Auto());
				else {
					boolean erfolg = false;
					int datum = 0;
					String name = "";
					try {
						if (!t_name.getText().isEmpty() && !t_erstZulassung.getText().isEmpty()) {
							datum = Integer.parseInt(t_erstZulassung.getText());
							name = t_erstZulassung.getText();
							erfolg = true;

						} else
							JOptionPane.showMessageDialog(AutoListeGUI.this, "Die Textfelder müssen gefüllt sein!", "Fehler",
									JOptionPane.OK_OPTION);
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(AutoListeGUI.this, "Nur ganze Zahlen bei Erstzulassung!", "Fehler",
								JOptionPane.OK_OPTION);
					}
					if (erfolg) {
						Auto a = new Auto(name, datum);
						Object o = new Object();
						autoIterator.setzenAktuellesElement(o);
						autoIterator.einfuegenElement(a);
						t_erstZulassung.setText("");
						t_name.setText("");
					}
				}
			}
		});
		naechstes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (autoListe.istLeer())
					JOptionPane.showMessageDialog(AutoListeGUI.this, "Noch kein Auto eingetragen!", 
							"Fehler", JOptionPane.OK_OPTION);
				else {
					boolean erfolg = false;
					int datum = 0;
					String name = "";
					try {
						if (!t_name.getText().isEmpty() && !t_erstZulassung.getText().isEmpty()) {
							datum = Integer.parseInt(t_erstZulassung.getText());
							name = t_erstZulassung.getText();
							erfolg = true;

						} else
							JOptionPane.showMessageDialog(AutoListeGUI.this, "Die Textfelder müssen gefüllt sein!", "Fehler",
									JOptionPane.OK_OPTION);
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(AutoListeGUI.this, "Nur ganze Zahlen bei Erstzulassung!", "Fehler",
								JOptionPane.OK_OPTION);
					}
					if (erfolg) {
						// WEnn der Iterator kein beim letzten ELement angelangt ist
						if (!autoIterator.hatNaechstesElement()) {
							// WEnn das erste Element überschrieben werden soll
							if ((JOptionPane.showConfirmDialog(AutoListeGUI.this,
									"Das Ende der Liste ist erreicht." + "\n Wollen sie die Daten des ersten Autos überschreiben?",
									"Achtung", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION)) {
								Auto a = new Auto(name, datum);
								autoListe.einfuegenErstesElement(a);
							}
							t_erstZulassung.setText("");
							t_name.setText("");
							// Iterator hat noch weitere Elemente
						}	else {
							Auto a = new Auto(name, datum);
							// nicht sicher ??? 							
							//autoIterator.setzenAktuellesElement(autoListe.elemente().naechstesElement());
							autoIterator.setzenAktuellesElement(a);
							autoIterator.naechstesElement();
						}
					}
				}
			}
		});
		loeschen.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (autoListe.istLeer())
					JOptionPane.showMessageDialog(AutoListeGUI.this, "Noch kein Auto eingetragen!", 
							"Fehler", JOptionPane.OK_OPTION);
				else {
					autoIterator.loeschenAktuellesElement();
					t_erstZulassung.setText("");
					t_name.setText("");
					if (autoListe.istLeer()) {
						JOptionPane.showMessageDialog(AutoListeGUI.this, "Die Auto-Liste ist jetzt leer!", 
								"Meldung", JOptionPane.OK_OPTION);
					} else {
						// Das nächste element der liste wird geholt
						if (autoIterator.hatNaechstesElement()) 
							autoListe.elemente().setzenAktuellesElement(autoIterator.naechstesElement());
						else {
							//wie bekomme ich das erste element????? 
							autoIterator.setzenAktuellesElement(autoListe.elemente().naechstesElement());
						}
					}
				}
			}
		});
		mitte.setLayout(new GridLayout(2, 2, 10, 10));
		mitte.setBorder(new EmptyBorder(25, 15, 15, 25));
		mitte.add(name);
		mitte.add(t_name);
		mitte.add(erstZulassung);
		mitte.add(t_erstZulassung);
		name.setFont(f);
		t_name.setFont(f);
		erstZulassung.setFont(f);
		t_erstZulassung.setFont(f);
		
		unten.setLayout(new FlowLayout());
		unten.setBorder(new EmptyBorder(0, 82, 0, 0));
		unten.add(naechstes);
		unten.add(neu);
		unten.add(loeschen);
		naechstes.setFont(f);
		neu.setFont(f);
		loeschen.setFont(f);

		add(mitte, BorderLayout.CENTER);
		add(unten, BorderLayout.SOUTH);
		
		pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new AutoListeGUI();
	}
	
}
