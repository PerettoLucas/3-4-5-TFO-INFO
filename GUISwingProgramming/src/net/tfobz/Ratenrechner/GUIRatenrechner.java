package net.tfobz.Ratenrechner;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.gobbz.eurorechner.EuroUmrechner;
import javax.swing.DropMode;

@SuppressWarnings("serial")
public class GUIRatenrechner extends JFrame
{

	private JPanel contentPane;
	private JTextField textFieldBarwert;
	private JTextField textFieldJahreszinssatz;
	private JTextField textFieldLaufzeitInJahren;
	private JTextField textFieltRate;

	private RatenRechner ratenRechner = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try {
					GUIRatenrechner frame = new GUIRatenrechner();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void showOptionPane(String message)
	{
		JOptionPane.showMessageDialog(this, message);
	}

	/**
	 * Create the frame.
	 */
	public GUIRatenrechner()
	{
		setTitle("Ratenrechner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);

		// Creating RatenRechner
		ratenRechner = new RatenRechner();

		JLabel lblRatenrechner = new JLabel("Ratenrechner");
		lblRatenrechner.setFont(new Font("Dialog", Font.BOLD, 22));

		JRadioButton rdbtnVorschssig = new JRadioButton("Vorschüssig");
		rdbtnVorschssig.setFont(new Font("Dialog", Font.BOLD, 15));
		JRadioButton rdbtnNachschssig = new JRadioButton("Nachschüssig");
		rdbtnNachschssig.setFont(new Font("Dialog", Font.BOLD, 15));

		// RadioButton Actionlistener
		rdbtnVorschssig.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (rdbtnNachschssig.isSelected())
					rdbtnNachschssig.setSelected(false);
				rdbtnVorschssig.setSelected(true);
				ratenRechner.setNachschuessig(true);
			}
		});

