package net.tfobz.jtree;

import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * Achtung: Die Operanden werden immer der Reihe nach in die Operation geh�ngt. Mit
 * setOperand kann man beim ersten Aufruf den ersten Operanden f�llen, ruft man
 * setOperand nochmals auf, so wird der zweite Operand gef�llt. Beim L�schen werden 
 * die Operanden unter Umst�nden nach vorne verschoben.
 * @author Michael Wild
 */
public abstract class Operation extends Operand
{
	private Operand[] operand = new Operand[2];
	
	public Operation(Operand operand0, Operand operand1) {
		this.setOperand(operand0);
		this.setOperand(operand1);
	}
	public Operation() {
		super();
	}
	public void setOperand(Operand operand) {
		if (this.operand[0] == null)
			this.operand[0] = operand;
		else
			if (this.operand[1] == null)
				this.operand[1] = operand;
	}
	public Operand getOperand(int position) {
		if (position >= 0 && position <= 1)
			return this.operand[position];
		else
			return null;
	}
	public void vertausche() 
	{
		if (this.operand[0] != null && this.operand[1] != null) 
		{
			Operand operand = this.operand[0];
			this.operand[0] = this.operand[1];
			this.operand[1] = operand;
		}else throw new IllegalArgumentException("Vertauschen nicht m�glich da Operand == null");
	}
	/**Removes Child at Position 0 or 1 from the Operand Array
	 * 
	 * @param position
	 */
	public void loescheOperand(int position) 
	{
		if (position == 0) 
		{
			this.operand[0] = this.operand[1];
			this.operand[1] = null;
		} else if (position == 1) this.operand[1] = null;
	}
	
	@Override
	public TreeNode getChildAt(int childIndex) 
	{
		return this.operand[childIndex];
	}

	@Override
	public int getChildCount() 
	{
		return (this.operand[0] != null ? 1 : 0) + (this.operand[1] != null ? 1 : 0);
	}

	@Override
	public int getIndex(TreeNode node) 
	{
		if (node.equals(operand[0]))
			return 0;
		if (node.equals(operand[1]))
			return 1;
		return -1;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public Enumeration<TreeNode> children() 
	{
		return new Enumeration<TreeNode>() 
		{
			private int index = -1;

			@Override
			public boolean hasMoreElements() {
				if (index == -1) {
					if (Operation.this.operand[0] != null) {
						return true;
					}
				}
				if (index == 0) {
					if (Operation.this.operand[1] != null) {
						return true;
					}
				}
				return false;
			}

			@Override
			public TreeNode nextElement() 
			{
				index += 1;
				if (index > 1)
					index = 0;
				return Operation.this.operand[index];
			}
			
		};
	}
	
	// Implementation of MutableNode
	@Override
	public void insert(MutableTreeNode child,int index)
	{
		if(index != 0 && index != 1)
			throw new IllegalArgumentException("Index out of bounds");
		
		if(this.operand[0] == null && index == 0) this.operand[0] = (Operand)child;
		else if (this.operand[0] != null && this.operand[1] == null) this.operand[1] = (Operand)child;
	}
	@Override
	public void remove(int index)
	{
		if(index != 0 && index != 1)
			throw new IllegalArgumentException("Index out of bounds");
		
		loescheOperand(index);
	}
	
	@Override
	public void remove(MutableTreeNode node)
	{
		if (node == null)
			throw new IllegalArgumentException("node == null");
		
		remove(getIndex(node));
	}

	
	//Diese Methoden werden nicht gebraucht
	
	@Override
	public void setUserObject(Object object)
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
	@Override
	public TreeNode getParent() {
		return null;
	}
	
}
