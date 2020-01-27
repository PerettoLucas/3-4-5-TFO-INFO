package net.tfobz.jtree;

import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Konstante extends Operand
{
	private double ergebnis = 0.0;
	
	public Konstante(double ergebnis) {
		this.ergebnis = ergebnis;
	}
	public Konstante() {
		super();
	}
	
	public void setErgebnis(double ergebnis) {
		this.ergebnis = ergebnis;
	}
	public double getErgebnis() {
		return this.ergebnis;
	}
	public String toString() {
		return String.valueOf(this.ergebnis);
	}

	@Override
	public void setUserObject(Object object)
	{
		try {
			if(object instanceof String) this.ergebnis = Double.parseDouble((String)object);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	
	@Override
	public boolean isLeaf()
	{
		return true;
	}
	
	/*Diese Methoden werden nicht gebraucht 
	 * 
	 * (non-Javadoc)
	 * @see javax.swing.tree.TreeNode#getParent()
	 */
	@Override
	public TreeNode getParent()
	{
		return null;
	}
	@Override
	public TreeNode getChildAt(int childIndex)
	{
		return null;
	}
	@Override
	public int getChildCount()
	{
		return 0;
	}
	@Override
	public int getIndex(TreeNode node)
	{
		return -1;
	}
	@Override
	public boolean getAllowsChildren()
	{
		return false;
	}
	@Override
	public Enumeration<? extends TreeNode> children()
	{
		return null;
	}
	@Override
	public void insert(MutableTreeNode child,int index)
	{
	}
	@Override
	public void remove(int index)
	{
	}
	@Override
	public void remove(MutableTreeNode node)
	{
	}
	@Override
	public void removeFromParent()
	{
	}
	@Override
	public void setParent(MutableTreeNode newParent)
	{
	}
}