		rdbtnNachschssig.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (rdbtnVorschssig.isSelected())
					rdbtnVorschssig.setSelected(false);
				rdbtnNachschssig.setSelected(true);
				ratenRechner.setNachschuessig(false);
			}
		});

		textFieldBarwert = new JTextField();
		textFieldBarwert.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldBarwert.setColumns(10);

		textFieldJahreszinssatz = new JTextField();
		textFieldJahreszinssatz.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldJahreszinssatz.setColumns(10);

		textFieldLaufzeitInJahren = new JTextField();
		textFieldLaufzeitInJahren.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldLaufzeitInJahren.setColumns(10);

		JButton btnBerechneBarwert = new JButton("Berechne Barwert");
		btnBerechneBarwert.setHorizontalAlignment(SwingConstants.LEFT);

		JButton btnBerechneLaufzeit = new JButton("Berechne Laufzeit");
		btnBerechneLaufzeit.setHorizontalAlignment(SwingConstants.LEFT);

		JButton btnBerechneRate = new JButton("Berechne Rate");
		btnBerechneRate.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lblBarwert = new JLabel("Barwert : ");
		lblBarwert.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblJahreszinssatz = new JLabel("Jahreszinssatz :");
		lblJahreszinssatz.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblLaufzeitInJahren = new JLabel("Laufzeit in Jahren :");
		lblLaufzeitInJahren.setHorizontalAlignment(SwingConstants.RIGHT);

		JComboBox<String> comboBoxRatenProJahr = new JComboBox<String>();
		comboBoxRatenProJahr.setModel(
				new DefaultComboBoxModel<String>(new String[] { "12 Raten ", "6 Raten", "4 Raten", "1 Rate" }));

		/*
		 * Button Action Listener / Setting the Value
		 */
		btnBerechneBarwert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{

				try {
					ratenRechner.setJahreszinssatz(textFieldJahreszinssatz.getText());
				} catch (RatenRechnerException e1) {
					showOptionPane(e1.getMessage());
				}

				try {
					ratenRechner.setLaufzeitInJahren(textFieldLaufzeitInJahren.getText());
				} catch (RatenRechnerException e1) {
					showOptionPane(e1.getMessage());
				}

				try {
					ratenRechner.setRatenProJahr((String) comboBoxRatenProJahr.getSelectedItem());
				} catch (RatenRechnerException e2) {
					showOptionPane(e2.getMessage());
				}

				try {
					ratenRechner.setRate(textFieltRate.getText());
				} catch (RatenRechnerException e2) {
					showOptionPane(e2.getMessage());
				}

				// setting the Value to the Textfield
				try {
					textFieldBarwert.setText(ratenRechner.getBarwert());
				} catch (RatenRechnerException e1) {
					showOptionPane(e1.getMessage());
				}
			}
		});
		btnBerechneLaufzeit.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					ratenRechner.setJahreszinssatz(textFieldJahreszinssatz.getText());
				} catch (RatenRechnerException e1) {
					showOptionPane(e1.getMessage());
				}

				try {
					ratenRechner.setRatenProJahr((String) comboBoxRatenProJahr.getSelectedItem());
				} catch (RatenRechnerException e2) {
					showOptionPane(e2.getMessage());
				}

				try {
					ratenRechner.setRate(textFieltRate.getText());
				} catch (RatenRechnerException e2) {
					showOptionPane(e2.getMessage());
				}

				try {
					ratenRechner.setBarwert(textFieldBarwert.getText());
				} catch (RatenRechnerException e1) {
					showOptionPane(e1.getMessage());
				}

				// setting the Value to the Textfield
				try {
					textFieldLaufzeitInJahren.setText(ratenRechner.getLaufzeitInJahren());
				} catch (RatenRechnerException e1) {
					showOptionPane(e1.getMessage());
				}
			}
		});
		btnBerechneRate.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					ratenRechner.setJahreszinssatz(textFieldJahreszinssatz.getText());
				} catch (RatenRechnerException e1) {
					showOptionPane(e1.getMessage());
				}

				try {
					ratenRechner.setRatenProJahr((String) comboBoxRatenProJahr.getSelectedItem());
				} catch (RatenRechnerException e2) {
					showOptionPane(e2.getMessage());
				}

				try {
					ratenRechner.setLaufzeitInJahren(textFieldLaufzeitInJahren.getText());
				} catch (RatenRechnerException e1) {
					showOptionPane(e1.getMessage());
				}

				try {
					ratenRechner.setBarwert(textFieldBarwert.getText());
				} catch (RatenRechnerException e1) {
					showOptionPane(e1.getMessage());
				}

				// setting the Value to the Textfield
				try {
					textFieltRate.setText(ratenRechner.getRate());
				} catch (RatenRechnerException e2) {
					showOptionPane(e2.getMessage());
				}

			}
		});

		JLabel lblRratenProJahr = new JLabel("Rraten pro Jahr :");
		lblRratenProJahr.setHorizontalAlignment(SwingConstants.RIGHT);

		textFieltRate = new JTextField();
		textFieltRate.setHorizontalAlignment(SwingConstants.CENTER);
		textFieltRate.setColumns(10);

		JLabel lblRate = new JLabel("Rate :");
		lblRate.setHorizontalAlignment(SwingConstants.RIGHT);

		JButton btnZeigeTilgungsplan = new JButton("Zeige Tilgungsplan");
		btnZeigeTilgungsplan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new Thread(new Runnable() {
					public void run()
					{
						boolean flag = false;

						btnBerechneBarwert.doClick();
						btnBerechneLaufzeit.doClick();
						btnBerechneRate.doClick();

						if (rdbtnNachschssig.isSelected())
							ratenRechner.setNachschuessig(true);
						else if (rdbtnVorschssig.isSelected())
							ratenRechner.setNachschuessig(false);

						try {
							ratenRechner.setJahreszinssatz(textFieldJahreszinssatz.getText());
						} catch (RatenRechnerException e1) {
							showOptionPane(e1.getMessage());
							flag = true;
						}

						try {
							ratenRechner.setRatenProJahr((String) comboBoxRatenProJahr.getSelectedItem());
						} catch (RatenRechnerException e2) {
							showOptionPane(e2.getMessage());
							flag = true;
						}

						try {
							ratenRechner.setLaufzeitInJahren(textFieldLaufzeitInJahren.getText());
						} catch (RatenRechnerException e1) {
							showOptionPane(e1.getMessage());
							flag = true;
						}

						try {
							ratenRechner.setBarwert(textFieldBarwert.getText());
						} catch (RatenRechnerException e1) {
							showOptionPane(e1.getMessage());
							flag = true;
						}

						try {
							ratenRechner.setRate(textFieltRate.getText());
						} catch (RatenRechnerException e2) {
							showOptionPane(e2.getMessage());
							flag = true;
						}

						if (!flag)
							try {
								new GUITilgungsplan(ratenRechner.getTilgungsplan());
							} catch (RatenRechnerException e) {
								e.printStackTrace();
							}
					}

				}).start();

			}
		});

		JLabel label = new JLabel("€");

		JLabel label_1 = new JLabel("€");

		JLabel label_2 = new JLabel("€");

		// GUI - Layout Settings
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(249)
										.addComponent(lblRatenrechner))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
										.createSequentialGroup().addGroup(gl_contentPane
												.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
														.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_contentPane.createSequentialGroup()
																.addContainerGap()
																.addComponent(lblBarwert, GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addComponent(lblRate, GroupLayout.PREFERRED_SIZE, 67,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblJahreszinssatz).addComponent(
																lblLaufzeitInJahren, GroupLayout.PREFERRED_SIZE, 136,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblRratenProJahr, GroupLayout.PREFERRED_SIZE, 136,
																GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(textFieldLaufzeitInJahren,
																		GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
																.addComponent(
																		textFieldJahreszinssatz,
																		GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
																.addGroup(gl_contentPane.createSequentialGroup()
																		.addComponent(textFieldBarwert,
																				GroupLayout.DEFAULT_SIZE, 179,
																				Short.MAX_VALUE)
																		.addPreferredGap(ComponentPlacement.RELATED))
																.addComponent(comboBoxRatenProJahr, 0, 179,
																		Short.MAX_VALUE)
																.addComponent(textFieltRate, GroupLayout.DEFAULT_SIZE,
																		179, Short.MAX_VALUE)))
												.addGroup(
														gl_contentPane
																.createSequentialGroup().addComponent(rdbtnVorschssig)
																.addPreferredGap(ComponentPlacement.RELATED)))
										.addGap(4).addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup().addGroup(
														gl_contentPane.createParallelGroup(Alignment.TRAILING)
																.addComponent(label).addComponent(label_2,
																		GroupLayout.PREFERRED_SIZE, 8,
																		GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(rdbtnNachschssig,
																		GroupLayout.PREFERRED_SIZE, 142,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(btnBerechneLaufzeit,
																		GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
																.addComponent(btnBerechneRate, GroupLayout.DEFAULT_SIZE,
																		197, Short.MAX_VALUE)
																.addComponent(btnBerechneBarwert,
																		GroupLayout.DEFAULT_SIZE, 197,
																		Short.MAX_VALUE)))
												.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 8,
														GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_contentPane.createSequentialGroup().addGap(261)
												.addComponent(btnZeigeTilgungsplan, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGap(122))))
						.addGap(59)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(31).addComponent(lblRatenrechner).addGap(27)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnNachschssig)
						.addComponent(rdbtnVorschssig))
				.addGap(50)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldBarwert, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBerechneBarwert, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(label)
						.addComponent(lblBarwert, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldJahreszinssatz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblJahreszinssatz).addComponent(label_1))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(6).addComponent(
								textFieldLaufzeitInJahren, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addComponent(btnBerechneLaufzeit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(lblLaufzeitInJahren))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxRatenProJahr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRratenProJahr))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addGroup(gl_contentPane
						.createSequentialGroup().addGap(6)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieltRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2)))
						.addComponent(btnBerechneRate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(lblRate))
				.addGap(18).addComponent(btnZeigeTilgungsplan, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
				.addGap(49)));
		contentPane.setLayout(gl_contentPane);
	}
}
