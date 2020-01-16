package net.gobbz.eurorechner;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class MeinTastaturAbhoerer extends KeyAdapter 
{
	JTextField[] jTextFields = null;
	EuroUmrechner euroUmrechner = null;
	
	public MeinTastaturAbhoerer(JTextField[] tF, EuroUmrechner eu)
	{
		this.jTextFields = tF;
		this.euroUmrechner = eu;
	}
	
	public void keyReleased(KeyEvent e) 
	{
		
		for(int i = 0; i < this.jTextFields.length ;i++)
		{
			if(e.getSource() == this.jTextFields[i])
			{
				//Setzt den Text + den Betrag dieses Feldes
				this.euroUmrechner.setWaehrung(i);
				if(!this.jTextFields[i].getText().isEmpty())
					this.euroUmrechner.setBetrag(Double.parseDouble(this.jTextFields[i].getText()));
				
				if(this.jTextFields[i].getText().isEmpty()) 
				{
					for(int j = 0; j < this.jTextFields.length; j++)
					{
						if(e.getSource() != this.jTextFields[j])
						{
							jTextFields[j].setText("/");
						}
					}
				}
				else 
				{
					
					//Setzt den Text + Betrag der restlichen Felder
					for(int j = 0; j < this.jTextFields.length; j++)
					{
						if(e.getSource() != this.jTextFields[j])
						{
							//TODO Auf zwei nachkommastellen runden.
							this.euroUmrechner.setBetrag((this.euroUmrechner.getBetrag(this.euroUmrechner.getWaehrung())));
							jTextFields[j].setText(""+(Math.round(euroUmrechner.getBetrag(j) * 100.0)) / 100.0);
						}
					}
				}
				break;			
			}
		}		
	}
}
