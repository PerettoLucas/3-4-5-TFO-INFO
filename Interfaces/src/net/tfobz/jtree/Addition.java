package net.tfobz.jtree;

import java.util.Enumeration;

import javax.swing.tree.TreeNode;

public class Addition extends Operation
{
	public Addition(Operand operand0, Operand operand1) {
		super(operand0, operand1);
	}
	public Addition() {
		super();
	}
	public double getErgebnis() {
		double ret = 0.0;
		if (this.getOperand(0) != null)
			ret = this.getOperand(0).getErgebnis();
		if (this.getOperand(1) != null)
			ret = ret + this.getOperand(1).getErgebnis();
		return ret;
	}
	
	public String toString() 
	{
		if(this.getOperand(0) != null && this.getOperand(1) != null)
			return "(" + this.getOperand(0) + "+" + this.getOperand(1) +")" + "=" + getErgebnis() + ")";
		return null;
	}
	@Override
	public TreeNode getChildAt(int childIndex)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getChildCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public TreeNode getParent()
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getIndex(TreeNode node)
	{
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean getAllowsChildren()
	{
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isLeaf()
	{
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Enumeration<? extends TreeNode> children()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
