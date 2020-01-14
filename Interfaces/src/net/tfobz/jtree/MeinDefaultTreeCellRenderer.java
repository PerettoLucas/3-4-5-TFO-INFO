package net.tfobz.jtree;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

@SuppressWarnings("serial")
public class MeinDefaultTreeCellRenderer extends DefaultTreeCellRenderer
{

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selcted, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		Component ret=null;
		ret=super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

		
		switch(value.getClass().getSimpleName())
		{
			case "Division":
			{
				ImageIcon i=new ImageIcon(this.getClass().getResource("division.png"));
				setIcon(i);
				break;
			}
			case "Multiplikation":
			{
				ImageIcon i=new ImageIcon(this.getClass().getResource("multiplizieren.png"));
				setIcon(i);
				break;
			}
			case "Addition":
			{
				ImageIcon i=new ImageIcon(this.getClass().getResource("addition.png"));
				setIcon(i);
				break;
			}	
			case "Subtraktion":
			{
				ImageIcon i=new ImageIcon(this.getClass().getResource("subtraktion.png"));
				setIcon(i);
				break;
			}
			case "Konstante":
			{
				ImageIcon i=new ImageIcon(this.getClass().getResource("konstante.png"));
				setIcon(i);
				break;
			}
			default:
				break;
		}
		return ret;
	}

}